package com.expensetracker.microservices.expenseservice.controller;

import com.expensetracker.microservices.expenseservice.exception.ExpenseNotFoundException;
import com.expensetracker.microservices.expenseservice.model.Category;
import com.expensetracker.microservices.expenseservice.model.Expense;
import com.expensetracker.microservices.expenseservice.repository.CategoryRepository;
import com.expensetracker.microservices.expenseservice.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/expense")
@EnableAutoConfiguration(exclude= DataSourceAutoConfiguration.class)

public class ExpenseController {

    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping(value="/start/{startDate}/end/{endDate}")
    public ResponseEntity<List<Expense>> getAllExpensesBetweenDates(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final Date startDate,
                                                    @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final Date endDate) {

        List<Expense> expenseList = expenseRepository.findByDateBetween(startDate,endDate);
        if(expenseList.isEmpty())
        {
            throw new ExpenseNotFoundException("No Expenses are spent between "+startDate+" - "+ endDate);
        }
        else {
            return new ResponseEntity<>(expenseList, HttpStatus.OK);
        }
    }

    @GetMapping(value="/date/{date}")
    public ResponseEntity<List<Expense>> getAllExpensesForDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final Date date) {

        List<Expense> expenseList = expenseRepository.findByDate(date);
        if(expenseList.isEmpty())
        {
            throw new ExpenseNotFoundException("No Expenses available for :"+date);
        }
        else {
            return new ResponseEntity<>(expenseList, HttpStatus.OK);
        }
    }

    @GetMapping(value="/month/{date}")
    public List<Expense> getAllExpensesForMonth(@PathVariable final String date)
    {
        return expenseRepository.findByMatchMonth(date);
    }

    @PostMapping(value="/add")
    public ResponseEntity<Expense> addExpense(@Valid @RequestBody final Expense expense)
    {
     Expense expenseObj = expenseRepository.save(expense);
        return new ResponseEntity<>(expenseObj,HttpStatus.OK);
    }

    /*@PostMapping(value="/add")
    public Expense addExpense(@Valid @RequestBody final Expense expense)
    {
        Expense expenseObj = expenseRepository.save(expense);
        return expenseObj;
    }*/

    @GetMapping(value = "/category/{categoryName}")
    public List<Expense> getAllExpensesForCategory(@PathVariable final String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        Optional<Category> categoryOptional = Optional.ofNullable(category);
        if (!categoryOptional.isPresent()) {
            throw new ExpenseNotFoundException("No Expenses available for category : "+categoryName);
        } else {
            return expenseRepository.findByCategory(category);
        }
    }

}
