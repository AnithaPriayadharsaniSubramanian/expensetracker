package com.expensetracker.microservices.expenseservice.repository;

import com.expensetracker.microservices.expenseservice.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    List<Expense> findByCategoryId(Long categoryId);
    List<Expense> findByDate(Date date);
    List<Expense> findByDateBetween(Date startDate, Date endDate);

    @Query(value = "SELECT * FROM Expense WHERE date Like ?1", nativeQuery = true)
    List<Expense> findByMatchMonth(@Param("date") String date);
}
