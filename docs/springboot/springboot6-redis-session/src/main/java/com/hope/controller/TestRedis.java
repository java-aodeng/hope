package com.hope.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-09 10:55
 **/
@RestController
public class TestRedis {

    @RequestMapping("/test")
    String test(HttpSession httpSession){
        UUID uuid=(UUID) httpSession.getAttribute("uuid");
        if(null==uuid){
            uuid=UUID.randomUUID();
        }
        httpSession.setAttribute("uuid",uuid);
        return httpSession.getId();
    }
}