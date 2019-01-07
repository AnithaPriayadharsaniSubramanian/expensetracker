package com.expensetracker.microservices.expenseservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "category", catalog = "mysql_demo")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "category_id")
    @NotNull(message = "{category.id.notNull}" )
    private long categoryId;

    @NotNull(message = "{category.name.notNull}")
    @Size(min = 2,max=60,message = "{category.name.size}")
    private String name;

   /* @OneToMany(mappedBy = "category")
    private List<Expense>  expenseList;*/

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }*/
}

