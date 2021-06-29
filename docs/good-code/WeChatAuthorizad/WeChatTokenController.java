package com.minghao.channel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @program:minghao-cloud
 * @ClassName:WeChatToken
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-01-26 18:24
 * @Description: 微信token验证 请填写接口配置信息，此信息需要你有自己的服务器资源，填写的URL需要正确响应微信发送的Token验证 注：微信80端口 本地内网穿透测试 网页 url token
 * @Version 1.0
 **/
@Controller
@RequestMapping("/api/channel/wechatToken")
public class WeChatTokenController {

    private static final Logger log= LoggerFactory.getLogger(WeChatTokenController.class);

    public static final String TOKEN = "wechat_mhyj_token";
    
    /** 
    * @Description: wechar token 验证
    * @Param: [signature 微信加密签名, timestamp 时间戳, nonce 随机数, echostr 随机字符串, response]
    * @return: [signature, timestamp, nonce, echostr, response]
    * @Author: aodeng
    * @Date: 19-1-26
    */ 
    @RequestMapping("/get")
    public void getToken(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, IOException {
        //处理数据
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        // 将token、timestamp、nonce三个参数进行字典序排序
        String[] params = new String[] { TOKEN, timestamp, nonce };
        Arrays.sort(params);

        // 将三个参数字符串拼接成一个字符串进行sha1加密
        String clearText = params[0] + params[1] + params[2];
        String algorithm = "SHA-1";
        String sign = new String(
                org.apache.commons.codec.binary.Hex.encodeHex(MessageDigest.getInstance(algorithm).digest((clearText).getBytes()), true));

        // 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if (signature.equals(sign)) {
            response.getWriter().print(echostr);
        }else{
            log.info("wechar token 验证失败");
        }
    }
}
