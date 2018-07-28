package io.exhub.exhub_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author
 * @data 2018/7/25
 */
@Controller
public class StaticController {

    @GetMapping(value = "/login.html")
    public String getLogin() {

        return "login";
    }

    /**
     * 进入后台管理运营平台首页
     * @return
     */
    @GetMapping(value = "manager_index")
    public String managerIndex(){
        return "demo/index";
    }

    /**
     * 进入页面
     * @param pageName
     * @return
     */
    @GetMapping(value = "showThisHtml.html")
    public String managerListKyc(@RequestParam String pageName){
            return "/demo/"+pageName;
    }

    /**
     * 用户管理-kyc
     * @return
     */
    @GetMapping(value = "user/identity.html")
    public String userIdentity() {

        return "user/identity";
    }

    /**
     * 首页
     * @return
     */
    @GetMapping(value = "index.html")
    public String getIndex() {

        return "index";
    }

}
