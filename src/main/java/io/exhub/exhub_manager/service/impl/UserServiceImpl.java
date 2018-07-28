package io.exhub.exhub_manager.service.impl;

import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.mapper.IdentityAuthenticationDOMapper;
import io.exhub.exhub_manager.mapper.PointRecordDOMapper;
import io.exhub.exhub_manager.pojo.DO.IdentityAuthenticationDO;
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

        IdentityAuthenticationDO identity = new IdentityAuthenticationDO();
        identity.setId(id);
        identity.setStatus(status);
        identity.setMessage(message);
        identityMapper.updateByPrimaryKeySelective(identity);
        //身份审核成功 加积分
        if (status.equals(IdentityAuthenticationDO.ADUIT_PASS)) {
            //更新注册积分记录
            updatePoint(identity.getUserId(), PointRecordDO.REGIST, registerPoint);
            //更新被推荐记录
            updatePoint(identity.getUserId(), PointRecordDO.REFFER, referrerPoint);
        }
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

}
