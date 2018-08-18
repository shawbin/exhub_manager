package io.exhub.exhub_manager.mapper;

import io.exhub.exhub_manager.pojo.DO.UserDO;
import io.exhub.exhub_manager.pojo.DO.UserDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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

    /**
     * bilala用户列表
     * @param params
     * @return
     */
    List<UserDO> listUserDO(@Param(value = "params") Map<String, Object> params);
}
