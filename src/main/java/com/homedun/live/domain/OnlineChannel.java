package com.homedun.live.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author hanliang.hl
 * @date 2018-05-29 上午1:16
 **/
@Data
@ToString
public class OnlineChannel {
    private Long roomId;

    private String broadcastTopic;

    private Integer onlineUsers;

    private Date startTime;
}
