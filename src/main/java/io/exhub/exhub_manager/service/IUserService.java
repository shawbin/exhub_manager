package io.exhub.exhub_manager.service;

import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.pojo.DO.IdentityAuthenticationDO;

import java.util.List;
import java.util.Map;

/**
 * 用户管理
 * @author
 * @data 2018/7/27
 */
public interface IUserService {

    /**
     * 按照条件查询身份申请
     * @param identityDO
     * @return
     */
    List<IdentityAuthenticationDO> postIdentityAuthentication(Map<String, Object> identityDO);

    /**
     * 身份认证结果
     * @param id
     * @return
     */
    IdentityAuthenticationDO getIdentityId(Long id);

    /**
     * 修改审核结果和备注
     * @param id
     * @param status
     * @param message
     * @return
     */
    ServerResponse postIdentityAuditId(Long id, Byte status, String message);
}
