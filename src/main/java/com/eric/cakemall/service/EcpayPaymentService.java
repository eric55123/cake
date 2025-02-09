package com.eric.cakemall.service;

import com.eric.cakemall.model.PaymentOrder;
import com.eric.cakemall.repository.PaymentOrderRepository;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

@Service
public class EcpayPaymentService {

    private static final Logger logger = Logger.getLogger(EcpayPaymentService.class.getName());

    @Autowired
    private PaymentOrderRepository paymentOrderRepository;

    private static final AllInOne all = new AllInOne("");

    /**
     * 根據訂單編號生成付款表單
     *
     * @param orderId 訂單編號
     * @return 綠界付款表單 HTML
     */
    public String generatePaymentFormByOrderId(Integer orderId) {
        PaymentOrder order = paymentOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("找不到訂單 ID：" + orderId));

        logger.info("成功找到訂單，生成付款表單，訂單編號：" + orderId);



        AioCheckOutALL checkout = new AioCheckOutALL();
        checkout.setMerchantTradeNo("Order" + System.currentTimeMillis());
        checkout.setMerchantTradeDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        checkout.setTotalAmount(order.getTotalAmount());  // 確保 totalAmount 是整數字串
        checkout.setTradeDesc("商品付款描述");
        checkout.setItemName("多項商品");
        checkout.setReturnURL("http://localhost:8080/api/payment/return");
        checkout.setNeedExtraPaidInfo("N");

        // 在 EcpayPaymentService 中的 generatePaymentFormByOrderId 方法內：
        String totalAmount = order.getTotalAmount();
        int roundedAmount = (int) Double.parseDouble(totalAmount);  // 將 totalAmount 四捨五入轉換為整數
        checkout.setTotalAmount(String.valueOf(roundedAmount));

        return all.aioCheckOut(checkout, null);
    }

    /**
     * 更新訂單狀態
     *
     * @param tradeNo 交易編號
     * @param status  訂單狀態
     */
    public void updateOrderStatus(String tradeNo, String status) {
        PaymentOrder order = paymentOrderRepository.findByTradeNo(tradeNo)
                .orElseThrow(() -> new RuntimeException("找不到對應的訂單，交易編號：" + tradeNo));

        order.setPaymentStatus(status);
        order.setPaymentTime(LocalDateTime.now());

        paymentOrderRepository.save(order);
        logger.info("訂單狀態已更新為：" + status + "，交易編號：" + tradeNo);
    }
}
