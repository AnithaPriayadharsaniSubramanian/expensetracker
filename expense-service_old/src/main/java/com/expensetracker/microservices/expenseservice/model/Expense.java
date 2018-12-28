package com.expensetracker.microservices.expenseservice.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "expense", catalog = "mysql_demo")
public class Expense {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long expenseId;

    @OneToOne
    @JoinColumn(name= "categoryId")
    private long categoryId;

    private BigDecimal amount;
    private Date date;
    private String remark;

    public long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(long expenseId) {
        this.expenseId = expenseId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
