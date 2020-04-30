package com.example.controller;

import com.example.entities.CommenResult;
import com.example.entities.Payment;
import com.example.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author rooster
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommenResult commenResult(Payment payment) {
        int result = paymentService.create(payment);
        if (result > 0) {
            return new CommenResult(200, "create success", result);
        } else {
            return new CommenResult(500, "create fail");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommenResult commenResult(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (Objects.nonNull(payment)) {
            return new CommenResult(200, "insert success", payment);
        } else {
            return new CommenResult(500, "insert fail");
        }
    }
}
