package io.exhub.exhub_manager.mapper;

import io.exhub.exhub_manager.pojo.DO.PointRecordDO;
import io.exhub.exhub_manager.pojo.DO.PointRecordDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 积分mapper
 * @author
 * @date 2018/7/27
 */
@Mapper
@Component
public interface PointRecordDOMapper extends BaseMapper<PointRecordDO, PointRecordDOExample>{
}