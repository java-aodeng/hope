package com.hope.service.impl;

import com.hope.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-11 13:25
 **/
@Service
public class EmaliServiceImpl implements EmailService {

    private static final Logger log= LoggerFactory.getLogger(EmaliServiceImpl.class);
    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    /***
     * 发送文本邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendTextEmail(String to, String subject, String content) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        try {
            mailSender.send(mailMessage);
            log.info("[文本邮件发送成功，当前时间]-[{}]",new Date());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /***
     * html邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendHtmlEmali(String to, String subject, String content) {
        MimeMessage message=mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);//true表示创建一个multipart message
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            //发送邮件
            mailSender.send(message);
            log.info("[html邮件发送成功，当前时间]-[{}]",new Date());
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    /***
     * 附件邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    @Override
    public void sendAttachmentsEmail(String to, String subject, String content, String filePath) {
        MimeMessage message=mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);//true表示创建一个multipart message
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            //上传附件
            FileSystemResource resource=new FileSystemResource(new File(filePath));
            String fileName=filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName,resource);
            //发送邮件
            mailSender.send(message);
            log.info("[附件邮件发送成功，当前时间]-[{}]",new Date());
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    /***
     * 嵌入静态资源邮件
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    @Override
    public void sendStaticResourcesEmail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message=mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);//true表示创建一个multipart message
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            //嵌入静态资源
            FileSystemResource resource=new FileSystemResource(new File(rscPath));
            helper.addInline(rscId,resource);

            //发送邮件
            mailSender.send(message);
            log.info("[嵌入静态资源邮件发送成功，当前时间]-[{}]",new Date());
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

}
