package com.hope.shiro.controller;

import cn.hutool.core.date.DateUtil;
import com.hope.shiro.utils.ResponseVo;
import com.hope.shiro.utils.ResultHopeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-22 17:13
 **/
@Controller
public class HopeController {
    private static final Logger log= LoggerFactory.getLogger(HopeController.class);

    @GetMapping("/login")
    public String login() {
        log.info("[---------------登录Get方法]-[{}]",DateUtil.date());
        return "admin/login";
    }

    /*首页*/
    @GetMapping(value = {"/","/admin/index", "/index"})
    public String index(){
        return "admin/index";
    }

    @GetMapping("/error")
    public String error() {
        log.info("[---------------error]-[{}]",DateUtil.date());
        return "admin/error";
    }
    /***
     * 登录
     * @param
     * @param
     * @param
     * @param
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseVo login(HttpServletRequest request, String username, String password
                            ){
        log.info("[---------------登录Post方法]-[{}]",DateUtil.date());
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            //验证
            subject.login(token);
        }catch (LockedAccountException e){
            token.clear();
            log.info("[用户已经被锁定不能登录，请联系管理员！]-[{}]",DateUtil.date());
//            return ResultHopeUtil.error("用户已经被锁定不能登录，请联系管理员！");
            return null;
        }catch (AuthenticationException e){
            token.clear();
            log.info("[用户名或者密码错误]-[{}]",DateUtil.date());
            return null;

//            return ResultHopeUtil.error("用户名或者密码错误！");
        }catch (Exception e){
            e.printStackTrace();
            log.info("[登录内部错误！请联系管理员检查！]-[{}]",DateUtil.date());
            return null;

//            return ResultHopeUtil.error("登录内部错误！请联系管理员检查！");
        }
        log.info("[登录成功]-[{}]",DateUtil.date());
//        return ResultHopeUtil.success("登录成功！");
        //此处不处理代为登录成功，有shiro代为处理
        return ResultHopeUtil.success("登录成功");
    }

}
