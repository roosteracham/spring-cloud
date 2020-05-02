package com.example.service;

import com.example.entities.CommenResult;
import com.example.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

    CommenResult getPaymentTomeOut();

    CommenResult getPaymentCB(Long id);
}
