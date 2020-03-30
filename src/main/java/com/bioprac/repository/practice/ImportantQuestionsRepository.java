package com.bioprac.repository.practice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioprac.model.practice.ImportantQuestion;

@Repository
public interface ImportantQuestionsRepository extends JpaRepository<ImportantQuestion, Integer> {

	Iterable<ImportantQuestion> findAllByUserName(String userName);

}
