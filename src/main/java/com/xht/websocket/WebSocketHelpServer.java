package com.xht.websocket;

import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;

@Service
public class WebSocketHelpServer {

    public void onMessage(WebSocketServer webSocketServer, String message) throws IOException {
        webSocketServer.sendMessage(message);
    }
}
