package io.exhub.exhub_manager.service;

import io.exhub.exhub_manager.pojo.DO.LoginRecordDO;

import java.util.List;

/**
 * @author
 * @data 2018/7/25
 */
public interface IUserService {

    /**
     * 获取登录记录
     * @return
     * @param userId
     */
    List<LoginRecordDO> getLoginRecord(Long userId);
}
