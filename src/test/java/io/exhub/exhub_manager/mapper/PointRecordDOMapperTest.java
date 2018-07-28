package io.exhub.exhub_manager.mapper;

import io.exhub.exhub_manager.pojo.DO.PointRecordDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author
 * @data 2018/7/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PointRecordDOMapperTest {

    @Autowired
    private PointRecordDOMapper pointMapper;

    @Test
    public void getPointByUserIdType() {

        PointRecordDO pointRecordDO = pointMapper.getPointByUserIdType(1002L, (byte)0);
        System.out.println(pointRecordDO);
    }
}