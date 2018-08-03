package io.exhub.exhub_manager.service.impl;

import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.mapper.IdentityAuthenticationDOMapper;
import io.exhub.exhub_manager.mapper.PointRecordDOMapper;
import io.exhub.exhub_manager.pojo.DO.IdentityAuthenticationDO;
import io.exhub.exhub_manager.pojo.DO.IdentityAuthenticationDOExample;
import io.exhub.exhub_manager.pojo.DO.PointRecordDO;
import io.exhub.exhub_manager.service.IUserService;
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
    private IdentityAuthenticationDOMapper identityMapper;
    @Autowired
    private PointRecordDOMapper pointMapper;

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
            //身份审核成功 加积分
            if (status.equals(IdentityAuthenticationDO.ADUIT_PASS)) {
                //更新注册积分记录
                //10000 前一万人额外奖励 500
                //查询审核成功的人数
                long count = countByExample(IdentityAuthenticationDO.ADUIT_PASS);
                if (count > peopleCount) {
                    updatePoint(identityDO.getUserId(), PointRecordDO.REGIST, registerPoint);
                }else {
                    updatePoint(identityDO.getUserId(), PointRecordDO.REGIST, registerPoint + extraPoint);
                }
                updatePoint(identityDO.getUserId(), PointRecordDO.REGIST, registerPoint);
                //更新被推荐记录
                updatePoint(identityDO.getUserId(), PointRecordDO.REFFER, referrerPoint);
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


}
