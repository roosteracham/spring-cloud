package com.example.service.impl;

import com.example.dao.PaymentDao;
import com.example.entities.CommenResult;
import com.example.entities.Payment;
import com.example.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getPaymentCB_FB", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public CommenResult getPaymentCB(Long id) {
        if (id < 5) {
            throw new RuntimeException(" little id..");
        }

        return new CommenResult(200, "success, getPaymentCB");
    }

    public CommenResult getPaymentCB_FB(Long id) {
        return new CommenResult(500, "降级方法8001, getPaymentCB_FB, id: " + id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getPaymentTomeOut_FB",  commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public CommenResult getPaymentTomeOut() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new CommenResult(200, "success");
    }

    public CommenResult getPaymentTomeOut_FB() {
        return new CommenResult(500, "降级方法");
    }
}
