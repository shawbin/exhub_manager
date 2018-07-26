package io.exhub.exhub_manager.service;

import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.pojo.DO.LoginRecordDO;
import io.exhub.exhub_manager.pojo.DO.ManagerUserDO;

import java.util.List;

/**
 * @author
 * @data 2018/7/25
 */
public interface IUserService {

    /**
     * manager登录
     * @param username
     * @param password
     * @return
     */
    ServerResponse postLogin(String username, String password);

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    ManagerUserDO getManagerUserByUsername(String username);

    /**
     * 判断用户名是否重复
     * @param username
     * @return
     */
    ServerResponse getLoginCheckUsername(String username);

    /**
     * 分配账号
     * @param role 角色
     * @param username
     * @param password
     * @return
     */
    ServerResponse postAssignedAccount(Long role, String username, String password);



    /**
     * 获取登录记录
     * @return
     * @param //userId
     */
    /*List<LoginRecordDO> getLoginRecord(Long userId);*/

}
