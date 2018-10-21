package com.example.user.controller;

import com.example.user.constant.CookieConstant;
import com.example.user.constant.RedisConstant;
import com.example.user.dataobject.UserInfo;
import com.example.user.enums.ResultEnum;
import com.example.user.enums.RoleEnum;
import com.example.user.service.UserService;
import com.example.user.utils.CookieUtil;
import com.example.user.utils.ResultVOUtil;
import com.example.user.vo.ResultVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/login")
public class LoginControlller {

    @Autowired
    UserService userService;


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping(value = "/buyer")
    public ResultVO buyerInfo(@RequestParam("openid") String openid, HttpServletResponse httpServletResponse) {


        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        //判断角色
        if (!RoleEnum.BUYER.getCode().equals(userInfo.getRole())) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        //设置cookie
        CookieUtil.set(httpServletResponse, CookieConstant.OPENID, openid, CookieConstant.EXPIRE);

        return ResultVOUtil.success();
    }

    @GetMapping(value = "/seller")
    public ResultVO sellerInfo(@RequestParam("openid") String openid,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {

        //判断是否登陆
        Cookie cookie = CookieUtil.get(httpServletRequest,CookieConstant.TOKEN);
        if(cookie != null){
            String tooken_key = String.format(RedisConstant.TOKEN, cookie.getValue());
            String last_openid = stringRedisTemplate.opsForValue().get(tooken_key);
            if(!StringUtils.isEmpty(last_openid)){
                return ResultVOUtil.success();
            }
        }
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        //判断角色
        if (!RoleEnum.SELLER.getCode().equals(userInfo.getRole())) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(
                String.format(RedisConstant.TOKEN, token),
                openid,
                CookieConstant.EXPIRE,
                TimeUnit.SECONDS);

        //设置cookie
        CookieUtil.set(httpServletResponse, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);

        return ResultVOUtil.success();

    }
}
