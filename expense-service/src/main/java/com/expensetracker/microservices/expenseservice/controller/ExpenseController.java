package com.expensetracker.microservices.expenseservice.controller;

import com.expensetracker.microservices.expenseservice.model.Expense;
import com.expensetracker.microservices.expenseservice.repository.CategoryRepository;
import com.expensetracker.microservices.expenseservice.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/expense")
@EnableAutoConfiguration(exclude= DataSourceAutoConfiguration.class)

public class ExpenseController {

    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping(value="/start/{startDate}/end/{endDate}")
    public List<Expense> getAllExpensesBetweenDates(@PathVariable final Date startDate, @PathVariable final Date endDate)
    {
        return expenseRepository.findByDateBetween(startDate,endDate);
    }

    @GetMapping(value="/date/{date}")
    public List<Expense> getAllExpensesForDate(@PathVariable final Date date)
    {
        return expenseRepository.findByDate(date);
    }

    @GetMapping(value="/month/{date}")
    public List<Expense> getAllExpensesForMonth(@PathVariable final String date)
    {
        return expenseRepository.findByMatchMonth(date);
    }

    @PostMapping(value="/add")
    public Expense addExpense(@RequestBody final Expense expense)
    {
        return expenseRepository.save(expense);
    }

    @GetMapping(value = "/category")
    public List<Expense> getAllExpensesForCategory(@PathVariable final String categoryName)
    {
        long categoryId=categoryRepository.findByName(categoryName).getCategoryId();
        return expenseRepository.findByCategoryId(categoryId);

    }
}
