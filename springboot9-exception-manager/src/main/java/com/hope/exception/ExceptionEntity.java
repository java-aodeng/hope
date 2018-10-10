package com.hope.exception;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-10 14:05
 **/
public class ExceptionEntity {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExceptionEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ExceptionEntity() {
    }
}
