package com.expensetracker.microservices.budgetplanservice.controller;

import com.expensetracker.microservices.budgetplanservice.model.Budget;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
@RequestMapping("/rest/budget")
public class BudgetController {


    @GetMapping(value="/month/{date}/budget/{budget}")
    public Budget calculateBudget(@PathVariable final BigDecimal budget, @PathVariable final String date)
    {
        RestTemplate restTemplate = new RestTemplate();
        String expenseForMonthURL = "http://localhost:8080/rest/expense/month/{date}";
        ResponseEntity<Budget> response =restTemplate.getForEntity(expenseForMonthURL,Budget.class);

        Budget responseFromExpenseService = response.getBody();
        BigDecimal savings = budget.subtract(responseFromExpenseService.getExpense());
        return new Budget(budget,responseFromExpenseService.getMonthForDate(),savings);
    }

   /* @GetMapping(value="/month/{date}")
    public List<Expense> getAllExpensesForMonth(@PathVariable final String date)
    {
        return expenseRepository.findByMatchMonth(date);
    }
    */
}
