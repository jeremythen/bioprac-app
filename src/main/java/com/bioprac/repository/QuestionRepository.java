package com.bioprac.repository;

import com.bioprac.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	public Iterable<Question> findAllByCategory(String category);
	
}
