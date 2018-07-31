package io.exhub.exhub_manager.config;

import io.exhub.exhub_manager.config.filter.SessionFilter;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置
 * @author
 * @data 2018/7/31
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //添加过滤器
        registration.setFilter(new SessionFilter());
        //设置过滤路径，/*所有路径
        registration.addUrlPatterns("/*");
        //添加默认参数
        registration.addInitParameter("sessionKey", "managerUser");
        registration.addInitParameter("exceptUrlPattern", "/login.html");
        registration.addInitParameter("forwardUrl", "/login.html");
        registration.addInitParameter("login", "/backstage/user/login");
        registration.addInitParameter("staticResource", "/static");
        //设置优先级
        registration.setName("SessionFilter");
        //设置优先级
        registration.setOrder(1);
        return registration;
    }
}
