package com.eric.cakemall.controller;

import com.eric.cakemall.dto.PaymentOrderDTO;
import com.eric.cakemall.service.PaymentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payment_orders")
public class PaymentOrderController {

    @Autowired
    private PaymentOrderService paymentOrderService;

    @PostMapping("/create")
    public ResponseEntity<PaymentOrderDTO> createOrder(@RequestBody PaymentOrderDTO orderDTO) {
        return ResponseEntity.ok(paymentOrderService.createOrder(orderDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentOrderDTO> getOrderById(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentOrderService.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<PaymentOrderDTO>> getAllOrders() {
        return ResponseEntity.ok(paymentOrderService.getAllOrders());
    }

    @PutMapping("/{orderNo}/status")
    public ResponseEntity<Map<String, String>> updateOrderStatus(
            @PathVariable Integer orderNo,
            @RequestBody Map<String, String> request) {

        String status = request.get("status");
        paymentOrderService.updateOrderStatus(orderNo, status);

        Map<String, String> response = new HashMap<>();
        response.put("message", "訂單狀態更新成功");
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        paymentOrderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}
