package io.exhub.exhub_manager.service.impl;

import io.exhub.exhub_manager.common.ResponseCode;
import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.mapper.IdentityAuthenticationDOMapper;
import io.exhub.exhub_manager.pojo.DO.IdentityAuthenticationDO;
import io.exhub.exhub_manager.pojo.DO.IdentityAuthenticationDOExample;
import io.exhub.exhub_manager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ServerResponse postIdentityAuditId(Long id, Byte status, String message) {

        IdentityAuthenticationDO identity = new IdentityAuthenticationDO();
        identity.setId(id);
        identity.setStatus(status);
        identity.setMessage(message);
        identityMapper.updateByPrimaryKeySelective(identity);
        return ServerResponse.createBySuccess();
    }

}
