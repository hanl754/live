package com.homedun.live.message.transport;

import com.homedun.live.utils.RandomUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hanliang.hl
 * @date 2018-06-16 下午11:54
 **/
@Component
// TODO: 2018/6/17 可以换成消息中间件的实现，rocketMQ，rabbitMQ等
public class RoomSubject {
    private Map<WebSocketSession, String> clients = new HashMap<>();

    public void attach(WebSocketSession session) {
        clients.put(session, RandomUtils.random(6));
    }

    public void disAttach(WebSocketSession session) {
        clients.remove(session);
    }

    public void notifyClients(WebSocketMessage message) {
        clients.keySet().parallelStream().forEach(session -> {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public String nameOf(WebSocketSession session) {
        return clients.get(session);
    }
}
