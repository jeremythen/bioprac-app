package com.bioprac.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.bioprac.model.question.Question;
import com.bioprac.repository.question.QuestionRepository;

@DataJpaTest
public class QuestionRepositoryTest {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Test
	void whenSaved_thenFindById() {
		
		Question question = new Question();
		
		question.setQuestion("pregunta test 1");
		question.setCategory("botanica");
		question.setSubcategory("hoja");
		question.setAnswer1("respuesta 1");
		question.setAnswer2("respuesta 2");
		question.setAnswer3("respuesta 3");
		question.setAnswer4("respuesta 4");
		question.setType("selección múltiple");
		question.setCreatedBy("Jeremy");
		question.setCorrectAnswer("respuesta 1");
		question.setExplanation("explicación 1");
		
		questionRepository.save(question);
		
		Assertions.assertThat(questionRepository.findById(100)).isNotNull();
		
	}
	
	@Test
	@Sql("createQuestion.sql")
	void checkIsPresent() {
		
		Optional<Question> optionalQuestion = questionRepository.findById(5);
		
		assertThat(optionalQuestion.isPresent()).isTrue();

	}

	@Test
	@Sql("createQuestion.sql")
	void checkIsAbsent() {
		
		Optional<Question> optionalQuestion = questionRepository.findById(10);
		
		assertThat(optionalQuestion.isPresent()).isFalse();

	}
	
}
