package io.exhub.exhub_manager.mapper;

import io.exhub.exhub_manager.pojo.DO.IdentityAuthenticationDO;
import io.exhub.exhub_manager.pojo.DO.IdentityAuthenticationDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 身份审核认证mapper
 */
@Mapper
@Component
public interface IdentityAuthenticationDOMapper extends BaseMapper<IdentityAuthenticationDO, IdentityAuthenticationDOExample>{

    /**
     * 根据条件查询kyc申请
     * @param params
     * @return
     */
    List<IdentityAuthenticationDO> selectIdentityByClause(@Param(value = "params") Map<String, Object> params);
}