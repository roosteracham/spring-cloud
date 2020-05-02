package com.example.service;

import com.example.entities.CommenResult;
import com.example.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "CLOUD-PAYMENT-SERIVCE")
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    CommenResult<Payment> getPayment(@PathVariable("id") Long id);

    @PostMapping("/payment/create")
    public CommenResult<Payment> create(@RequestBody Payment payment);
}
