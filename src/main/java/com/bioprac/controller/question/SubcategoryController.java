package com.bioprac.controller.question;

import javax.validation.Valid;
import com.bioprac.model.question.Subcategory;
import com.bioprac.repository.question.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Subcategory addSubcategory(@Valid @RequestBody Subcategory subcategory) {

        subcategoryRepository.save(subcategory);

        return subcategory;

    }

    @PutMapping("/{id}")
    public Subcategory updateSubcategory(@Valid @RequestBody Subcategory subcategory) {

        subcategoryRepository.save(subcategory);

        return subcategory;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSubcategory(@PathVariable int id) {

        subcategoryRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}
