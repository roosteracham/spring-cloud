package com.example.service;

import com.example.entities.CommenResult;
import com.example.entities.Payment;
import com.example.service.impl.PaymentFeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "CLOUD-PAYMENT-SERIVCE", fallback = PaymentFeignServiceImpl.class)
public interface PaymentFeignService {

    @GetMapping("/payment/cb/get/{id}")
    public CommenResult getPaymentCB(@PathVariable("id") Long id);

    @GetMapping("/payment/get/{id}")
    CommenResult<Payment> getPayment(@PathVariable("id") Long id);

    @PostMapping("/payment/create")
    CommenResult<Payment> create(@RequestBody Payment payment);

    @GetMapping("/payment/get/to")
    CommenResult getPaymentTomeOut();
}
