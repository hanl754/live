package com.homedun.live.dao;

import com.homedun.live.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author hanliang.hl
 * @date 2018-05-29 上午1:11
 **/
public interface UserDao {
    /**
     * 增加一个用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 查询用户
     * @param phone
     * @param password
     * @return
     */
    User queryUser(@Param("phone") String phone, @Param("password") String password);
}
