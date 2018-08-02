package io.exhub.exhub_manager.mapper;

import io.exhub.exhub_manager.pojo.DO.ManagerModuleDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author
 * @data 2018/8/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerModuleDOMapperTest {

    @Autowired
    private ManagerModuleDOMapper moduleMapper;

    private final static Logger LOGGER = LoggerFactory.getLogger(ManagerModuleDOMapperTest.class);

    @Test
    public void listModuleByRoleId() {

        List<ManagerModuleDO> moduleDOS = moduleMapper.listModuleByRoleId(1L);
        moduleDOS.forEach(module -> {
            LOGGER.info("{}", module.toString());
        });
    }
}