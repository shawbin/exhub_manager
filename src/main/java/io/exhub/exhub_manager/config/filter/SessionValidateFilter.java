package io.exhub.exhub_manager.config.filter;

import io.exhub.exhub_manager.common.ResponseCode;
import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 校验session是否过期
 *
 * @author
 * @Modified By: 参考原项目
 */
@Component
public class SessionValidateFilter extends DelegatingFilterProxy implements Filter{

    private static final Logger logger = LoggerFactory.getLogger(SessionValidateFilter.class);

    private static final String LOGIN = "/login";
    private static final String ERROR = "/error";

    @Value("${exhubConfig.managerSession}")
    private String managerSession;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "0");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("XDomainRequestAllowed", "1");
        response.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        logger.debug("请求的uri为：" + uri);
        //获取session
        if (uri.startsWith(LOGIN) || uri.startsWith(ERROR)) {

        }else {
            HttpSession session = request.getSession(false);
            ServerResponse serverResponse;
            if (session == null) {
                serverResponse = ServerResponse.createByErrorCodeMessage(ResponseCode.SESSION_VERIFY_FAILURE.getCode(), ResponseCode.SESSION_VERIFY_FAILURE.getDesc());
                response.getWriter().print(JsonUtils.object2json(serverResponse));
                return;
            }
            Object object = session.getAttribute(managerSession);
            if (object == null) {
                serverResponse = ServerResponse.createByErrorCodeMessage(ResponseCode.SESSION_VERIFY_FAILURE.getCode(), ResponseCode.SESSION_VERIFY_FAILURE.getDesc());
                response.getWriter().print(JsonUtils.object2json(serverResponse));
                return;
            }
        }
        chain.doFilter(request, response);
    }

}
