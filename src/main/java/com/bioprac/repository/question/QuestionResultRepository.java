package com.bioprac.repository.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioprac.model.question.QuestionResult;

@Repository
public interface QuestionResultRepository extends JpaRepository<QuestionResult, Integer> {

	public Iterable<QuestionResult> findAllByCreatedBy(String createdBy);
	
}
