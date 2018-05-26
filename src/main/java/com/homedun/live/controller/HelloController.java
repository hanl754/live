package com.homedun.live.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hanliang.hl
 * @date 2018-04-20 下午10:35
 **/
@RestController
public class HelloController {

    @GetMapping("/")
    public String helloworld() {
        return "Hello World";
    }

    @GetMapping("/hehe")
    public String hehe() {
        return "Hehe?";
    }
}
