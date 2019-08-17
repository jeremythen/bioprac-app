package com.bioprac.repository.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioprac.model.question.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	public Iterable<Question> findAllByCategory(String category);
	
}
