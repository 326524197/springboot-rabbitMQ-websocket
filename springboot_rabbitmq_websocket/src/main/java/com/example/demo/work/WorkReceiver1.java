package com.example.demo.work;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "workQueue1")
public class WorkReceiver1 {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("WorkReceiver1: " + msg);
    }
}
