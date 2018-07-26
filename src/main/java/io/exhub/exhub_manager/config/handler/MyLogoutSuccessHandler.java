package io.exhub.exhub_manager.config.handler;

import io.exhub.exhub_manager.common.ResponseCode;
import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义的logout功能
 * @date 2018/7/26
 * @author
 */
public class MyLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(MyLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String token = request.getHeader("token");
        //判断token是否存在
        ServerResponse serverResponse;
        if (token != null) {
            serverResponse = ServerResponse.createBySuccess(true);
        }else {
            serverResponse = ServerResponse.createByErrorCodeMessage(ResponseCode.REQUEST_PARAMS_TAMPER.getCode(), ResponseCode.REQUEST_PARAMS_TAMPER.getDesc());
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(JsonUtils.object2json(serverResponse));
        } catch (IOException e) {
            LOGGER.error("{}", e.getStackTrace());
        }finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
        return;
    }
}
