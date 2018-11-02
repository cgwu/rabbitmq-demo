package me.danny.rabbitmqdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by danny on 2018/11/2.
 */
@Component
@RabbitListener(queues = "hello")
@Slf4j
public class HelloReceiver2 {

    @RabbitHandler
    public void process(String hello) {
        log.info("接收者Receiver #2: " + hello);
    }

}
