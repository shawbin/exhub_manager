package io.exhub.exhub_manager.pojo.DO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * 后台用户
 * @date 2018/7/26
 * @author
 */
public class ManagerUserDO implements Serializable, UserDetails{
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_manager_user.id
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_manager_user.username
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_manager_user.password
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_manager_user.status
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    private Byte status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_manager_user.role_id
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    private Long roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_manager_user.create_time
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_manager_user.update_time
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_manager_user.id
     *
     * @return the value of t_manager_user.id
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_manager_user.id
     *
     * @param id the value for t_manager_user.id
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_manager_user.username
     *
     * @return the value of t_manager_user.username
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_manager_user.username
     *
     * @param username the value for t_manager_user.username
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_manager_user.password
     *
     * @return the value of t_manager_user.password
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_manager_user.password
     *
     * @param password the value for t_manager_user.password
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_manager_user.status
     *
     * @return the value of t_manager_user.status
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_manager_user.status
     *
     * @param status the value for t_manager_user.status
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_manager_user.role_id
     *
     * @return the value of t_manager_user.role_id
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_manager_user.role_id
     *
     * @param roleId the value for t_manager_user.role_id
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_manager_user.create_time
     *
     * @return the value of t_manager_user.create_time
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_manager_user.create_time
     *
     * @param createTime the value for t_manager_user.create_time
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_manager_user.update_time
     *
     * @return the value of t_manager_user.update_time
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_manager_user.update_time
     *
     * @param updateTime the value for t_manager_user.update_time
     *
     * @mbg.generated Thu Jul 26 20:09:45 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ManagerUserDO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", roleId=" + roleId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}