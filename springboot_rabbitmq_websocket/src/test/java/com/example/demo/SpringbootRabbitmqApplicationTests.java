package com.example.demo;

import com.example.demo.fanout.FanoutSender;
import com.example.demo.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.direct.DirectSender;
import com.example.demo.handler.Sender;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRabbitmqApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringbootRabbitmqApplicationTests {
	
	@Autowired
	private Sender sender;
	
	@Autowired
	private DirectSender directSender;

	@Autowired
	private FanoutSender fanoutSender;

	@Autowired
	private TopicSender topicSender;

	@Test
	public void send() {
		sender.send();
	}
	
	@Test
	public void sendDirect() {
		directSender.sendDirect();
	}

	@Test
	public void sendFanout() {
		fanoutSender.send();
	}

	@Test
	public void sendTopic() {
		topicSender.send();
	}

}
