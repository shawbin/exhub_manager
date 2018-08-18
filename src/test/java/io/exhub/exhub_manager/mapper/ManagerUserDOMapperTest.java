package io.exhub.exhub_manager.mapper;

import com.google.common.collect.ImmutableMap;
import io.exhub.exhub_manager.pojo.DO.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author bin
 * @date 2018/8/4 21:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerUserDOMapperTest {

    @Autowired
    private ManagerUserDOMapper managerUserMapper;
    @Autowired
    private UserDOMapper userMapper;

    @Test
    public void listManagerUser() {

        List<Map<String, Object>> data = managerUserMapper.listManagerUser();
        System.out.println(data.toString());
    }

    @Test
    public void listUserDO() {

        Map<String, Object> params = ImmutableMap.of("startTime", "2018-08-01", "endTime", "2018-08-06");
        List<UserDO> userDOS = userMapper.listUserDO(params);
        System.out.println(userDOS);
    }
}