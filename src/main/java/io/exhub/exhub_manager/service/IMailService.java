package io.exhub.exhub_manager.service;

import java.util.Map;

public interface IMailService {


    /**
     * 谷歌身份认证绑定成功邮件
     * @param email
     * @return
     */
    boolean identityAuthSuccess(String email);

    /**
     * 谷歌身份认证绑定成功邮件
     * @param email
     * @return
     */
    boolean identityAuthFail(String email);

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
    boolean sendTemplateEmail(String template, String title, String email, Map<String, Object> params, String from);

}
