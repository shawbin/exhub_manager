package io.exhub.exhub_manager.mapper;

import io.exhub.exhub_manager.pojo.DO.IdentityAuthenticationDO;
import io.exhub.exhub_manager.pojo.DO.IdentityAuthenticationDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 身份审核认证mapper
 */
@Mapper
@Component
public interface IdentityAuthenticationDOMapper extends BaseMapper<IdentityAuthenticationDO, IdentityAuthenticationDOExample>{
}