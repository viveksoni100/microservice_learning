package com.vivek.currencyexchangeservice.controller;

import com.vivek.currencyexchangeservice.entity.CurrencyExchange;
import com.vivek.currencyexchangeservice.repository.CurrencyExchangeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        log.info("retrieveExchangeValue called with {} to {}", from, to);
        Optional<CurrencyExchange> ex = Optional.ofNullable(currencyExchangeRepository.findByFromAndTo(from, to));
        if (ex.isPresent()) {
            ex.get().setEnvironment(environment.getProperty("local.server.port"));
            return ex.get();
        } else {
            throw new RuntimeException("no data found!");
        }
    }

}
