package com.hope.config;

import com.hope.exception.CustomException;
import com.hope.exception.ExceptionEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-10 14:15
 **/
@RestControllerAdvice //该注解将异常以json格式输出
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    /***
     *定义要普获的异常
     * @param request
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public ExceptionEntity customExceptionEntity(HttpServletRequest request, final Exception e, HttpServletResponse response){
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        CustomException customException=(CustomException)e;
        return new ExceptionEntity(customException.getCode(),customException.getMessage());
    }

    /***
     * 捕获  RuntimeException 异常
     * 如果你觉得在一个 exceptionHandler 通过  if (e instanceof xxxException) 太麻烦
     * 那么你还可以自己写多个不同的 exceptionHandler 处理不同异常
     * @param request
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ExceptionEntity runtimeExceptionEntity(HttpServletRequest request,final Exception e,HttpServletResponse response){
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        RuntimeException runtimeException=(RuntimeException)e;
        return new ExceptionEntity(400,runtimeException.getMessage());
    }

    /***
     * Override ResponseEntityExceptionHandler类中的handleExceptionInternal方法
     * 通用的接口映射异常处理方
     * @param e
     * @param o
     * @param httpHeaders
     * @param httpStatus
     * @param webRequest
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object o, HttpHeaders httpHeaders,
                                                    HttpStatus httpStatus, WebRequest webRequest){
        if (e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException methodArgumentNotValidException=(MethodArgumentNotValidException)e;
            return new ResponseEntity<>(new ExceptionEntity(httpStatus.value(),methodArgumentNotValidException.getBindingResult().getAllErrors().get(0).getDefaultMessage()),httpStatus);
        }
        if(e instanceof MethodArgumentTypeMismatchException){
            MethodArgumentTypeMismatchException methodArgumentTypeMismatchException=(MethodArgumentTypeMismatchException)e;
            logger.error("参数转换失败，方法："+methodArgumentTypeMismatchException.getParameter().getMethod().getName()+",参数："+
            methodArgumentTypeMismatchException.getName()+"，信息："+methodArgumentTypeMismatchException.getLocalizedMessage());
            return new ResponseEntity<>(new ExceptionEntity(httpStatus.value(),"参数转换失败"),httpStatus);
        }
        return new ResponseEntity<>(new ExceptionEntity(httpStatus.value(),"参数转换失败"),httpStatus);
    }
}
