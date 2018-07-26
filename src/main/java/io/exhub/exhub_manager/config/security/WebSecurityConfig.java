package io.exhub.exhub_manager.config.security;

import io.exhub.exhub_manager.config.filter.SessionValidateFilter;
import io.exhub.exhub_manager.config.handler.MyLogoutSuccessHandler;
import io.exhub.exhub_manager.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 安全验证
 * @date 2018/7/26
 * @author
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 定制用户认证和授权信息
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /**
         * 对密码采用 BCryptPasswordEncoder 加密方式
         */
        auth.userDetailsService(userService()).passwordEncoder(getPasswordEncoder());
        //auth.authenticationProvider()
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Bean
    public LogoutSuccessHandler getLogoutHandler() {
        return new MyLogoutSuccessHandler();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests().anyRequest().permitAll()
                .and()
                .csrf().disable();
        http.addFilterBefore(new SessionValidateFilter(), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement()
                //session创建策略
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                //允许同一用户拥有多个并发session
                .maximumSessions(1)
                .and()
                //防止篡改session
                .sessionFixation().migrateSession();

    }
}
