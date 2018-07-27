package io.exhub.exhub_manager.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import io.exhub.exhub_manager.common.ResponseCode;
import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.pojo.DO.LoginRecordDO;
import io.exhub.exhub_manager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 内容管理
 * @author
 * @data 2018/7/25
 */
@RequestMapping(value = "/backstage")
@Controller
public class BackstageController {

    @Autowired
    private IUserService iUserService;

    /**
     * 登录
     * @return
     */
    @PostMapping(value = "/user/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ServerResponse postLogin(@RequestBody Map<String, String> params, HttpSession session) {

        String username = params.get("username");
        String password = params.get("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        }
        return iUserService.postLogin(username, password, session);
    }

    /**
     * 判断用户名是否重复
     * @param username
     * @return
     */
    @GetMapping(value = "/user/check/{username}")
    @ResponseBody
    public ServerResponse getLoginCheckUsername(@PathVariable String username) {

        return iUserService.getLoginCheckUsername(username);
    }

    /**
     * 分配账号
     * @param params
     * @return
     */
    @PostMapping(value = "/assigned/account/{role}")
    @ResponseBody
    public ServerResponse postAssignedAccount(@PathVariable Long role, @RequestBody Map<String, String> params) {

        String username = params.get("username");
        String password = params.get("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        }
        return iUserService.postAssignedAccount(role, username, password);
    }

    /**
     * 用户退出
     * @param session
     */
    @GetMapping(value = "/user/logout")
    public String logout(HttpSession session) {

        if (session != null) {
            session.invalidate();
        }
        return "redirect:login";
    }

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
