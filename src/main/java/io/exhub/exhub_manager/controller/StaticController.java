package io.exhub.exhub_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.TemplateEngine;

/**
 * @author
 * @data 2018/7/25
 */
@Controller
public class StaticController {

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${exhubConfig.index}")
    private String index;

    @GetMapping(value = "/login")
    public String getIndex() {

        return "login";
    }
}
