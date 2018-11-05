package me.danny.rabbitmqdemo.service;

import lombok.extern.slf4j.Slf4j;
import me.danny.rabbitmqdemo.vo.UserEntity;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by danny on 2018/11/2.
 */
@Component
@Slf4j
public class HelloSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "喂hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

    public void send(String message) {
        String context = "喂hello " + new Date()+": "+message;
//        log.info("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

    public void send(UserEntity u) {
        this.rabbitTemplate.convertAndSend("user", u);
    }

}
