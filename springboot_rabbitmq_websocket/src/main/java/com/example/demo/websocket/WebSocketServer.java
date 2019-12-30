package com.example.demo.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;

@ServerEndpoint(value="/webSocket")
@Component
public class WebSocketServer {

    public static Set<WebSocketServer> webSockets = new CopyOnWriteArraySet<WebSocketServer>();

	private Session session;

    private static AtomicLong onlineCount = new AtomicLong(0);

	@OnOpen
	public void onOpen(Session session) throws InterruptedException {
		this.session = session;
        onlineCount.incrementAndGet();
		webSockets.add(this);
        System.out.println("有用户上线，当前在线人数有：" + onlineCount.get());
        this.send("欢迎新用户加入");
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            this.send("这是服务端推送缴费通知：" + i);
        }
	}

	@OnClose
	public void onClose(Session s) {
		webSockets.remove(this);
        onlineCount.decrementAndGet();
        System.out.println("有用户下线，当前在线人数有： " + onlineCount.get());
        this.send("用户离开，欢迎下次再来");
    }

	@OnMessage
	public void onMessage(String msg) throws InterruptedException {
		System.out.println("从客服端接受的消息： "+msg);
	}
	
	public void send(String msg) {
		try {
			this.session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
