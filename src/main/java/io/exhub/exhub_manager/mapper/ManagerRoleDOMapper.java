package io.exhub.exhub_manager.mapper;

import io.exhub.exhub_manager.pojo.DO.ManagerRoleDO;
import io.exhub.exhub_manager.pojo.DO.ManagerRoleDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色 mapper
 * @author
 * @date 2018/8/2
 */
@Mapper
@Component
public interface ManagerRoleDOMapper extends BaseMapper<ManagerRoleDO, ManagerRoleDOExample>{

    /**
     * 批量插入角色模块
     * @param roleId
     * @param ids
     * @return
     */
    int batchInsertRoleModule(@Param(value = "roleId") Long roleId, @Param(value = "ids") List<Long> ids);

    /**
     * 根据roleId删除对应的模块
     * @param roleId
     * @return
     */
    int deleteModulesByRoleId(@Param(value = "roleId") Long roleId);

}