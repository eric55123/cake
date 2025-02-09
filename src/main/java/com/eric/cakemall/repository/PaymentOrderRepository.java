package com.eric.cakemall.repository;

import com.eric.cakemall.model.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Integer> {
    Optional<PaymentOrder> findByTradeNo(String tradeNo);

}
