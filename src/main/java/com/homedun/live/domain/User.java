package com.homedun.live.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author hanliang.hl
 * @date 2018-05-29 上午1:13
 **/
@Data
@ToString
public class User {
    private Long id;

    private String username;

    private String password;

    private String phone;

    private Date gmtCreate;

    private Date gmtModified;
}
