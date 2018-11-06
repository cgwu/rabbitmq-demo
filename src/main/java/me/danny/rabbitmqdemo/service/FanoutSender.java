package me.danny.rabbitmqdemo.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 发送消息并有回调
 * https://www.jianshu.com/p/e1258c004314
 */
@Component
public class FanoutSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;

//    @Autowired
//    private AmqpTemplate rabbitTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    public void send() {
        System.out.println("------------------------");
        String msgString = virtualHost + ": fanoutSender :hello i am 张三 :" + publisherConfirms;
        System.out.println(msgString);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("callbackSender UUID: " + correlationData.getId());

        this.rabbitTemplate.convertAndSend("fanoutExchange", "abcd.ee.foo.bar", msgString, correlationData);

//        this.rabbitTemplate.convertAndSend("fanoutExchange", "abcd.ee.foo.bar", msgString, correlationData);
//        System.out.println("开始发送消息 : " + msgString.toLowerCase());
//        String response = rabbitTemplate.convertSendAndReceive("fanoutExchange", "key.1", msgString, correlationData).toString();
//        System.out.println("结束发送消息 : " + msgString.toLowerCase());
//        System.out.println("消费者响应 : " + response + " 消息处理完成");
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息发送成功:" + correlationData);
        } else {
            System.out.println("消息发送失败:" + cause);
        }

//        System.out.println("callbakck confirm: " + correlationData.getId());
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println(message.getMessageProperties().getCorrelationId() + " 发送失败");

    }
}
