package me.danny.rabbitmqdemo.service;

import me.danny.rabbitmqdemo.vo.UserEntity;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "userQueue")
public class UserReceiver {
    @RabbitHandler
    public void process(UserEntity user) {
        System.out.println("user receive  : " + user.toString());
    }
}
