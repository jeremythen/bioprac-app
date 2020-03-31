package com.bioprac.controller.question;

import javax.validation.Valid;
import com.bioprac.model.question.Subcategory;
import com.bioprac.repository.question.SubcategoryRepository;
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
@RequestMapping("/subcategories")
public class SubcategoryController {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @GetMapping
    public Iterable<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addSubcategory(@Valid Subcategory subcategory) {

        subcategoryRepository.save(subcategory);

        return ResponseEntity.ok(subcategory);

    }

    @PutMapping("/{id}")
    public ResponseEntity.HeadersBuilder updateSubcategory(@Valid Subcategory subcategory) {

        subcategoryRepository.save(subcategory);

        return ResponseEntity.noContent();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity.HeadersBuilder deleteSubcategory(@PathVariable int id) {

        subcategoryRepository.deleteById(id);

        return ResponseEntity.noContent();

    }

}
