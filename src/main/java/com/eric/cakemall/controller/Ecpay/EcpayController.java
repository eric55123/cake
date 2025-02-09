package com.eric.cakemall.controller.Ecpay;

import com.eric.cakemall.service.EcpayPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/payment")
public class EcpayController {

    private static final Logger logger = Logger.getLogger(EcpayController.class.getName());

    @Autowired
    private EcpayPaymentService paymentService;

    /**
     * 生成付款表單（根據訂單 ID）
     *
     * @param orderId 訂單編號
     * @return 綠界付款表單 HTML
     */
    @GetMapping("/checkout/{orderId}")
    public ResponseEntity<String> checkout(@PathVariable Integer orderId) {
        try {
            logger.info("開始生成訂單付款表單，訂單編號：" + orderId);
            String formHtml = paymentService.generatePaymentFormByOrderId(orderId);
            return ResponseEntity.ok(formHtml);
        } catch (Exception e) {
            logger.severe("生成付款表單失敗：" + e.getMessage());
            return ResponseEntity.badRequest().body("生成付款表單失敗：" + e.getMessage());
        }
    }

    /**
     * 處理綠界回傳結果
     *
     * @param params 綠界回傳的參數
     * @return 綠界 API 的回應字串
     */
    @PostMapping("/return")
    public String paymentReturn(@RequestParam Map<String, String> params) {
        try {
            // 確認參數是否正確
            System.out.println("回傳參數：" + params);

            if (!params.containsKey("RtnCode")) {
                System.err.println("缺少 RtnCode 參數");
                return "0|FAIL";
            }

            if ("1".equals(params.get("RtnCode"))) {
                String tradeNo = params.get("MerchantTradeNo");
                paymentService.updateOrderStatus(tradeNo, "Paid");
                return "1|OK";
            }

            return "0|FAIL";
        } catch (Exception e) {
            e.printStackTrace();
            return "0|FAIL";
        }
    }

}
