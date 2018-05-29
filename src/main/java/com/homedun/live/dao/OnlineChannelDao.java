package com.homedun.live.dao;

import com.homedun.live.domain.OnlineChannel;

import java.util.List;

/**
 * @author hanliang.hl
 * @date 2018-05-29 上午1:22
 **/
public interface OnlineChannelDao {
    int insert(OnlineChannel onlineChannel);

    int delete(OnlineChannel onlineChannel);

    int updateOnlineUsers(Long roomId, Integer delta);

    OnlineChannel select(Long roomId);

    List<OnlineChannel> selectAll();

}
