package com.example.demo.direct;

import com.example.demo.websocket.WebSocketServer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "direct")
public class DirectReceive3 {

    @RabbitHandler
    public void process(String msg) throws InterruptedException {
        int i = 1 / 0;
        System.out.println("Receive3接受的消息： " + msg);
        Thread.sleep(500);
        //同websocket推送到页面
        for (WebSocketServer webSocketServer : WebSocketServer.webSockets) {
            try {
                synchronized (webSocketServer) {
                    webSocketServer.send(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
