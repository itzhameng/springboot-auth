package com.as.auth.controller;

import com.as.auth.common.Result;
import com.as.auth.common.ResultCodeEnum;
import com.as.auth.config.MyException;
import com.as.auth.helper.JwtHelper;
import com.as.auth.helper.MD5;
import com.as.auth.model.system.SysUser;
import com.as.auth.model.vo.LoginVo;
import com.as.auth.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 后台登录登出
 * </p>
 */
@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {


    @Autowired
    private SysUserService sysUserService;


    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo, HttpServletResponse response) {
        SysUser sysUser = sysUserService.getByUsername(loginVo.getUsername());
        if (null == sysUser) {
            throw new MyException(ResultCodeEnum.ACCOUNT_ERROR);
        }
        if (!MD5.encrypt(loginVo.getPassword()).equals(sysUser.getPassword())) {
            throw new MyException(ResultCodeEnum.PASSWORD_ERROR);
        }
        if (sysUser.getStatus().intValue() == 0) {
            throw new MyException(ResultCodeEnum.ACCOUNT_STOP);
        }

        Map<String, Object> map = new HashMap<>();
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
        map.put("token", token);

        response.addHeader("token", token);

        return Result.ok(map);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/info")
    public Result info(String token) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("roles", "[admin]");
//        map.put("name", "admin");
//        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        String username = JwtHelper.getUsername(token);
        Map<String, Object> map = sysUserService.getUserInfo(username);
        return Result.ok(map);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result logout() {
        return Result.ok();
    }

}