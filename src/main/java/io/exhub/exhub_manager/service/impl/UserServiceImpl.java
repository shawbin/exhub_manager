package io.exhub.exhub_manager.service.impl;

import io.exhub.exhub_manager.mapper.LoginRecordDOMapper;
import io.exhub.exhub_manager.pojo.DO.LoginRecordDO;
import io.exhub.exhub_manager.pojo.DO.LoginRecordDOExample;
import io.exhub.exhub_manager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @data 2018/7/25
 */
@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private LoginRecordDOMapper loginRecordMapper;

    /**
     * 获取登录记录
     * @return
     * @param userId
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
