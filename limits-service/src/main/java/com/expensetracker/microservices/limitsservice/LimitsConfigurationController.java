package com.expensetracker.microservices.limitsservice;

import com.expensetracker.microservices.limitsservice.bean.LimitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;
    @GetMapping("/limits")
    public LimitConfiguration retrieveLimits()
    {
        return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
    }
}
