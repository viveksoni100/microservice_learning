package com.vivek.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class CircuitBreakerController {

    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
//    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
//    @Retry(name = "default")
//    @RateLimiter(name = "default")
//    @Bulkhead(name = "default")
    @GetMapping("/sample-api")
    public String sampleApi() {
        log.info("sample-api call received");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        return forEntity.getBody();
    }

    public String hardcodedResponse(Exception e) {
        return "fallback-response";
    }
}
