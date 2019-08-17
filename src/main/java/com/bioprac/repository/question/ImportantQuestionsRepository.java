package com.bioprac.repository.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioprac.model.question.ImportantQuestions;

@Repository
public interface ImportantQuestionsRepository extends JpaRepository<ImportantQuestions, Integer> {

	public Iterable<ImportantQuestions> findAllByCreatedBy(String createdBy);
	
}
