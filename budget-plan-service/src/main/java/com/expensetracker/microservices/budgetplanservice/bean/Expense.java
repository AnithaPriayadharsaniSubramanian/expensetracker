package com.expensetracker.microservices.budgetplanservice.bean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


public class Expense {

    private long expenseId;

    private Category category;

    private BigDecimal amount;

    private Date date;

    private String remark;

    public long getExpenseId() {
        return expenseId;
    }

    public Category getCategory() {
        return category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getRemark() {
        return remark;
    }
}
