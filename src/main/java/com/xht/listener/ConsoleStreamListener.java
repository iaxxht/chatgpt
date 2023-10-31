package com.xht.listener;

import com.xht.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 控制台测试
 * Console Stream Test Listener
 *
 * @author plexpt
 */
@Slf4j
public class ConsoleStreamListener extends AbstractStreamListener {

    WebSocketServer webSocketServer;

    public ConsoleStreamListener() {
    }

    public ConsoleStreamListener(WebSocketServer webSocketServer) {
        this.webSocketServer = webSocketServer;
    }

    @Override
    public void onMsg(String message) throws IOException {
        System.out.print(message);
        webSocketServer.sendMessage(message);
    }

    @Override
    public void onError(Throwable throwable, String response) {

    }

}
