package io.exhub.exhub_manager.service.impl;

import io.exhub.exhub_manager.common.ResponseCode;
import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.mapper.ManagerModuleDOMapper;
import io.exhub.exhub_manager.mapper.ManagerRoleDOMapper;
import io.exhub.exhub_manager.mapper.ManagerUserDOMapper;
import io.exhub.exhub_manager.pojo.DO.*;
import io.exhub.exhub_manager.service.IBackstageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @data 2018/7/25
 */
@Service
public class BackstageServiceImpl implements IBackstageService {

    @Autowired
    private ManagerUserDOMapper managerUserMapper;
    @Autowired
    private ManagerModuleDOMapper moduleMapper;
    @Autowired
    private ManagerRoleDOMapper roleMapper;

    @Value("${exhubConfig.managerSession}")
    private String managerSession;

    /**
     * manager登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @Override
    public ServerResponse postLogin(String username, String password, HttpSession session) {

        //判断邮箱密码是否正确
        ManagerUserDO managerUser = getManagerUserByUsername(username);
        if (managerUser == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ACCOUNT_NOT_EXIST.getCode(), ResponseCode.ACCOUNT_NOT_EXIST.getDesc());
        }
        if (!StringUtils.equals(password, managerUser.getPassword())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PASSWORD_ERROR.getCode(), ResponseCode.PASSWORD_ERROR.getDesc());
        }
        //将managerUser放入session
        session.setAttribute(managerSession, managerUser);
        //设置session过期时间是30min
        session.setMaxInactiveInterval(30 * 60);
        return ServerResponse.createBySuccess();
    }

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    @Override
    public ManagerUserDO getManagerUserByUsername(String username) {

        ManagerUserDOExample example = new ManagerUserDOExample();
        ManagerUserDOExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andStatusEqualTo(ManagerUserDO.USE);
        List<ManagerUserDO> list = managerUserMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 判断用户名是否重复
     * @param username
     * @return data true 账号不重复 false 账号重复
     */
    @Override
    public ServerResponse getLoginCheckUsername(String username) {

        ManagerUserDO managerUser = getManagerUserByUsername(username);
        if (managerUser == null) {
            return ServerResponse.createBySuccess(true);
        }
        return ServerResponse.createBySuccess(false);
    }

    /**
     * 分配账号
     * @param role 角色
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse postAssignedAccount(Long role, String username, String password) {

        //获取manager_user
        ManagerUserDO managerUserDO = getManagerUserByUsername(username);
        ManagerUserDO managerUser = new ManagerUserDO();
        managerUser.setUsername(username);
        managerUser.setPassword(password);
        managerUser.setRoleId(role);
        if (managerUserDO == null) {
            //插入用户
            managerUserMapper.insertSelective(managerUser);
        }else {
            //更新用户
            managerUser.setId(managerUserDO.getId());
            managerUserMapper.updateByPrimaryKeySelective(managerUser);
        }
        return ServerResponse.createBySuccess();
    }

    /**
     * 获取角色模块列表
     * @param managerUser
     * @param modelMap
     */
    @Override
    public void getModulesMap(ManagerUserDO managerUser, ModelMap modelMap) {

        //查询该用户的角色名
        ManagerRoleDO roleDO = roleMapper.selectByPrimaryKey(managerUser.getRoleId());
        //包装modulelist
        List<Map<String, Object>> moduleList = new ArrayList<>();
        // 获取module列表
        List<ManagerModuleDO> modules = moduleMapper.listModuleByRoleId(managerUser.getRoleId());
        modules.forEach(module -> {
            //查询得到module父列表
            Map<String, Object> moduleMap = new HashMap<>(1<<4);
            if (module.getParentId().equals(ManagerModuleDO.PARENT_ID)) {
                moduleMap.put("parent", module);
                List<ManagerModuleDO> managerModuleDOS = new ArrayList<>();
                modules.forEach(moduleDO -> {
                    if (module.getId().equals(moduleDO.getParentId())) {
                        managerModuleDOS.add(moduleDO);
                    }
                    moduleMap.put("modules", managerModuleDOS);
                });
                moduleList.add(moduleMap);
            }
        });

        modelMap.put("roleName", roleDO == null ? "" : roleDO.getRoleName());
        modelMap.put("moduleList", moduleList);
    }

    /**
     * 获取账号列表
     * @return
     */
    @Override
    public List<Map<String, Object>> getManagerUserList() {

        List<Map<String, Object>> data = managerUserMapper.listManagerUser();
        return data;
    }

    /**
     * 删除后台账号
     * @param id
     * @return
     */
    @Override
    public ServerResponse deleteManagerAccount(Long id) {

        ManagerUserDO managerUser = new ManagerUserDO();
        managerUser.setId(id);
        managerUser.setStatus(ManagerUserDO.UN_USE);
        managerUserMapper.updateByPrimaryKeySelective(managerUser);
        return ServerResponse.createBySuccess();
    }

    /**
     * 获取角色列表
     * @return
     */
    @Override
    public List<ManagerRoleDO> listRole() {

        ManagerRoleDOExample example = new ManagerRoleDOExample();
        ManagerRoleDOExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(true);
        List<ManagerRoleDO> managerRoleDOS = roleMapper.selectByExample(example);
        return managerRoleDOS;
    }

    /**
     * 发送角色列表到页面
     * @param id
     * @param modelMap
     */
    @Override
    public void getBackstageAccountEdit(Long id, ModelMap modelMap) {

        //获取角色列表
        List<ManagerRoleDO> roleList = listRole();
        modelMap.put("roleList", roleList);
        if (id == null) {
            modelMap.put("username", null);
        }else {
            //查询该用户
            ManagerUserDO user = managerUserMapper.selectByPrimaryKey(id);
            modelMap.put("username", user == null ? null : user.getUsername());
        }
    }

    /**
     * 密码管理-修改密码
     * @param managerUser
     * @param password
     * @param resetPassword
     * @return
     */
    @Override
    public ServerResponse putAccountPassword(ManagerUserDO managerUser, String password, String resetPassword) {

        //判断密码是否正确
        if (!StringUtils.equals(managerUser.getPassword(), password)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PASSWORD_ERROR.getCode(), ResponseCode.PASSWORD_ERROR.getDesc());
        }
        //更新密码
        ManagerUserDO user = new ManagerUserDO();
        user.setId(managerUser.getId());
        user.setPassword(resetPassword);
        managerUserMapper.updateByPrimaryKeySelective(user);
        return ServerResponse.createBySuccess();
    }

}
