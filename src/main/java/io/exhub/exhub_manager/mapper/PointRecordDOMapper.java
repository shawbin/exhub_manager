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

    /**
     * 通过userId、type查询注册/推荐人最多一条记录
     * @param userId 被推荐人
     * @param type
     * @return
     */
    PointRecordDO getPointByUserIdType(@Param(value = "userId") Long userId, @Param(value = "type") Byte type);
}