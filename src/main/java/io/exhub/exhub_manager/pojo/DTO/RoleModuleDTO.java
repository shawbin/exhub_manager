package io.exhub.exhub_manager.pojo.DTO;

import java.util.List;

/**
 * 角色绑定模块 内部用不做校验
 * @author
 * @data 2018/8/7
 */
public class RoleModuleDTO {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 模块列表
     */
    private List<Long> ids;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "RoleModuleDTO{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", ids=" + ids +
                '}';
    }
}
