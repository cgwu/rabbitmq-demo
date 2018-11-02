package me.danny.rabbitmqdemo.rest;

import lombok.extern.slf4j.Slf4j;
import me.danny.rabbitmqdemo.service.HelloSender;
import me.danny.rabbitmqdemo.vo.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by test5d on 2018/11/2.
 */
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

    @Autowired
    private HelloSender helloSender;

    @GetMapping("/send")
    @ResponseBody
    public String send( String msg) {
        for(int i=0;i<20;i++) {
            helloSender.send(String.valueOf(i) + msg);
        }
        return "test send 测试发送";
    }

    @GetMapping("/send-user")
    @ResponseBody
    public String sendUser()  {
        UserEntity u = new UserEntity(110,"张三","123");
        this.helloSender.send(u);
        return  "send user complete";
    }
}
