package io.exhub.exhub_manager.config.filter;

import io.exhub.exhub_manager.common.ResponseCode;
import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.util.JsonUtils;
import io.exhub.exhub_manager.util.TokenFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 校验session是否过期
 * @author
 * @Modified By: 参考原项目
 * 跨域问题解决
 */
@Component
public class SessionValidateFilter extends GenericFilterBean {
    private static final Logger logger = LoggerFactory.getLogger(SessionValidateFilter.class);

    /**
     * 对外放开
     */
    private static final String LOGIN = "/login";

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
        //不验证
        if (uri.startsWith(LOGIN)) {

        } else {
            boolean verifyFlag = verify(request, response);
            if (!verifyFlag) {
                return;
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 验证session token
     *
     * @param request
     * @param response
     * @return true成功  false失败
     * @throws IOException
     */
    private boolean verify(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //判断用户是否发token
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            ServerResponse serverResponse = ServerResponse.createByErrorCodeMessage(ResponseCode.TOKEN_NOT_EXIST.getCode(), ResponseCode.TOKEN_NOT_EXIST.getDesc());
            response.getWriter().write(JsonUtils.object2json(serverResponse));
            return false;
        }
        //token验证
        Map<String, String> claims = TokenFactory.verifyToken(token);
        if (claims == null) {
            ServerResponse serverResponse = ServerResponse.createByErrorCodeMessage(ResponseCode.TOKEN_VERIFY_FAILURE.getCode(), ResponseCode.TOKEN_VERIFY_FAILURE.getDesc());
            response.getWriter().write(JsonUtils.object2json(serverResponse));
            return false;
        }

        return true;
    }

}
