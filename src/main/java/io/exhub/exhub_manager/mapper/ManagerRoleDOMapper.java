package io.exhub.exhub_manager.mapper;

import io.exhub.exhub_manager.pojo.DO.ManagerRoleDO;
import io.exhub.exhub_manager.pojo.DO.ManagerRoleDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 角色 mapper
 * @author
 * @date 2018/8/2
 */
@Mapper
@Component
public interface ManagerRoleDOMapper extends BaseMapper<ManagerRoleDO, ManagerRoleDOExample>{

}