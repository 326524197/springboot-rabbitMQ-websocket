package com.example.demo.controller;

import com.example.demo.direct.DirectSender;
import com.example.demo.direct.DirectSender2;
import com.example.demo.fanout.FanoutSender;
import com.example.demo.handler.Sender;
import com.example.demo.topic.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

    @Autowired
    private Sender sender;

    @Autowired
    private DirectSender directSender;
    @Autowired
    private DirectSender2 directSender2;
    @Autowired
    private TopicSender topicSender;
    @Autowired
    private FanoutSender fanoutSender;

    @ResponseBody
    @GetMapping(path = "/send")
    public void senderMsg() {
        directSender.sendDirect();
        directSender2.sendDirect();
    }

    @GetMapping(path = "/sendTopic")
    public void sendTopic() {
        topicSender.send();
    }

    @GetMapping(path = "/sendfanout")
    public void sendfanout() {
        fanoutSender.send();
    }
}
