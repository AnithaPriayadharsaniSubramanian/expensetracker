package com.expensetracker.microservices.expenseservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "expense", catalog = "mysql_demo")
public class Expense {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotNull(message = "{expense.expenseId.notNull}")
    private long expenseId;

    @ManyToOne
    @JoinColumn(name= "category_id")
    @NotNull(message = "{expense.categoryId.notNull}")
    private Category category;

    @NotNull(message = "{expense.amount.notNull}")
    private BigDecimal amount;

/*
    @Future(message = "{expense.date.future}")
*/
    @Past(message = "{expense.date.past}")
    @NotNull(message = "{expense.date.notnull}")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date date;

    @Size(min = 2,max=60,message = "{expense.remark.size}")
    private String remark;

    public long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(long expenseId) {
        this.expenseId = expenseId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
