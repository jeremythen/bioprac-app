package com.bioprac.controller.question;

import javax.validation.Valid;
import com.bioprac.model.question.Category;
import com.bioprac.repository.question.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> addCategory(@Valid @RequestBody Category category) {

        categoryRepository.save(category);

        return ResponseEntity.ok(category);

    }

    @PutMapping("/{id}")
    public Category updateCategory(@Valid @RequestBody Category category) {

        categoryRepository.save(category);

        return category;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id) {

        categoryRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}
