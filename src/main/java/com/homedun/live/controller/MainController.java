package com.homedun.live.controller;

import com.homedun.live.utils.SystemUtils;
import org.springframework.session.Session;
import org.springframework.session.jdbc.JdbcOperationsSessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Resource
    private JdbcOperationsSessionRepository repository;

    @RequestMapping("")
    private String index(HttpServletRequest request, Map<String, Object> map) {
        HttpSession session = request.getSession();
        String ip = (String) session.getAttribute("ip");
        if(ip == null) {
            session.setAttribute("ip", SystemUtils.getIpAddr(request));
        }
        //map.put("channels", onlineChannels);
        return "index";
    }
}
