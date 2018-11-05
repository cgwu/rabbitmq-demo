package me.danny.rabbitmqdemo.service;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by danny on 2018/11/5.
 */
@Component
public class TopicSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msg1 = "I am topic.mesaage msg======";
        System.out.println("sender1 : " + msg1);
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", msg1);

        String msg2 = "I am topic.mesaages msg########";
        System.out.println("sender2 : " + msg2);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", msg2);

        String msg3 = "I am topic.mesaages.foo msg******";
        System.out.println("sender3 : " + msg3);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages.foo", msg3);

        String msg4 = "I am topic.mesaages.foo.verrrrrry.long msg";
        System.out.println("sender4 : " + msg4);
        this.rabbitTemplate.convertAndSend("exchange", "topic.mesaages.foo.verrrrrry.long", msg4);

    }
}
