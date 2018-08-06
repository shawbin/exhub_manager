package io.exhub.exhub_manager.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import io.exhub.exhub_manager.common.ResponseCode;
import io.exhub.exhub_manager.common.ServerResponse;
import io.exhub.exhub_manager.pojo.DO.LoginRecordDO;
import io.exhub.exhub_manager.pojo.DO.ManagerUserDO;
import io.exhub.exhub_manager.service.IBackstageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private IBackstageService iBackstageService;

    @Value("${exhubConfig.managerSession}")
    private String managerSession;

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
        return iBackstageService.postLogin(username, password, session);
    }

    /**
     * 判断用户名是否重复
     * @param username
     * @return
     */
    @GetMapping(value = "/user/check/{username}")
    @ResponseBody
    public ServerResponse getLoginCheckUsername(@PathVariable String username) {

        return iBackstageService.getLoginCheckUsername(username);
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
        return "redirect:/templates/login.html";
    }

    /**
     * 账号列表
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/manager_user/list")
    @ResponseBody
    public Map<String, Object> getManagerUserList(Integer page, Integer limit) {

        Page<Object> pageHelper = PageHelper.startPage(page, limit, true);
        List<Map<String, Object>> managerUserList = iBackstageService.getManagerUserList();
        Map<String, Object> data = ImmutableMap.of("count", pageHelper.getTotal(),
                "data", managerUserList,
                "msg", "",
                "code", 0);
        return data;
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
        return iBackstageService.postAssignedAccount(role, username, password);
    }

    /**
     * 删除后台账号
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping(value = "/manager/account/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse deleteManagerAccount(@PathVariable(value = "user_id") Long id) {

        return iBackstageService.deleteManagerAccount(id);
    }

    /**
     * 密码管理-修改密码
     * @param session
     * @return
     */
    @ResponseBody
    @PutMapping(value = "/account/password", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse putAccountPassword(HttpSession session, @RequestBody Map<String, String> params) {

        Object object = session.getAttribute(managerSession);
        if (object == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.SESSION_VERIFY_FAILURE.getCode(), ResponseCode.SESSION_VERIFY_FAILURE.getDesc());
        }
        String password = params.get("password");
        String resetPassword = params.get("resetPassword");
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(resetPassword)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        }
        ManagerUserDO managerUserDO = (ManagerUserDO) object;
        return iBackstageService.putAccountPassword(managerUserDO, password, resetPassword);
    }

}
