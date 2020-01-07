package com.example.demo.direct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectSender2 {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDirect() {
        for (int i = 1; i < 3; i++) {
            String msg = "DirectSender-2发送的缴费通通知" + i * 100 + "元";
            System.out.println(msg);
            rabbitTemplate.convertAndSend("directExchange2", "rabbit.msg", msg);
        }
    }

}
