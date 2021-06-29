package com.hope.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-07 12:17
 **/
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "Docker ok";
    }
}
