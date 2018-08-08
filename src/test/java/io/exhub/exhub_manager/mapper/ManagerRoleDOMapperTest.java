package io.exhub.exhub_manager.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author
 * @data 2018/8/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerRoleDOMapperTest {

    @Autowired
    private ManagerRoleDOMapper roleMapper;

    @Test
    public void batchInsertRoleModule() {

        List<Long> ids = new ArrayList<>();
        ids.add(2L);
        ids.add(3L);
        ids.add(4L);
        ids.add(5L);
        int result = roleMapper.batchInsertRoleModule(2L, ids);
        System.out.println(result);
    }

    @Test
    public void deleteModulesByRoleId() {

        int result = roleMapper.deleteModulesByRoleId(2L);
        System.out.println(result);
    }
}