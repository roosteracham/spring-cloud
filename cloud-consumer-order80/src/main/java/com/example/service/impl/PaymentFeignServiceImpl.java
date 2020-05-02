package com.example.service.impl;

import com.example.entities.CommenResult;
import com.example.entities.Payment;
import com.example.service.PaymentFeignService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignServiceImpl implements PaymentFeignService {
    @Override
    public CommenResult getPaymentCB(Long id) {
        return new CommenResult<>(500, "error , getPaymentCB");
    }

    @Override
    public CommenResult<Payment> getPayment(Long id) {
        return new CommenResult<>(500, "error , getPayment");
    }

    @Override
    public CommenResult<Payment> create(Payment payment) {
        return new CommenResult<>(500, "error , create");
    }

    @Override
    public CommenResult getPaymentTomeOut() {
        return new CommenResult<>(500, "error , getPaymentTomeOut");
    }
}
