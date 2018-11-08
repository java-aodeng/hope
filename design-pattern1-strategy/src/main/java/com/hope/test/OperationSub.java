package com.hope.test;

/**
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-11-08 20:36
 **/
public class OperationSub implements Operation {
    @Override
    public int doOperation(int number1, int number2) {
        return number1-number2;
    }
}
