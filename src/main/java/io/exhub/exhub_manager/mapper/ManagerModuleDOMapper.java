package io.exhub.exhub_manager.mapper;

import io.exhub.exhub_manager.pojo.DO.ManagerModuleDO;
import io.exhub.exhub_manager.pojo.DO.ManagerModuleDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色模块 mapper
 * @author
 * @date 2018/8/2
 */
@Mapper
@Component
public interface ManagerModuleDOMapper extends BaseMapper<ManagerModuleDO, ManagerModuleDOExample>{

    /**
     * 根据角色id获取对应的模块列表
     * @param roleId
     * @return
     */
    List<ManagerModuleDO> listModuleByRoleId(Long roleId);
}