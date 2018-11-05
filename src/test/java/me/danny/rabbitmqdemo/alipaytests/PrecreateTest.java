package me.danny.rabbitmqdemo.alipaytests;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *

 *
 * Created by danny on 2018/11/5.
 *
 */
@Slf4j
public class PrecreateTest {

    static final String APP_ID = "2016092000555661";
    static final String SERVER_URL = "https://openapi.alipaydev.com/gateway.do";
//    static final String SERVER_URL = "https://openapi.alipay.com/gateway.do";
    static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCSTXdiC2NncMyvus+ZWJD0njza5gtwh9lg8x9PXEYkK8dXzBDNWn7+AaeZmkkdpKcp8dDkq6I+jFrT14DGAe02F0MzIlBY9l9u6fn5qlm8SBa/HQMyQQOymtOIpsNqzRLq0fk8rFZiIiVZ1Hkn3BMJZhKeCVoj8w/DEKsdhSZdelQVx1EXYFvAf7j15P9871TzZBPYqZOWf5RHcXQuLMMVRv7rzd06gy9lw2M4r/mLSyRjWlse7ChR6KBJsU1fDM3BGGE/GvYCK0h/mkKNmfnYa7YiQ8iYTTk3fLecaGpqkc7ZekunuzhwWiIhiYN2EM7RoB4G/jVBZY+ulClDRh8LAgMBAAECggEBAIYS3fQI0bjrA3mLrlIuevDhzo0gQp2GsWyKgUf+HP1q7U0FYuR+KWDn7UrzvEuhJ9xlkfTtYUsG3vnCRW2wOE3Ytam3qYBNkBmZ7I6jUYcdXVMY6GP2dsgH4REbGnzWoJ53fHvgx205M5eGAt3Hbd6wHyGbGnNVfT1bGiV8bzM70/Z1l3ePDWEseCTwKdYypEH2gyFaD/Vc29gWsJekD/nHRZQBGpI9pn0ph8AWRoK7V1DHcQwRR5W51xUSzVpTo7oxbHFvoR9cAPrw9IuByRJ/Td+Xtq9s5up1Pj5xp8FVvgGbxcDSQJqI8rM/GoWTYa17VwEZJrqdaBeixS0suMECgYEAw31/auSyM7otAC2n8qPu3N5cEviu/6BpuszjsdxMTZv/yFmWfkoPrBOKFuXCn4JhIjd7C7IPAoW+YgY+oYMrBDxZ3/TM9QxyeooUjNrRK3QHysEaNMFIzGWIt6nvGSrbSw7VjZ6vqByVLIvvOe6s/riBj9BYXWRiu6u7FWzbhqECgYEAv5ZgnzOYMkMfrEzHYZnnAeKpnnjUkopBCCLRIyqG7409AMX1t78YVvvU9s/RTDy2WwjyVZy4tqdIxZoINtUE+UNjEWaf0LiyDAvourARWvemETWeZMpFHX5yQFZjAblS0X2hS/TIBeZla1AoxjQQLGtuk8C4tazNR6oowtERQisCgYAbY5PciBongSgY5EyphCpNK6I+zjTGf9UQZPBp1PGj1f1qNpMNMpcrhrlMJ/iynHO7dYZ5xwKvWcmotjNEMbpvJ314ETY+BvZrELl8WpP/wIvu7ksbpRDsAjW7dcWYAxciVvu4kckOhqKn0Rs9RKFcNsGAQrJ+szfaD19wywWewQKBgDPKPsxKcjYEFyfIiY1EwUGtOBTyrOQAI5xDbS2IbXmMQJVY7heBvN6mrOcbPoTRVKo/POMPvo8BoZZpERdhdjq7kKg/iawugVLoVT1CIy7oCyhTykGMvCpA6DmkIJdytYST5WpW/Us7AOeFf8A0irq3P+kJC+VwlP1ubh5bzcKJAoGAIxh7/2y501l/lqgq3TeCudRcuJZdCV+CfqtfYPD9XjoBO6Qctrsf7vQ9Hq6RiiYaxFDW/ZYZzQM7G6SRwDkgnKaEFluS3T2+xtNfTwF0Pl5sEPhJ5IC6N+C2yoOOmRgdRsDpL+Y7kAMRpZQ96gCWF6a+u+Mk81Iim2C2yBDNY0I=";
    static final String CHARSET = "utf-8";
    static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAubpm7Wq171hAJKa8m3BgcUdIELyCDRop8rv1BRFYCKQzrZTcuhn15Hkw0Ohqce/cRZNkXOef/eckKTw+K+l4gg8fkQqCBmWWtSpi7Ne9EUj+9EIByVu0EWDicnMs8Llto9FvctwvYnh5hgbeMKICeqabSTPuWAs+x6yPsm2bS/lYhxZ6EFlrgqIGTAjjr3ka74vuJMIfOQZJ35fLFxH28Kk0dZQE0V0wIqRDegXK/Z/ama88phHNNWJT3le1dMmN+KEeRldnTC1uVztuIuTZEC9JVVq2tDqVtIjuHFwGeonhuxBmHnaQxsnZxBonIJuY3R8zMEYhNkTtEqKFcu/2awIDAQAB";


    /**
     *
     * alipay.trade.precreate(统一收单线下交易预创建)
     * https://docs.open.alipay.com/api_1/alipay.trade.precreate
     *
     * 触发通知：
     * https://www.merchant.com/receive_notify.htm?notify_type=trade_status_sync&notify_id=91722adff935e8cfa58b3aabf4dead6ibe&notify_time=2017-02-16 21:46:15&sign_type=RSA2&sign=WcO+t3D8Kg71dTlKwN7r9PzUOXeaBJwp8/FOuSxcuSkXsoVYxBpsAidprySCjHCjmaglNcjoKJQLJ28/Asl93joTW39FX6i07lXhnbPknezAlwmvPdnQuI01HZsZF9V1i6ggZjBiAd5lG8bZtTxZOJ87ub2i9GuJ3Nr/NUc9VeY=&charge_amount=8.88&charge_flags=bluesea_1&settlement_id=2018101610032004620239146945
     */
    @Test
    public void testPrecate() {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL,
                APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");

        //创建API对应的request类
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101005\"," +
                "    \"seller_id\":\"2088102176486320\"," +     //卖家ID
                "    \"total_amount\":\"88.88\"," +             //总金额 =【打折金额】+【不可打折金额】
                "    \"discountable_amount\":\"8.88\"," +       //可打折金额
                "    \"undiscountable_amount\":\"80\"," +       //不可打折金额
                "    \"buyer_logon_id\":\"15901825620\"," +
                "    \"subject\":\"我的宝贝商品\"," +
                "    \"store_id\":\"NJ_001\"" +
                "    }");
        request.setNotifyUrl("http://45502596.800.si/alipay/post");   // TODO: 通知路径

        //通过alipayClient调用API，获得对应的response类
        AlipayTradePrecreateResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功!");
        } else {
            System.out.println("调用失败!");
        }
        System.out.print(response.getBody());
        //根据response中的结果继续业务逻辑处理

    }


    @Test
    public void testJsonMap(){
        Map<String,String> m = new HashMap<String,String>(){};
        m.put("a","b");
        m.put("c","d");
        String json = JSON.toJSONString(m);
        log.info(json);
    }


    /**
     * 条码支付接入指引
     * 条码支付：即商家使用扫码设备，扫描用户支付宝钱包上的条码/二维码，完成收款。
     * https://docs.open.alipay.com/194/106039/
     * 调用交易支付接口alipay.trade.pay：
     *
     */
    @Test
    public void testTrade()
    {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");

        //创建API对应的request类
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        request.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101001\"," +
                "    \"scene\":\"bar_code\"," +
                "    \"auth_code\":\"28763443825664394\"," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"store_id\":\"NJ_001\"," +
                "    \"timeout_express\":\"2m\"," +
                "    \"total_amount\":88.88" +
                "  }"); //设置业务参数

        //通过alipayClient调用API，获得对应的response类
        AlipayTradePayResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.print(response.getBody());
        //根据response中的结果继续业务逻辑处理

    }

}
