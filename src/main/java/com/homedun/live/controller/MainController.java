package com.homedun.live.controller;

import com.alibaba.druid.util.StringUtils;
import com.homedun.live.dao.RoomDao;
import com.homedun.live.dao.UserDao;
import com.homedun.live.domain.Room;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author hanliang.hl
 * @date 2018-05-29 下午9:13
 **/
@Controller
public class MainController {

    @Resource
    private RoomDao roomDao;
    @Resource
    private UserDao userDao;

    @RequestMapping("/")
    private String index(Map<String, Object> map) {
        List<Room> rooms = roomDao.selectAllOnlineRooms();
        map.put("rooms", rooms);
        return "index";
    }


    @RequestMapping("/room/{id}/{topic}")
    private String room(@PathVariable("id")String id, @PathVariable("topic")String topic, Map<String, Object> map) {
        map.put("topic", topic);
        map.put("roomId", id);
        return "room";
    }

    @RequestMapping("/checkIn")
    @ResponseBody
    private String checkIn(Long roomId, String topic, HttpServletResponse response) {
        Room room = roomDao.queryByRoomId(roomId);
        if(room == null) {
            response.setStatus(403);
            return "roomId not exists";
        }
        if(! StringUtils.equals(room.getTopic(), topic)) {
            response.setStatus(403);
            return "topic deny";
        }
        room.setStatus(1);
        room.setLastBroadcastStartTime(new Date());
        roomDao.update(room);
        return "ok";
    }

    @RequestMapping("/createRoom")
    @ResponseBody
    private Room createRoom(Long userId) {
        String topic = generateTopic();
        roomDao.createRoom(userId, topic);
        return roomDao.queryRoom(userId);
    }

    /**
     * 随机生成topic
     * @return
     */
    private String generateTopic() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
