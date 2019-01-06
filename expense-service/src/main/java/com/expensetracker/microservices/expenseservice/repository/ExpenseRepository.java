package com.expensetracker.microservices.expenseservice.repository;

import com.expensetracker.microservices.expenseservice.model.Category;
import com.expensetracker.microservices.expenseservice.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    List<Expense> findByCategory(Category category);
    List<Expense> findByDate(Date date);
    List<Expense> findByDateBetween(Date startDate, Date endDate);

    @Query(value = "SELECT * FROM Expense e WHERE e.date LIKE CONCAT('%',:dateStr,'%')", nativeQuery = true)
    List<Expense> findByMatchMonth(@Param("dateStr") String dateStr);
}
