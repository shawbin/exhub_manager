package io.exhub.exhub_manager.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import io.exhub.exhub_manager.common.ResponseCode;
import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.pojo.DO.IdentityAuthenticationDO;
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

    @ResponseBody
    @PostMapping(value = "/identity/authentication/{pageNum}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> postIdentityAuthentication(@PathVariable Integer pageNum, @PathVariable Integer pageSize,
                                                     @RequestBody Map<String, Object> params) {

        Page page = PageHelper.startPage(pageNum, pageSize, true);
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
}
