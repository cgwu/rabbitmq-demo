package me.danny.rabbitmqdemo.rest;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import me.danny.rabbitmqdemo.vo.UserEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by danny on 2018/11/7.
 */
@RestController
@RequestMapping("rest")
@Slf4j
public class RestTemplateDemoController {

    @GetMapping("/caller")
    @ResponseBody
    public String caller() {
        UserEntity u = new UserEntity(110,"张三","123");
        String notify3rdPartyUrl = "http://localhost:8080/rest/callee";
        try {
            RestTemplate rest = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.TEXT_PLAIN.toString());
            HttpEntity<String> formEntity = new HttpEntity<String>(JSON.toJSONString(u), headers);

            String result = rest.postForObject(notify3rdPartyUrl, formEntity, String.class);
            log.info("notify3rdParty result: {}", result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "caller done";
    }

    @GetMapping("/caller2")
    @ResponseBody
    public String caller2() {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("clientFlag", "clientFlag xyz");
        requestEntity.add("xml", "xml data");
        requestEntity.add("verifyData", "md5sum");

        String notify3rdPartyUrl = "http://localhost:8080/rest/callee";
        try {
            RestTemplate rest = new RestTemplate();

            /*
            Resolved [org.springframework.web.HttpMediaTypeNotSupportedException:
            Content type 'application/x-www-form-urlencoded;charset=UTF-8' not supported]
             */
            String result = rest.postForObject(notify3rdPartyUrl, requestEntity, String.class);
            log.info("caller2 notify3rdParty result: {}", result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "caller done";
    }

    /**
     * 被调用者
     * @return
     */
    @PostMapping("/callee")
    @ResponseBody
//    public String callee(@RequestBody UserEntity u)  {
    public String callee(@RequestBody Map<String,String> u) {
        log.info("被调用者接收到的参数: {}", u);
        return "callee done";
    }

}
