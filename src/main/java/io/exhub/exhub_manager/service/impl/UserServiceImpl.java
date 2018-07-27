package io.exhub.exhub_manager.service.impl;

import io.exhub.exhub_manager.common.ResponseCode;
import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.mapper.LoginRecordDOMapper;
import io.exhub.exhub_manager.mapper.ManagerUserDOMapper;
import io.exhub.exhub_manager.pojo.DO.LoginRecordDO;
import io.exhub.exhub_manager.pojo.DO.LoginRecordDOExample;
import io.exhub.exhub_manager.pojo.DO.ManagerUserDO;
import io.exhub.exhub_manager.pojo.DO.ManagerUserDOExample;
import io.exhub.exhub_manager.service.IUserService;
import io.exhub.exhub_manager.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author
 * @data 2018/7/25
 */
@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private LoginRecordDOMapper loginRecordMapper;
    @Autowired
    private ManagerUserDOMapper managerUserMapper;

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
        if (!EncryptUtil.matches(password, managerUser.getPassword())) {
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
        managerUser.setPassword(EncryptUtil.encode(password));
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

}
