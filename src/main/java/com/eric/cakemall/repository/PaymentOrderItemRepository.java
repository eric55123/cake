package com.eric.cakemall.repository;

import com.eric.cakemall.model.PaymentOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderItemRepository extends JpaRepository<PaymentOrderItem, Integer> {
}
