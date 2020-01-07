package com.example.demo.work;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        for (int i = 0; i < 100; i++) {
            String msg = "WorkSender-1:" + i;
            System.out.println(msg);
            rabbitTemplate.convertAndSend("workQueue1", msg);
        }
    }

}
