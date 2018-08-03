package io.exhub.exhub_manager.service.impl;

import com.google.common.collect.ImmutableMap;
import io.exhub.exhub_manager.common.ResponseCode;
import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.mapper.LoginRecordDOMapper;
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
    private LoginRecordDOMapper loginRecordMapper;
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

        ManagerUserDO managerUser = new ManagerUserDO();
        managerUser.setUsername(username);
        managerUser.setPassword(password);
        managerUser.setRoleId(role);
        //保存t_manager_user
        managerUserMapper.insertSelective(managerUser);
        return ServerResponse.createBySuccess();
    }

    /**
     * 获取登录记录
     * @return
     * @param //userId
     */
    @Override
    public List<LoginRecordDO> getLoginRecord(Long userId) {

        LoginRecordDOExample example = new LoginRecordDOExample();
        example.setOrderByClause("id desc");
        LoginRecordDOExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return loginRecordMapper.selectByExample(example);
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


}
