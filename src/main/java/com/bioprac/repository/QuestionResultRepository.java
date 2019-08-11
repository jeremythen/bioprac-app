package com.bioprac.repository;

import com.bioprac.model.QuestionResult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionResultRepository extends JpaRepository<QuestionResult, Integer> {

	public Iterable<QuestionResult> findAllByCreatedBy(String createdBy);
	
}
