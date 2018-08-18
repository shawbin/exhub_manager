package io.exhub.exhub_manager.service.impl;

import io.exhub.exhub_manager.common.ResponseCode;
import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.mapper.IdentityAuthenticationDOMapper;
import io.exhub.exhub_manager.mapper.LoginRecordDOMapper;
import io.exhub.exhub_manager.mapper.PointRecordDOMapper;
import io.exhub.exhub_manager.mapper.UserDOMapper;
import io.exhub.exhub_manager.pojo.DO.*;
import io.exhub.exhub_manager.service.IMailService;
import io.exhub.exhub_manager.service.IUserService;
import io.exhub.exhub_manager.util.GoogleAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 用户管理
 * @author
 * @data 2018/7/27
 */
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IMailService iMailService;

    @Autowired
    private IdentityAuthenticationDOMapper identityMapper;
    @Autowired
    private PointRecordDOMapper pointMapper;
    @Autowired
    private UserDOMapper userMapper;
    @Autowired
    private LoginRecordDOMapper loginRecordMapper;

    @Value("${exhubConfig.registerPoint}")
    private Long registerPoint;
    @Value("${exhubConfig.referrerPoint}")
    private Long referrerPoint;
    @Value("${exhubConfig.extraPoint}")
    private Long extraPoint;
    @Value("${exhubConfig.peopleCount}")
    private Long peopleCount;

    /**
     * 按照条件查询身份申请
     * @param params
     * @return
     */
    @Override
    public List<IdentityAuthenticationDO> postIdentityAuthentication(Map<String, Object> params) {

        return identityMapper.selectIdentityByClause(params);
    }

    /**
     * 身份认证结果
     * @param id
     * @return
     */
    @Override
    public IdentityAuthenticationDO getIdentityId(Long id) {

        //查询身份验证及结果
        return identityMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改审核结果和备注
     * @param id
     * @param status
     * @param message
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServerResponse postIdentityAuditId(Long id, Byte status, String message) {

        IdentityAuthenticationDO identityDO = identityMapper.selectByPrimaryKey(id);
        if (identityDO != null) {
            IdentityAuthenticationDO identity = new IdentityAuthenticationDO();
            identity.setId(id);
            identity.setStatus(status);
            identity.setMessage(message);
            identityMapper.updateByPrimaryKeySelective(identity);
            //获取用户信息
            UserDO userDO = userMapper.selectByPrimaryKey(identityDO.getUserId());
            //身份审核成功 10000 前一万人额外奖励 500
            if (status.equals(IdentityAuthenticationDO.ADUIT_PASS)) {
                //查询审核成功的人数
                long count = countByExample(IdentityAuthenticationDO.ADUIT_PASS);
                if (count <= peopleCount) {
                    //插入额外奖励
                    insertPoint(extraPoint, userDO.getUsername(), userDO.getUsername(), PointRecordDO.AUTH);
                }
                //异步发送审核成功邮箱
                iMailService.identityAuthSuccess(userDO.getUsername());
            }else {
                //异步发送审核失败邮箱
                iMailService.identityAuthFail(userDO.getUsername());
            }
        }
        return ServerResponse.createBySuccess();
    }

    /**
     * 查询身份认证记录数
     * @param status
     * @return
     */
    @Override
    public long countByExample(Byte status) {

        IdentityAuthenticationDOExample example = new IdentityAuthenticationDOExample();
        IdentityAuthenticationDOExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(status);
        return identityMapper.countByExample(example);
    }

    /**
     * bilala用户列表
     * @param params
     * @return
     */
    @Override
    public List<UserDO> postList(Map<String, Object> params) {

        return userMapper.listUserDO(params);
    }

    /**
     * 获取登录记录
     * @return
     * @param //userId
     */
    @Override
    public List<LoginRecordDO> getLoginRecord(Long userId) {

        LoginRecordDOExample example = new LoginRecordDOExample();
        example.setOrderByClause("id desc");
        LoginRecordDOExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return loginRecordMapper.selectByExample(example);
    }

    /**
     * 关闭/开启google验证
     * @param userId
     * @param flag true 开启 false 关闭
     * @return
     */
    @Override
    public ServerResponse putGoogleAuth(Long userId, boolean flag) {

        UserDO userDO = userMapper.selectByPrimaryKey(userId);
        if (userDO == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ACCOUNT_NOT_EXIST.getCode(), ResponseCode.ACCOUNT_NOT_EXIST.getDesc());
        }
        userDO = new UserDO();
        userDO.setUserId(userId);
        userDO.setGoogleValidateCodeFlag(flag);
        //重新设置googleAuth
        userDO.setGoogleValidateCode(GoogleAuth.getGoogleKey());
        userMapper.updateByPrimaryKeySelective(userDO);
        return ServerResponse.createBySuccess();
    }

    /**
     * 冻结/解冻账户
     * @param userId
     * @param status
     * @return
     */
    @Override
    public ServerResponse putAccountStatus(Long userId, boolean status) {

        UserDO userDO = userMapper.selectByPrimaryKey(userId);
        if (userDO == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ACCOUNT_NOT_EXIST.getCode(), ResponseCode.ACCOUNT_NOT_EXIST.getDesc());
        }
        userDO = new UserDO();
        userDO.setUserId(userId);
        userDO.setStatus(status);
        userMapper.updateByPrimaryKeySelective(userDO);
        return ServerResponse.createBySuccess();
    }

    /**
     * 变动积分
     * @param UserId
     * @param type
     * @param point
     */
    public void updatePoint(Long UserId, Byte type, Long point) {
        //查询type积分记录
        PointRecordDO record = pointMapper.getPointByUserIdType(UserId, type);
        if (record != null) {
            //变动积分
            PointRecordDO recordDO = new PointRecordDO();
            recordDO.setId(record.getId());
            recordDO.setPoint(point);
            pointMapper.updateByPrimaryKeySelective(recordDO);
        }

    }

    /**
     * 插入额外奖励
     * @param extraPoint
     * @param referrer
     * @param referral
     * @param type
     */
    public void insertPoint(Long extraPoint, String referrer, String referral, Byte type) {

        PointRecordDO point = new PointRecordDO();
        point.setPoint(extraPoint);
        point.setReferrer(referrer);
        point.setReferral(referral);
        point.setType(type);
        pointMapper.insertSelective(point);
    }


}
