package com.homedun.live.socket;

import com.alibaba.fastjson.JSON;
import com.homedun.live.message.transport.RoomSubject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/**
 * @author hanliang.hl
 * @date 2018-06-17 上午12:07
 **/
@Slf4j
public class ChatRoomSocketHandler extends AbstractWebSocketHandler {

    private RoomSubject roomSubject;

    public ChatRoomSocketHandler(RoomSubject roomSubject) {
        this.roomSubject = roomSubject;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        roomSubject.attach(session);
        log.debug("Opened new session in instance " + this);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
        throws Exception {
        Message msg = new Message(message.getPayload(), roomSubject.nameOf(session));
        TextMessage extMessage = new TextMessage(JSON.toJSONString(msg));
        roomSubject.notifyClients(extMessage);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception)
        throws Exception {
        session.close(CloseStatus.SERVER_ERROR);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        roomSubject.disAttach(session);
    }

    @Data
    class Message {
        private String from;
        private String content;
        private Long timestamp;

        public Message(String content, String from) {
            this.from = from;
            this.content = content;
            this.timestamp = System.currentTimeMillis();
        }
    }
}
