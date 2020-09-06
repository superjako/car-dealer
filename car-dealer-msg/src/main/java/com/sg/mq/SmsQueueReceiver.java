package com.sg.mq;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "smsQueue")
public class SmsQueueReceiver {

    private static final Logger LOGGER = Logger.getLogger(SmsQueueReceiver.class);

    @Autowired
    //private SMSService smsService;

    @RabbitHandler
    public void process() {
        //LOGGER.info("发送短信|"+sms.get("mobile")+"|"+sms.get("content"));

		//云片短信发送
		//SmsApiUtil.sendSms(sms.get("content"), sms.get("mobile"));

        //国都短信发送
        /*SMSOrder smsOrder = new SMSOrder();
        smsOrder.setContent(sms.get("content"));
        smsOrder.addTarget(sms.get("mobile"));
        try {
            smsService.sendSMS(smsOrder);
        } catch (SMSException e) {
            LOGGER.error("国都短信发送失败|"+sms.get("mobile")+"|"+sms.get("content"));
        }*/
    }

}
