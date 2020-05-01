package com.bl;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyLoadBalancer {

    @Bean
    public IRule getIRule() {
        return new RoundRobinRule();
    }
}
