package com.expensetracker.microservices.expenseservice.controller;

import com.expensetracker.microservices.expenseservice.exception.CategoryNotFoundException;
import com.expensetracker.microservices.expenseservice.model.Category;
import com.expensetracker.microservices.expenseservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

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


    @GetMapping(value="/name/{name}")
    public ResponseEntity<Optional<Category>> getCategory(@PathVariable final String name)
    {
        Optional<Category> category = Optional.ofNullable(categoryRepository.findByName(name));
        if (!category.isPresent())
            throw new CategoryNotFoundException("Category "+name+" is not available");

        return  new ResponseEntity<>(category,HttpStatus.OK);
    }

    @PostMapping(value="/add")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody final Category category)
    {
        Category categoryObj = categoryRepository.save(category);
        return new ResponseEntity<Category> (categoryObj, HttpStatus.CREATED);
    }

}
