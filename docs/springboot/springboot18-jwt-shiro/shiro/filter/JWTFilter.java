package com.nihaojewelry.admin.shiro.filter;

import com.nihaojewelry.admin.shiro.JwtToken.JWTToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author:aodeng(低调小熊猫)
 * @blog:（https://aodeng.cc)
 * @Description: TODO
 * @Date: 19-4-29
 **/
public class JWTFilter extends BasicHttpAuthenticationFilter {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTFilter.class);

    /**
     * @Description: 检查token
     * @Param: [request, response, mappedValue]
     * @return: [request, response, mappedValue]
     * @Author: aodeng
     * @Date: 2019/4/29
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        //判断请求头中是否带上 “Token”
        if (isLoginAttempt(request, response)) {
            //登入
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                //token错误
                responseError(response, e.getMessage());
            }
        }
        //如果请求头不存在token，无需检查token，返回true。可能是登陆或者游客状态访问。
        return true;
    }

    /**
     * @Description: 检测header里面是否包含Token字段 也有用Authorization传的token
     * @Param: [request, response]
     * @return: [request, response]
     * @Author: aodeng
     * @Date: 2019/4/29
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        //用header 传token
        //String token = httpServletRequest.getHeader("Token");

        //用Authorization传的token
        String token = httpServletRequest.getHeader("Authorization");

        return token != null;
    }

    /**
     * @Description: 登入 也有用Authorization传的token
     * @Param: [request, response]
     * @return: [request, response]
     * @Author: aodeng
     * @Date: 2019/4/29
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        //用header 传token
        //String token = httpServletRequest.getHeader("Token");

        //用Authorization传的token，需要做截取处理
        String token=httpServletRequest.getHeader("Authorization");
        if (token!=null){
            token=token.substring(6).trim();
        }

        JWTToken jwtToken = new JWTToken(token);
        //提交给realm登入，错误会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        //如果没有抛出异常，代表登入成功，返回true
        return true;
    }

    /**
     * @Description: 对跨域提供支持
     * @Param: [request, response]
     * @return: [request, response]
     * @Author: aodeng
     * @Date: 2019/4/29
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        //跨域首先会发送一个option请求，我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus((HttpStatus.OK.value()));
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * @Description: 将非法请求跳转到 /unauthorized/**
     * @Param: [response, message]
     * @return: [response, message]
     * @Author: aodeng
     * @Date: 2019/4/29
     */
    private void responseError(ServletResponse response, String message) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            //设置编码，否则中文字符在重定向时会变为空字符串
            message = URLEncoder.encode(message, "UTF-8");
            httpServletResponse.sendRedirect("/unauthorized/" + message);
        } catch (IOException e) {
            LOGGER.info("[重定向设置编码异常]-[{}]", e.getMessage());
        }
    }
}
