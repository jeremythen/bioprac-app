package com.bioprac.repository;

import com.bioprac.model.ImportantQuestions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportantQuestionsRepository extends JpaRepository<ImportantQuestions, Integer> {

	public Iterable<ImportantQuestions> findAllByCreatedBy(String createdBy);
	
}
