package com.hope.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-07 18:53
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    /***
     * 全局的统一MultipartException异常处理,当上传文件超过设定最大值，错误信息显示在页面
     * @param e
     * @param redirectAttributes
     * @return
     */
    @ExceptionHandler(MultipartException.class)
    public String multipartException(MultipartException e, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message",e.getCause().getMessage());
        return "redirect:uploadShow";
    }
}