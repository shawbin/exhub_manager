package io.exhub.exhub_manager.config.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 用于检查用户是否登录了系统的过滤器
 * @author
 * @data 2018/7/31
 */
public class SessionFilter implements Filter{

    /** 要检查的 session 的名称 */
    private String sessionKey;

    /** 需要排除（不拦截）的URL的正则表达式 */
    private Pattern exceptUrlPattern;

    /** 检查不通过时，转发的URL */
    private String forwardUrl;

    private String login;

    @Override
    public void init(FilterConfig cfg) throws ServletException {

        sessionKey = cfg.getInitParameter("sessionKey");
        String exceptUrlRegex = cfg.getInitParameter("exceptUrlPattern");
        if (!StringUtils.isBlank(exceptUrlRegex)) {
            exceptUrlPattern = Pattern.compile(exceptUrlRegex);
        }
        forwardUrl = cfg.getInitParameter("forwardUrl");
        login = cfg.getInitParameter("login");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 如果 sessionKey 为空，则直接放行
        if (StringUtils.isBlank(sessionKey)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath = request.getServletPath();
        // 如果请求的路径与forwardUrl相同，或请求的路径是排除的URL时，则直接放行
        if (servletPath.startsWith(login) || servletPath.equals(forwardUrl) || exceptUrlPattern.matcher(servletPath).matches()) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //session 为空
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(forwardUrl);
            return;
        }
        Object sessionObj = request.getSession().getAttribute(sessionKey);
        // 如果Session为空，则跳转到指定页面
        if (sessionObj == null) {
            response.sendRedirect(forwardUrl);
            return;
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }


}
