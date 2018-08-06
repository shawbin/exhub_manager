package io.exhub.exhub_manager.service;

import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.pojo.DO.LoginRecordDO;
import io.exhub.exhub_manager.pojo.DO.ManagerRoleDO;
import io.exhub.exhub_manager.pojo.DO.ManagerUserDO;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @data 2018/7/25
 */
public interface IBackstageService {

    /**
     * manager登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    ServerResponse postLogin(String username, String password, HttpSession session);

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
     * 获取角色模块列表
     * @param managerUser
     * @param modelMap
     */
    void getModulesMap(ManagerUserDO managerUser, ModelMap modelMap);

    /**
     * 获取账号列表
     * @return
     */
    List<Map<String,Object>> getManagerUserList();

    /**
     * 删除后台账号
     * @param id
     * @return
     */
    ServerResponse deleteManagerAccount(Long id);

    /**
     * 获取角色列表
     * @return
     */
    List<ManagerRoleDO> listRole();

    /**
     * 发送角色列表到页面
     * @param id
     * @param modelMap
     */
    void getBackstageAccountEdit(Long id, ModelMap modelMap);

    /**
     * 密码管理-修改密码
     * @param managerUser
     * @param password
     * @param resetPassword
     * @return
     */
    ServerResponse putAccountPassword(ManagerUserDO managerUser, String password, String resetPassword);
}
