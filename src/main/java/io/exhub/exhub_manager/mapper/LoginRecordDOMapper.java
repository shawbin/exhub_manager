package io.exhub.exhub_manager.mapper;

import io.exhub.exhub_manager.pojo.DO.LoginRecordDO;
import io.exhub.exhub_manager.pojo.DO.LoginRecordDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 登录操作记录mapper
 * @author
 * @date 2018/7/25
 */
@Mapper
@Component
public interface LoginRecordDOMapper extends BaseMapper<LoginRecordDO, LoginRecordDOExample>{

    /**
     * 根据email查询登录记录
     * @param email
     * @return
     */
    LoginRecordDO getOneLoginByEmail(String email);
}