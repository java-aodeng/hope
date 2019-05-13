package com.minghao.channel.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.minghao.framework.common.response.ResultBean;
import com.minghao.framework.util.JudgeUserUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @program:minghao-cloud
 * @ClassName:WeChatAuthorizedController
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-01-24 09:51
 * @Description: 微信公众号授权:当一个用户点击一个公众号的一个特定链接，判断用户是否关注公众号，若没有关注就跳转到关注界面，已关注就跳转到指定界面
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/channel/wechat")
public class WeChatAuthorizedController {

    private static final Logger log= LoggerFactory.getLogger(PayAliController.class);

    @Value("${wxappid}")
    private String wxappid;// 公众号的唯一标识
    @Value("${redirect_uri}")
    private String redirect_uri;// 授权后重定向的回调链接地址， 请使用 urlEncode 对链接进行处理
    @Value("${response_type}")
    private String response_type;// 返回类型，请填写code
    @Value("${scope}")
    private String scope;//应用授权作用域
    @Value("${state}")
    private String state;//重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
    @Value("${wechat_redirect}")
    private String wechat_redirect;//无论直接打开还是做页面302重定向时候，必须带此参数
    @Value("${secret}")
    private String secret;//公众号的appsecret
    @Value("${grant_type}")
    private String grant_type;//#填写为authorization_code

    /** 
    * @Description: 微信公众号授权发送
    * @Param: [request, response]
    * @return: [request, response]
    * @Author: aodeng
    * @Date: 19-1-24
    */ 
    @RequestMapping("/doPost")
    @ApiOperation(value = "微信公众号授权发送" ,notes ="true:提交成功,false:提交失败")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        //回调地址
        String redirect_uriStr= URLEncoder.encode(redirect_uri,"UTF-8");

        //拼接参数
        StringBuilder url=new StringBuilder("https://open.weixin.qq.com/connect/oauth2/authorize?"
            +"&appid="+wxappid
            +"&redirect_uri="+redirect_uriStr
            +"&response_type="+response_type
            +"&scope="+scope
            +"&state="+state
            +"&wechat_redirect");

        log.info("微信公众号授权发送,[{}]",url);
        //请不要使用get请求，单纯的跳转即可
        response.sendRedirect(url.toString());
    }
    
    /** 
    * @Description: 获取code，再通过处理数据得到openid 判断是否关注
    * @Param: [request, response]
    * @return: [request, response]
    * @Author: aodeng
    * @Date: 19-1-24
    */
    @RequestMapping("/doGet")
    @ApiOperation(value = "微信公众号授权验证" ,notes ="true:提交成功,false:提交失败")
    public ResultBean<String> doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        //获取code
        String code=request.getParameter("code");
        log.info("[code为]：[{}]",code);

        //处理数据
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("appid",wxappid);
        params.put("secret",secret);
        params.put("code",code);
        params.put("grant_type",grant_type);

        //微信开放接口 获取授权access_token 微信这里贼几把坑，两个access_token不一样 他娘的 post
        String wxUrl="https://api.weixin.qq.com/sns/oauth2/access_token";

        //通过code换取网页授权access_token 这里通过code换取的是一个特殊的网页授权access_token,与基础支持中的access_token（该access_token用于调用其他接口）不同
        String result= HttpUtil.post(wxUrl,params);
        JSONObject jsonObject= JSONUtil.parseObj(result);
        String access_token = jsonObject.get("access_token").toString();
        log.info("[授权access_token为]：[{}]",access_token);

        //微信开放接口 获取基础access_token 微信这里贼几把坑，两个access_token不一样 他娘的 get
        String wxUrl2="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + wxappid + "&secret=" + secret;

        //获取基础access_token
        String result2= HttpUtil.get(wxUrl2);
        JSONObject jsonObject2= JSONUtil.parseObj(result2);
        String access_token2 = jsonObject2.get("access_token").toString();
        log.info("[基础access_token]：[{}]",access_token2);

        //获取openid
        String openid = jsonObject.get("openid").toString();
        log.info("[openid为]：[{}]",openid);

        //判断用户是否关注 我这里只封装了subscribe字段，如需获取其他值，可以自己扩展即可
        ResultBean<String> resultBean = new ResultBean<>();
        if (JudgeUserUtil.judgeIsFollow(access_token2,openid)){
            resultBean.setCode(1);
            resultBean.setSuccess(true);
            resultBean.setMsg("微信认证成功");
            log.info("微信认证成功");
            return resultBean;
        }else {
            resultBean.setCode(0);
            resultBean.setSuccess(false);
            resultBean.setMsg("微信认证失败，请关注公众号");
            log.info("微信认证失败，请关注公众号");
            return resultBean;
        }
    }
}