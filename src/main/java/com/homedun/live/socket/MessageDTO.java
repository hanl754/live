package com.homedun.live.socket;

import lombok.Data;

/**
 * @author hanliang.hl
 * @date 2018-06-17 上午12:34
 **/
@Data
public class MessageDTO {
    private String content;

    private String from;

    private Long timestamp;
}
