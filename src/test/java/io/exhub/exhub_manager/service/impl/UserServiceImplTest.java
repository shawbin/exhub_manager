package io.exhub.exhub_manager.service.impl;

import io.exhub.exhub_manager.pojo.DO.IdentityAuthenticationDO;
import io.exhub.exhub_manager.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author
 * @data 2018/8/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private IUserService iUserService;

    @Test
    public void countByExample() {

        long count = iUserService.countByExample(IdentityAuthenticationDO.ADUIT_PASS);
        System.out.println(count);
    }
}