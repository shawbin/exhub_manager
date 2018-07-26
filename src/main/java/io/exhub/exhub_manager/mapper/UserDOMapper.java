package io.exhub.exhub_manager.mapper;

import io.exhub.exhub_manager.pojo.DO.UserDO;
import io.exhub.exhub_manager.pojo.DO.UserDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDOMapper extends BaseMapper<UserDO, UserDOExample>{

    /**
     * 通过资产名称和地址来查询用户
     * @param assetName
     * @param address
     * @return
     */
    UserDO getOneByAssetNameAndAddress(@Param("assetName") String assetName,
                                       @Param("address") String address);
}
