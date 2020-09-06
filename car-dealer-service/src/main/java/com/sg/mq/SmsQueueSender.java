package com.sg.mq;

import java.util.Map;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsQueueSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(Map<String, String> sms) {
		this.rabbitTemplate.convertAndSend("smsQueue", sms);
	}

}
