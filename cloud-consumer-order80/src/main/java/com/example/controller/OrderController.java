package com.example.controller;

import com.example.entities.CommenResult;
import com.example.entities.Payment;
import com.example.service.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "getPaymentTomeOut_FB")
public class OrderController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/create")
    public CommenResult<Payment> create(Payment payment) {
        return paymentFeignService.create(payment);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommenResult commenResult(@PathVariable("id") Long id) {
        return paymentFeignService.getPayment(id);
    }

    @GetMapping("/payment/get/to")
//    @HystrixCommand(fallbackMethod = "getPaymentTomeOut_FB",  commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
//    })
    @HystrixCommand // 走全局降级
    public CommenResult getPaymentTomeOut() {
        int a = 1/0;
        return paymentFeignService.getPaymentTomeOut();
    }

    public CommenResult getPaymentTomeOut_FB() {
        return new CommenResult(500, "降级方法80");
    }
}
