package com.homedun.live.dao;

import com.homedun.live.domain.Room;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hanliang.hl
 * @date 2018-05-29 下午9:38
 **/
public interface RoomDao {
    Long createRoom(@Param("userId") Long userId, @Param("topic") String topic);

    void modifyRoom(@Param("id") Long id,@Param("title") String title);

    Room queryRoom(@Param("userId") Long userId);

    Room queryByRoomId(@Param("roomId") Long roomId);

    int update(Room room);

    List<Room> selectAllOnlineRooms();
}
