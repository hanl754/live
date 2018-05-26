package com.homedun.live.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author hanliang.hl
 * @date 2018-04-20 下午11:29
 **/
@Controller
@RequestMapping("freemarker")
public class FreemarkerController {
    @RequestMapping("hello")
    private String hello(@RequestParam("name") String name, Map<String,Object> map) {
        map.put("msg", name);
        return "hello";
    }
}
