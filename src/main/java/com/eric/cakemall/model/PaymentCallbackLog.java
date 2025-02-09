package com.eric.cakemall.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_callback_log")
public class PaymentCallbackLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "callback_log_no")
    private Integer callbackLogNo;

    @ManyToOne
    @JoinColumn(name = "payment_order_no", nullable = false)
    private PaymentOrder paymentOrder;

    @Column(name = "trade_no", nullable = false)
    private String tradeNo;

    @Column(name = "rtn_code", nullable = false)
    private String rtnCode;

    @Column(name = "rtn_msg")
    private String rtnMsg;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "callback_data", columnDefinition = "TEXT")
    private String callbackData;

    // Getter & Setter 方法
    public Integer getCallbackLogNo() { return callbackLogNo; }
    public void setCallbackLogNo(Integer callbackLogNo) { this.callbackLogNo = callbackLogNo; }

    public PaymentOrder getPaymentOrder() { return paymentOrder; }
    public void setPaymentOrder(PaymentOrder paymentOrder) { this.paymentOrder = paymentOrder; }

    public String getTradeNo() { return tradeNo; }
    public void setTradeNo(String tradeNo) { this.tradeNo = tradeNo; }

    public String getRtnCode() { return rtnCode; }
    public void setRtnCode(String rtnCode) { this.rtnCode = rtnCode; }

    public String getRtnMsg() { return rtnMsg; }
    public void setRtnMsg(String rtnMsg) { this.rtnMsg = rtnMsg; }

    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    public String getCallbackData() { return callbackData; }
    public void setCallbackData(String callbackData) { this.callbackData = callbackData; }
}
