package com.learn_spring.jpa_and_hibernate_v1.controller;

import org.springframework.web.bind.annotation.RestController;

import com.learn_spring.jpa_and_hibernate_v1.configuration.CurrencyServiceConfiguration;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class CurrencyController {

    private CurrencyServiceConfiguration configuration;

    public CurrencyController(CurrencyServiceConfiguration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/currency-service-config")
    public CurrencyServiceConfiguration retrieveCurrencyConfiguration() {
        return configuration;
    }
}
