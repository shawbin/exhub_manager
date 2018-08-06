package io.exhub.exhub_manager.service;

import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.pojo.DO.IdentityAuthenticationDO;
import io.exhub.exhub_manager.pojo.DO.LoginRecordDO;
import io.exhub.exhub_manager.pojo.DO.PointRecordDO;
import io.exhub.exhub_manager.pojo.DO.UserDO;

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

    /**
     * 查询身份认证记录数
     * @param status
     * @return
     */
    long countByExample(Byte status);

    /**
     * bilala用户列表
     * @param params
     * @return
     */
    List<UserDO> postList(Map<String, Object> params);

    /**
     * 获取登录记录
     * @return
     * @param userId
     */
    List<LoginRecordDO> getLoginRecord(Long userId);

    /**
     * 关闭/开启google验证
     * @param userId
     * @param flag true 开启 false 关闭
     * @return
     */
    ServerResponse putGoogleAuth(Long userId, boolean flag);

    /**
     * 冻结/解冻账户
     * @param userId
     * @param status
     * @return
     */
    ServerResponse putAccountStatus(Long userId, boolean status);
}
