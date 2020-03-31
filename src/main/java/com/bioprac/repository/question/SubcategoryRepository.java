package com.bioprac.repository.question;

import com.bioprac.model.question.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {
}
