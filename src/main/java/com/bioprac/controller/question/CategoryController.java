package com.bioprac.controller.question;

import javax.validation.Valid;
import com.bioprac.model.question.Category;
import com.bioprac.repository.question.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@Valid Category category) {

        categoryRepository.save(category);

        return ResponseEntity.ok(category);

    }

    @PutMapping("/{id}")
    public ResponseEntity.HeadersBuilder updateCategory(@Valid Category category) {

        categoryRepository.save(category);

        return ResponseEntity.noContent();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity.HeadersBuilder deleteCategory(@PathVariable int id) {

        categoryRepository.deleteById(id);

        return ResponseEntity.noContent();

    }

}
