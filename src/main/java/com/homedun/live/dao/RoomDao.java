package com.homedun.live.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author hanliang.hl
 * @date 2018-05-29 下午9:38
 **/
public interface RoomDao {
    Long createRoom(@Param("userId") Long userId);

    void modifyRoom(@Param("id") Long id,@Param("title") String title);
}
