package io.exhub.exhub_manager.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.exhub.exhub_manager.common.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author
 * @data 2018/8/2
 */
@Component
public class AsyncService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AsyncService.class);

    /**
     * 使用ExHub Info <info@bilala.io>发送邮件到用户邮箱
     * @param title   邮件主题
     * @param email   用户邮箱
     * @param content 邮件内容
     */
    @Async
    public Future<Boolean> createEmailAndSend(String title, String email, String content, String from) {

        LOGGER.info("send mail to " + email);
        HttpResponse<JsonNode> request = null;
        try {
            request = Unirest.post("https://api.mailgun.net/v3/" + "mail.bilala.io" + "/messages")
                    .basicAuth("api", "key-d7e1a40d6cc653b21337b0559ba5c2fa")
                    .field("from", from)
                    .field("to", email)
                    .field("subject", title)
                    .field("vars", "%recipient_email%")
                    .field("html", content).asJson();
            LOGGER.info(request.getBody().toString());
            if (request.getStatus() != ResponseCode.SUCCESS.getCode()) {
                return new AsyncResult<>(false);
            }
        } catch (UnirestException e) {
            LOGGER.warn("HttpResponse<JsonNode> asJson() fail " + e.getMessage());
        }
        return new AsyncResult<>(true);
    }

}
