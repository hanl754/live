package com.homedun.live.service;

import com.homedun.live.dao.RoomDao;
import com.homedun.live.domain.Room;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hanliang.hl
 * @date 2018-05-29 下午9:44
 **/
@Service
public class MainService {
    @Resource
    private RoomDao roomDao;

    /**
     * 列出所有在线的频道
     * @return
     */
    public List<Room> listOnlineChannels() {
        return roomDao.selectAllOnlineRooms();
    }


}
