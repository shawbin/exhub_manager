package io.exhub.exhub_manager.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import io.exhub.exhub_manager.common.ResponseCode;
import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.pojo.DO.IdentityAuthenticationDO;
import io.exhub.exhub_manager.pojo.DO.LoginRecordDO;
import io.exhub.exhub_manager.pojo.DO.UserDO;
import io.exhub.exhub_manager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户管理
 * @author
 * @data 2018/7/27
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 身份认证审核申请记录
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/identity/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> postIdentityAuthentication(@RequestBody Map<String, Object> params) {

        Page page = PageHelper.startPage(Integer.parseInt(String.valueOf(params.get("page"))), Integer.parseInt(String.valueOf(params.get("limit"))), true);
        List<IdentityAuthenticationDO> identityList = iUserService.postIdentityAuthentication(params);
        Map<String, Object> data = ImmutableMap.of("count", page.getTotal(),
                "data", identityList,
                "msg", "",
                "code", 0);
        return data;
    }

    /**
     * 身份认证结果
     * @param id
     * @return
     */
    @GetMapping(value = "/identity/{id}")
    public String getIdentityId(@PathVariable Long id, ModelMap modelMap) {

        modelMap.put("identity", iUserService.getIdentityId(id));
        return "user/identity-detail";
    }

    /**
     * 修改审核结果和备注
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/identity/audit/{id}")
    public ServerResponse postIdentityAuditId(@PathVariable Long id, @RequestBody Map<String, Object> params) {

        Object messageObj = params.get("message");
        Object statusObj = params.get("status");
        if (StringUtils.isEmpty(messageObj) || StringUtils.isEmpty(statusObj)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        }
        Byte status = Byte.parseByte(String.valueOf(statusObj));
        String message = String.valueOf(messageObj);
        return iUserService.postIdentityAuditId(id, status, message);
    }

    /**
     * bilala用户列表
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/list")
    public Map<String, Object> postList(@RequestBody Map<String, Object> params) {

        Object page = params.get("page");
        Object limit = params.get("limit");
        //分页
        Page<Object> pageHelper = PageHelper.startPage(Integer.parseInt(String.valueOf(page)), Integer.parseInt(String.valueOf(limit)), true);
        List<UserDO> userDOS = iUserService.postList(params);
        Map<String, Object> data = ImmutableMap.of("count", pageHelper.getTotal(),
                "data", userDOS,
                "msg", "",
                "code", 0);
        return data;
    }

    /**
     * 获取登录记录
     * @date 2018/7/25
     */
    @ResponseBody
    @GetMapping(value = "/{user_id}/login/record")
    public Map<String, Object> getLoginRecord(@PathVariable(value = "user_id") Long userId,
                                              @RequestParam Integer page,
                                              @RequestParam Integer limit) {

        Page<Object> pageHelper = PageHelper.startPage(page, limit);
        List<LoginRecordDO> loginRecordList = iUserService.getLoginRecord(userId);
        Map<String, Object> data = ImmutableMap.of("count", pageHelper.getTotal(),
                "data", loginRecordList,
                "code", 0,
                "msg", "");
        return data;
    }

    /**
     * 关闭/开启google验证
     * @param userId
     * @param flag true 开启 false 关闭
     * @return
     */
    @ResponseBody
    @PutMapping(value = "/google/auth/{user_id}/{flag}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse putGoogleAuth(@PathVariable(value = "user_id") Long userId,
                                        @PathVariable(value = "flag") boolean flag) {

        return iUserService.putGoogleAuth(userId, flag);
    }

    /**
     * 冻结/解冻账户
     * @param userId
     * @param status
     * @return
     */
    @ResponseBody
    @PutMapping(value = "/account/{user_id}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse putAccountStatus(@PathVariable(value = "user_id") Long userId,
                                           @PathVariable(value = "status") boolean status) {

        return iUserService.putAccountStatus(userId, status);
    }

}
