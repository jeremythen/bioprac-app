package com.bioprac.repository;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.bioprac.model.question.Question;
import com.bioprac.repository.question.QuestionRepository;

@DataJpaTest
public class QuestionRepositoryTest {
	
	@Autowired
	QuestionRepository questionRepository;

	private static final Logger logger = LoggerFactory.getLogger(QuestionRepositoryTest.class);

	@Test
	void whenSaved_thenFindById() {
		
		Question question1 = getAQuestion();
		Question question2 = getAQuestion();

		questionRepository.save(question1);
		questionRepository.save(question2);

		logger.info("questionRepository.findAll " + questionRepository.findAll());

		Assertions.assertThat(questionRepository.findById(question1.getId()).orElseGet(() -> null)).isNotNull();
		Assertions.assertThat(questionRepository.findById(question2.getId()).orElseGet(() -> null)).isNotNull();
		Assertions.assertThat(questionRepository.findById(3).orElseGet(() -> null)).isNull();
		
	}

	private static final Question getAQuestion() {
		Question question = new Question();

		question.setQuestion("pregunta test 11111111");
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

		return question;

	}

	@Sql("createquestion.sql")
	@Test
	void checkIsPresent() {
		
		Optional<Question> optionalQuestion = questionRepository.findById(5);

		logger.info("optionalQuestion " + optionalQuestion);
		
		assertThat(optionalQuestion.isPresent()).isTrue();

	}

	@Test
	@Sql("createquestion.sql")
	void checkIsAbsent() {
		
		Optional<Question> optionalQuestion = questionRepository.findById(10);

		logger.info("optionalQuestion " + optionalQuestion);

		assertThat(optionalQuestion.isPresent()).isFalse();

	}
	
}
