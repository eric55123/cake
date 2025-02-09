package com.eric.cakemall.service;

import com.eric.cakemall.dto.PaymentOrderDTO;

import java.util.List;

public interface PaymentOrderService {
    PaymentOrderDTO createOrder(PaymentOrderDTO orderDTO);
    PaymentOrderDTO getOrderById(Integer orderNo);
    List<PaymentOrderDTO> getAllOrders();
    void updateOrderStatus(Integer orderNo, String status);
    void deleteOrder(Integer orderNo);
}
