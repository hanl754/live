package com.homedun.live.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.jdbc.JdbcOperationsSessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.util.Map;

/**
 * @author hanliang.hl
 * @date 2018-05-29 下午9:13
 **/
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private JdbcOperationsSessionRepository repository;

    @RequestMapping("")
    private String index(Map<String,Object> map) {
        Session jdbcSession = this.repository.createSession();
        jdbcSession.setAttribute("name", "hanliang");
        //map.put("channels", onlineChannels);
        return "index";
    }
}
