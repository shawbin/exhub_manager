package io.exhub.exhub_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;

/**
 * @author
 * @data 2018/7/25
 */
@Controller
public class StaticController {

    @Autowired
    private TemplateEngine templateEngine;

    @GetMapping(value = "/login")
    public String getIndex() {

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


}
