package com.expensetracker.microservices.budgetplanservice.repository;

import com.expensetracker.microservices.budgetplanservice.model.Budget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends CrudRepository<Budget,Long> {
}
