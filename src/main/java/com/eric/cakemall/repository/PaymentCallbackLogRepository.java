package com.eric.cakemall.repository;

import com.eric.cakemall.model.PaymentCallbackLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentCallbackLogRepository extends JpaRepository<PaymentCallbackLog, Integer> {}
