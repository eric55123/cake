package com.eric.cakemall.service.impl;

import com.eric.cakemall.dto.PaymentOrderDTO;
import com.eric.cakemall.dto.PaymentOrderItemDTO;
import com.eric.cakemall.model.PaymentOrder;
import com.eric.cakemall.model.PaymentOrderItem;
import com.eric.cakemall.model.Product;
import com.eric.cakemall.repository.PaymentOrderRepository;
import com.eric.cakemall.repository.PaymentOrderItemRepository;
import com.eric.cakemall.repository.ProductRepository;
import com.eric.cakemall.service.PaymentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentOrderServiceImpl implements PaymentOrderService {

    @Autowired
    private PaymentOrderRepository paymentOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public PaymentOrderDTO createOrder(PaymentOrderDTO orderDTO) {
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setPaymentStatus("Pending");
        paymentOrder.setPaymentTime(LocalDateTime.now());
        paymentOrder.setTradeNo("Order" + System.currentTimeMillis());

        double totalAmount = 0;

        // 處理商品項目
        if (orderDTO.getProductItems() != null && !orderDTO.getProductItems().isEmpty()) {
            for (PaymentOrderItemDTO itemDTO : orderDTO.getProductItems()) {
                Product product = productRepository.findById(itemDTO.getProductNo())
                        .orElseThrow(() -> new RuntimeException("找不到商品 ID：" + itemDTO.getProductNo()));

                PaymentOrderItem orderItem = new PaymentOrderItem();
                orderItem.setPaymentOrder(paymentOrder);
                orderItem.setProduct(product);
                orderItem.setUnitPrice(product.getProductPrice());
                orderItem.setQuantity(itemDTO.getQuantity());

                // 累加總金額 (單價 * 數量)
                totalAmount += Double.parseDouble(product.getProductPrice()) * itemDTO.getQuantity();

                paymentOrder.getOrderItems().add(orderItem);
            }
        } else {
            throw new IllegalArgumentException("至少需要選擇一項商品");
        }

        paymentOrder.setTotalAmount(String.valueOf(totalAmount));
        PaymentOrder savedOrder = paymentOrderRepository.save(paymentOrder);

        return convertToDTO(savedOrder);
    }


    @Override
    public PaymentOrderDTO getOrderById(Integer paymentOrderNo) {
        PaymentOrder order = paymentOrderRepository.findById(paymentOrderNo)
                .orElseThrow(() -> new RuntimeException("找不到訂單 ID：" + paymentOrderNo));
        return convertToDTO(order);
    }

    @Override
    public List<PaymentOrderDTO> getAllOrders() {
        return paymentOrderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateOrderStatus(Integer orderNo, String status) {
        PaymentOrder order = paymentOrderRepository.findById(orderNo)
                .orElseThrow(() -> new RuntimeException("找不到訂單 ID：" + orderNo));
        order.setPaymentStatus(status);
        paymentOrderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer paymentOrderNo) {
        paymentOrderRepository.deleteById(paymentOrderNo);
    }

    private PaymentOrderDTO convertToDTO(PaymentOrder order) {
        PaymentOrderDTO dto = new PaymentOrderDTO();
        dto.setPaymentOrderNo(order.getPaymentOrderNo());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setPaymentStatus(order.getPaymentStatus());
        dto.setPaymentTime(order.getPaymentTime());

        // 將訂單項目轉換為 DTO，並回傳商品名稱
        dto.setProductItems(order.getOrderItems().stream()
                .map(item -> {
                    PaymentOrderItemDTO itemDTO = new PaymentOrderItemDTO();
                    itemDTO.setProductNo(item.getProduct().getProductNo());
                    itemDTO.setProductName(item.getProduct().getProductName());  // 取得商品名稱
                    itemDTO.setQuantity(item.getQuantity());
                    return itemDTO;
                })
                .collect(Collectors.toList()));

        return dto;
    }
}