package com.wyl.webdemo.aspect.rabbitmq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import static com.wyl.webdemo.common.constant.RabbitmqQueues.DIRECT_QUEUE;


@Configuration
public class RabbitConfig {

    @Bean
    public Queue Queue(){
        return new Queue(DIRECT_QUEUE, true);
    }

}
