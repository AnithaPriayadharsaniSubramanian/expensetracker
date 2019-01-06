package com.expensetracker.microservices.expenseservice.repository;

import com.expensetracker.microservices.expenseservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    <Optional>Category findByName(String name);
}
