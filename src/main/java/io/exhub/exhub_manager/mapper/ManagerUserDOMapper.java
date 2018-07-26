package io.exhub.exhub_manager.mapper;

import io.exhub.exhub_manager.pojo.DO.ManagerUserDO;
import io.exhub.exhub_manager.pojo.DO.ManagerUserDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 后台用户 mapper
 * @date 2018/7/26
 * @author
 */
@Mapper
@Component
public interface ManagerUserDOMapper extends BaseMapper<ManagerUserDO, ManagerUserDOExample>{
}