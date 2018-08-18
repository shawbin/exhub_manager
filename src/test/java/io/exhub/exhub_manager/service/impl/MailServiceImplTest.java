package io.exhub.exhub_manager.service.impl;

import io.exhub.exhub_manager.service.IMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author
 * @data 2018/8/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Autowired
    private IMailService iMailService;

    @Test
    public void identityAuthSuccess() {

        boolean flag1 = iMailService.identityAuthFail("s915781694@163.com");
        boolean flag2 = iMailService.identityAuthSuccess("s915781694@163.com");
        System.out.println(flag1);
        System.out.println(flag2);
    }
}