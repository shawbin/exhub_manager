package io.exhub.exhub_manager.controller;

import com.google.common.collect.ImmutableMap;
import io.exhub.exhub_manager.mapper.ManagerModuleDOMapper;
import io.exhub.exhub_manager.mapper.ManagerRoleDOMapper;
import io.exhub.exhub_manager.pojo.DO.ManagerModuleDO;
import io.exhub.exhub_manager.pojo.DO.ManagerRoleDO;
import io.exhub.exhub_manager.pojo.DO.ManagerUserDO;
import io.exhub.exhub_manager.service.IBackstageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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
    @Value("${exhubConfig.headerTem}")
    private String headerTem;
    @Value("${exhubConfig.indexTem}")
    private String index;

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
    @GetMapping(value = "index.html")
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
    @GetMapping(value = "user/identity.html")
    public String userIdentity() {

        return "user/identity";
    }

}
