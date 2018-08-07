package io.exhub.exhub_manager.controller;

import io.exhub.exhub_manager.pojo.DO.ManagerUserDO;
import io.exhub.exhub_manager.service.IBackstageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

/**
 * @author
 * @data 2018/7/25
 */
@Controller
public class StaticController {

    @Autowired
    private IBackstageService iBackstageService;

    @Value("${exhubConfig.managerSession}")
    private String managerSession;

    /**
     * 登录页
     * @return
     */
    @GetMapping(value = "/login.html")
    public String getLogin() {

        return "login";
    }

    /**
     * 首页 此上添加列表
     * @return
     */
    @GetMapping(value = "/index.html")
    public String getIndex(HttpSession session, ModelMap modelMap) {

        Object object = session.getAttribute(managerSession);
        if (object == null) {
            return "login";
        }
        ManagerUserDO managerUser = (ManagerUserDO)object;
        iBackstageService.getModulesMap(managerUser.getRoleId(), modelMap);
        return "index";
    }

    /**
     * 用户管理-kyc
     * @return
     */
    @GetMapping(value = "/user/identity.html")
    public String getUserIdentity() {

        return "user/identity";
    }

    /**
     * 用户管理-bilala用户管理
     * @return
     */
    @GetMapping(value = "/user/bilala.html")
    public String getUserBilala() {

        return "user/bilala";
    }

    /**
     * 用户管理-登录历史
     * @return
     */
    @GetMapping(value = "/user/login-record.html")
    public String getUserLoginRecord(@PathParam(value = "userId") Long userId, ModelMap modelMap) {

        modelMap.put("userId", userId);
        return "user/login-record";
    }

    /**
     * 后台管理-账号管理
     * @return
     */
    @GetMapping(value = "/backstage/account.html")
    public String getBackstageAccount() {

        return "backstage/account";
    }

    /**
     * 新增或编辑账号
     * @return
     */
    @GetMapping(value = "/backstage/account/edit.html")
    public String getBackstageAccountEdit(Long id, ModelMap modelMap) {

        //发送角色列表到页面
        iBackstageService.getBackstageAccountEdit(id, modelMap);
        return "backstage/account-edit";
    }

    /**
     * 后台管理-密码管理
     * @return
     */
    @GetMapping(value = "/backstage/password.html")
    public String getBackstagePassword() {

        return "backstage/password";
    }

    /**
     * 后台管理-角色管理
     * @return
     */
    @GetMapping(value = "/backstage/role.html")
    public String getBackstageRole() {

        return "backstage/role";
    }

    /**
     * 角色模块绑定页
     * @return
     */
    @GetMapping(value = "/backstage/role-module.html")
    public String getBackstageRoleModule(Long roleId, ModelMap modelMap) {

        if (roleId != null) {
            iBackstageService.getBackstageRoleModule(roleId, modelMap);
        }
        return "backstage/role-module";
    }

    //交易管理

    //内容管理

}
