package com.expensetracker.microservices.budgetplanservice.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="budget",catalog = "mysql_demo")
public class Budget {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long budgetId;

    private BigDecimal budget;

    private BigDecimal expense;

    private Date monthForDate;

    private BigDecimal savings;

    public Budget() {
    }

    public Budget(BigDecimal budget, Date monthForDate, BigDecimal savings) {
        this.budget = budget;
        this.monthForDate = monthForDate;
        this.savings = savings;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Date getMonthForDate() {
        return monthForDate;
    }

    public void setMonthForDate(Date monthForDate) {
        this.monthForDate = monthForDate;
    }

    public BigDecimal getSavings() {
        return savings;
    }

    public void setSavings(BigDecimal savings) {
        this.savings = savings;
    }
}
