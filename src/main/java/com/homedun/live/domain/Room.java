package com.homedun.live.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author hanliang.hl
 * @date 2018-05-29 上午1:15
 **/
@Data
@ToString
public class Room {
    private Long id;

    private Long owner;

    private String title;

    private Date gmtCreate;

    private Date gmtModified;
}
