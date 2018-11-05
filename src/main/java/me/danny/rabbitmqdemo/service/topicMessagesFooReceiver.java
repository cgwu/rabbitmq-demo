package me.danny.rabbitmqdemo.service;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.messages.foo")
public class topicMessagesFooReceiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println(".* topicMessagesFooReceiver : " +msg);
    }

}