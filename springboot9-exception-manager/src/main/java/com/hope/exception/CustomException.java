package com.hope.exception;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-10 14:10
 **/
public class CustomException extends RuntimeException{
    private static final long serialVersionUID = -8400239090334588012L;
    private int code;

    public CustomException(){
        super();
    }

    public CustomException(int code,String message){
        super(message);
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
