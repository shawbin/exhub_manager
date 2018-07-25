package io.exhub.exhub_manager.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.pojo.DO.LoginRecordDO;
import io.exhub.exhub_manager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 内容管理
 * @author
 * @data 2018/7/25
 */
@RequestMapping(value = "/manager/user")
@RestController
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 获取登录记录
     * @date 2018/7/25
     */
    @GetMapping(value = "/{user_id}/login/record")
    public ServerResponse getLoginRecord(@PathVariable(value = "user_id") Long userId,
                                         @RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize) {

        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<LoginRecordDO> loginRecordList = iUserService.getLoginRecord(userId);
        Map<String, Object> data = ImmutableMap.of("total", page.getTotal(),
                "loginRecordList", loginRecordList);
        return ServerResponse.createBySuccess(data);
    }
}
