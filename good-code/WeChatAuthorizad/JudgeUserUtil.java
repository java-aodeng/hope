package com.minghao.framework.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @program:minghao-cloud
 * @ClassName:JudgeUserUtil
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-01-24 15:30
 * @Description: 公号校验是否关注工具类
 * @Version 1.0
 **/
public class JudgeUserUtil {

    private static final Logger log= LoggerFactory.getLogger(JudgeUserUtil.class);

    public static Boolean judgeIsFollow(String access_token,String openid){
        //subscribe	用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
        Integer subscribe = null;

        //微信开放接口
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";

        log.info("获取用户url:[{}]",url);

        try {
            //处理请求
            URL urlGet=new URL(url);
            URLConnection rulConnection = urlGet.openConnection();
            HttpURLConnection http = (HttpURLConnection) rulConnection;
            //HttpURLConnection http = (HttpURLConnection)urlGet.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type","application/x-java-serialized-object");//"application/x-www-form-urlencoded"
            http.setDoOutput(true);
            http.setDoInput(true);
            http.connect();

            //处理数据
            InputStream is =http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String msg = new String(jsonBytes,"UTF-8");

            JSONObject jsonObject= JSONUtil.parseObj(msg);
            subscribe = jsonObject.getInt("subscribe");

            //关闭
            is.close();
        }catch (Exception e){
            log.info("公号校验是否关注异常：[{}]",e.getMessage());
            e.printStackTrace();
        }

        return 1==subscribe?true:false;
    }
}
