package com.hope.controller;

import com.hope.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestControllerTest {

    @Autowired
    private EmailService emailService;

    @Autowired
    private TemplateEngine templateEngine;
    @Test
    public void test1(){
        //for(int i=0;i<=2;i++){
            emailService.sendTextEmail("java@aodeng.cc","测试","收到请回复，over！");
        //}
    }
    @Test
    public void test2(){
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        emailService.sendHtmlEmali("java@aodeng.cc","测试",content);
    }
    @Test
    public void test3(){
        emailService.sendAttachmentsEmail("f12974049826@163.com","测试","收到请回复，over！","C:\\Users\\ad182\\Pictures\\image3\\a.jpg");
    }
    @Test
    public void test4(){
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\ad182\\Pictures\\image3\\a.jpg";
        emailService.sendStaticResourcesEmail("f12974049826@163.com","测试",content,imgPath,rscId);
    }
    @Test
    public void test5() {
        //创建邮件正文
        org.thymeleaf.context.Context context = new org.thymeleaf.context.Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        emailService.sendHtmlEmali("f12974049826@163.com","主题：这是模板邮件",emailContent);
    }
}