package io.anshily.admin.controller;

import io.anshily.base.core.Result;
import io.anshily.base.utils.weixin.utils.PayCommonUtil;
import io.anshily.base.utils.weixin.utils.WXConfigUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.util.Map;

/**
 * Created by hang on 2018/08/14.
 */
@RestController
@RequestMapping("/wx/pay")
public class WechatController {

    //生成二维码支付
    @GetMapping("/codePayMargin")
    public Result codePayMargin(String balance, HttpServletRequest request) throws Exception {
//        返回生成二维码的路径
        return PayCommonUtil.wxQRPay(balance, request);
    }


    //微信二维码支付回调
    @RequestMapping("/payMarginNotify")
    public void payMarginNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map packageParams = PayCommonUtil.getWXNotifyData(request, WXConfigUtil.API_KEY);
        String resXml = "";
        if (packageParams != null) {
            //------------------------------
            //处理业务开始
            //------------------------------
            if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
                // 这里是支付成功
                //////////执行自己的业务逻辑////////////////
                System.out.println("支付成功");
                Double money = Double.valueOf((String) packageParams.get("total_fee")) / 100;
//                String attach = (String) packageParams.get("attach");
//                JSONObject attachObj = JSONObject.fromObject(attach);
//                String Pid = attachObj.getString("Pid");
//                Integer Pid_identity = Integer.parseInt(attachObj.getString("Pid_identity"));

                //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                resXml = PayCommonUtil.setXML("SUCCESS", "OK");

            } else {
                resXml = PayCommonUtil.setXML("FAIL", "报文为空");
            }
        } else {
            resXml = PayCommonUtil.setXML("FAIL", "报文为空");
        }
        //------------------------------
        //处理业务完毕
        //------------------------------
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }
}
