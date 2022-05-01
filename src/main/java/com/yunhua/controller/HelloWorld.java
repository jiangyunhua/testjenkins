package com.yunhua.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yunhua
 * @version 1.0
 * @description 功能描述
 * @create 2022/4/30 13:36
 */
@RestController
public class HelloWorld {
    @RequestMapping("/hello")
    public String hello(){
        return "hello world，i am jiangyunhua ,jiang tian v3.0";
    }

}
