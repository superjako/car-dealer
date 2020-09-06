package com.sg.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue createSmsQueue() {
        return new Queue("smsQueue");
    }

    @Bean
    public Queue createPushQueue() {
        return new Queue("pushQueue");
    }

}
