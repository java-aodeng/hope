package com.hope.test;

/**
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-11-08 20:37
 **/
public class Cal {
    private Operation operation;

    public void setOperation(Operation operation){
        this.operation=operation;
    }
    public int doOperation(int number1,int number2){
        return this.operation.doOperation(number1,number2);
    }
}
