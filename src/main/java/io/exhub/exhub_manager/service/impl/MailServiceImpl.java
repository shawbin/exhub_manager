package io.exhub.exhub_manager.service.impl;

import com.google.common.collect.ImmutableMap;
import io.exhub.exhub_manager.service.IMailService;
import io.exhub.exhub_manager.util.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class MailServiceImpl implements IMailService {

    @Autowired
    private AsyncService asyncService;
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${exhubConfig.identityAuthSuccess}")
    private String identityAuthSuccess;
    @Value("${exhubConfig.identityAuthFail}")
    private String identityAuthFail;
    @Value("${exhubConfig.fromEmail}")
    private String fromEmail;

    private Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    /**
     * 谷歌身份认证绑定成功邮件
     * @param email
     * @return
     */
    @Override
    public boolean identityAuthSuccess(String email) {

        //发送激活邮件
        Map<String, Object> params = ImmutableMap.of("username", email);
        return sendTemplateEmail(identityAuthSuccess, "【Bilala】身份认证通知", email, params, fromEmail);
    }

    /**
     * 谷歌身份认证绑定成功邮件
     * @param email
     * @return
     */
    @Override
    public boolean identityAuthFail(String email) {

        //发送激活邮件
        Map<String, Object> params = ImmutableMap.of("username", email);
        return sendTemplateEmail(identityAuthFail, "【Bilala】身份认证通知", email, params, fromEmail);
    }

    /**
     * 发送模板邮件
     *
     * @param template html模板
     * @param title    邮件标题
     * @param email    接收者邮箱
     * @param params   发送模板内容 参数为/templates/*.html里的需要的参数
     * @param from     发送者邮箱
     * @return
     */
    @Override
    public boolean sendTemplateEmail(String template, String title, String email, Map<String, Object> params, String from) {

        Context context = new Context();
        context.setVariables(params);
        String content = templateEngine.process(template, context);
        Future<Boolean> future = asyncService.createEmailAndSend(title, email, content, from);
        //这里使用了循环判断，等待获取结果信息
        while (true) {
            try {
                if (future.isDone()) {
                    return future.get();
                }
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                LOGGER.error("{}", e.getStackTrace());
                break;
            } catch (ExecutionException e) {
                LOGGER.error("{}", e.getStackTrace());
                break;
            }
        }
        return true;
    }

}
