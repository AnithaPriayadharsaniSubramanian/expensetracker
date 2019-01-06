package com.expensetracker.microservices.budgetplanservice.controller;

import com.expensetracker.microservices.budgetplanservice.bean.Expense;
import com.expensetracker.microservices.budgetplanservice.model.Budget;
import com.expensetracker.microservices.budgetplanservice.repository.BudgetRepository;
import com.expensetracker.microservices.budgetplanservice.service.BudgetCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest/budget")
public class BudgetController {

    @Autowired
    BudgetCalculationService BudgetCalculationService;

    @Autowired
    RestTemplate restTemplate;

    String expenseForMonthURL = "http://localhost:8080/rest/expense/month/{date}";

    @PostMapping(value="/month/{date}/budget/{budget}")
    public Budget calculateBudget(@PathVariable final BigDecimal budget, @PathVariable final String date)
    {
        Map<String, String> variables = new HashMap<>(1);
        variables.put("date", date);

        ResponseEntity<List<Expense>> response = restTemplate.exchange(expenseForMonthURL,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Expense>>(){},variables);

        List<Expense> expenseList = response.getBody();

       return BudgetCalculationService.newBudgetCalculation(expenseList,budget,date);

    }

}
