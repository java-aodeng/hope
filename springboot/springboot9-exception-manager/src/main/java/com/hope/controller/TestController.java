package com.hope.controller;

import com.hope.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-10 15:00
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(Integer number){
        if (null == number){
            throw new CustomException(110,"number不能为空，其实是400");
        }
        int i=100/ number;
        return "你输入的数字缩小100倍是："+i;
    }
}
