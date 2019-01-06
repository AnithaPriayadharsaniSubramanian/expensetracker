package com.expensetracker.microservices.budgetplanservice.service;

import com.expensetracker.microservices.budgetplanservice.bean.Expense;
import com.expensetracker.microservices.budgetplanservice.model.Budget;
import com.expensetracker.microservices.budgetplanservice.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class BudgetCalculationService {

    @Autowired
    private BudgetRepository budgetRepository;

    public Budget newBudgetCalculation(List<Expense> expenseList, BigDecimal budget, String date) {

        Optional<List<Expense>> responseList = Optional.ofNullable(expenseList);
        Budget budgetNew = null;

        if (responseList.isPresent()) {
            BigDecimal totalExpense = expenseList.stream().map(Expense::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal savings = budget.subtract(totalExpense);
            budgetNew = new Budget(budget, totalExpense, date, savings);
            budgetRepository.save(budgetNew);
        }
        return budgetNew;
        }

}
