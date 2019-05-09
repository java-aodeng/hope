package com.hope.service;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-11 13:17
 **/
public interface EmailService {
    void sendTextEmail(String to,String subject,String content);
    void sendHtmlEmali(String to,String subject,String content);
    void sendAttachmentsEmail(String to,String subject,String content,String filePath);
    void sendStaticResourcesEmail(String to,String subject,String content,String rscPath,String rscId);
}
