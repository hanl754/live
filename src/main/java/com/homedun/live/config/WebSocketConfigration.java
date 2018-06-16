package com.homedun.live.config;

import com.homedun.live.message.transport.RoomSubject;
import com.homedun.live.socket.ChatRoomSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author hanliang.hl
 * @date 2018-06-17 上午12:04
 **/
@Configuration
@EnableWebSocket
public class WebSocketConfigration implements WebSocketConfigurer {
    @Autowired
    private RoomSubject roomSubject;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatRoomHandler(), "/chatRoom").withSockJS();
    }

    @Bean
    public WebSocketHandler chatRoomHandler() {
        return new ChatRoomSocketHandler(roomSubject);
    }
}
