package io.exhub.exhub_manager.controller;

import io.exhub.exhub_manager.pojo.DO.ManagerUserDO;
import io.exhub.exhub_manager.service.IBackstageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

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
        iBackstageService.getModulesMap(managerUser, modelMap);
        return "index";
    }

    /**
     * 用户管理-kyc
     * @return
     */
    @GetMapping(value = "/user/identity.html")
    public String userIdentity() {

        return "user/identity";
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

    //交易管理

    //内容管理

}
