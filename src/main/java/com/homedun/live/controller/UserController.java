package com.homedun.live.controller;

import com.homedun.live.dao.UserDao;
import com.homedun.live.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hanliang.hl
 * @date 2018-05-29 上午1:43
 **/
@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserDao userDao;

    @PostMapping("create")
    public int createUser(User user) {
        return userDao.addUser(user);
    }
}
