package com.expensetracker.microservices.expenseservice.controller;

import com.expensetracker.microservices.expenseservice.model.Category;
import com.expensetracker.microservices.expenseservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/category")
@EnableAutoConfiguration(exclude= DataSourceAutoConfiguration.class)

public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping(value="/all")
    public List<Category> getAllCategories()
    {
        return  categoryRepository.findAll();
    }

    @GetMapping(value="/{name}")
    public Category getCategory(@PathVariable final String name)
    {
        return  categoryRepository.findByName(name);
    }

    @PostMapping(value="/add")
    public Category addCategory(@RequestBody final Category category)
    {
        categoryRepository.save(category);
        return categoryRepository.findByName(category.getName());
    }


}
