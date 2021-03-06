package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.entities.CommenResult;
import com.example.entities.Payment;
import com.example.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author rooster
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping("/payment/cb/get/{id}")
    public CommenResult getPaymentCB(@PathVariable("id") Long id) {
        return paymentService.getPaymentCB(id);
    }

    @GetMapping("/payment/get/to")
    public CommenResult getPaymentTomeOut() throws InterruptedException {
        return paymentService.getPaymentTomeOut();
    }

    @GetMapping("/payment/discevory")
    public Object getDiscovery() {
        log.info(JSON.toJSONString(discoveryClient));
        return discoveryClient.getInstances("CLOUD-PAYMENT-SERIVCE");
    }

    @PostMapping("/payment/create")
    public CommenResult<Payment> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        if (result > 0) {
            return new CommenResult(200, "create success", result);
        } else {
            return new CommenResult(500, "create fail");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommenResult getPayment(@PathVariable("id") Long id) {
        log.info(port);
        Payment payment = paymentService.getPaymentById(id);
        if (Objects.nonNull(payment)) {
            return new CommenResult(200, "insert success, " + port, payment);
        } else {
            return new CommenResult(500, "insert fail, " + port);
        }
    }
}
