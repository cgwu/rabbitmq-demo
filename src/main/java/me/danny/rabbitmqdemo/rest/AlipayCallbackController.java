package me.danny.rabbitmqdemo.rest;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by danny on 2018/11/5.
 */
@RestController
@RequestMapping("alipay")
@Slf4j
public class AlipayCallbackController {

    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return "Ok";
    }


    /**
     *
     * 当面付异步通知-仅用于扫码支付
     * https://docs.open.alipay.com/194/103296/
     *
     * 支付宝 POST回调参数及数据：
     {
     "gmt_create": "2018-11-05 17:03:07",
     "charset": "utf-8",
     "seller_email": "tlecte7848@sandbox.com",
     "subject": "我的宝贝商品",
     "sign": "Ik1Y6eFBw0oBnHK7420CEwNDAJmP6dwK5CfAPYZIXAXEaZFTTfrZIlHPjAA05sDvRD922lAYLk5gc9HCYz63Ax0TpOIJc7f761eWWzg0ZJTELTqKTJr9ldnyG260u/W4oDq0dHj8azTlEyGhnNbTxGbLpwVQAlQIVwuEseNVdJ1kVuYNz3ndoEdaxAx2+e6etQrn+nvyrICtsktlYIr33D/HHLXEKLShmRpSEAcP0XI64hK/trgcVeUKlmlHvKCS5tqKnIIwv+vvbUZFFAz4DR0quut3y6uWiAz31nVZzkTS5iTiTkBy1PlX68wsazMl1F+uikkaE7cnezSYBUYZmg==",
     "buyer_id": "2088102177065535",
     "invoice_amount": "88.88",     //开票金额
     "notify_id": "116bdc890220a08ae14f3134dcd18fak3d",
     "fund_bill_list": "[{\"amount\":\"88.88\",\"fundChannel\":\"ALIPAYACCOUNT\"}]",
     "notify_type": "trade_status_sync",
     "trade_status": "TRADE_SUCCESS",
     "receipt_amount": "88.88",     //实收金额
     "app_id": "2016092000555661",
     "buyer_pay_amount": "88.88",
     "sign_type": "RSA2",
     "seller_id": "2088102176486320",
     "gmt_payment": "2018-11-05 17:03:14",
     "notify_time": "2018-11-05 17:03:14",
     "version": "1.0",
     "out_trade_no": "20150320010101005",    // 订单ID
     "total_amount": "88.88",       //订单金额
     "trade_no": "2018110522001465530500791679",    // 支付宝交易号
     "auth_app_id": "2016092000555661",
     "buyer_logon_id": "wrg***@sandbox.com",
     "point_amount": "0.00"
     }
     * @param allRequestParams
     */
    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestParam Map<String,String> allRequestParams) {
        log.info("post notified");
        String json = JSON.toJSONString(allRequestParams);
        log.info(json);
        return "success";
    }

    @GetMapping("/notify")
    @ResponseBody
    public void notifyHandler(@RequestParam Map<String,String> allRequestParams) {
        log.info("notified");
        String json = JSON.toJSONString(allRequestParams);
        log.info(json);
    }

}
