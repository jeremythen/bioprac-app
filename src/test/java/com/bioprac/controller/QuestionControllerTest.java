package com.bioprac.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.security.test.context.support.WithMockUser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import com.bioprac.controller.QuestionControllerTest;
import com.bioprac.model.Question;
import com.bioprac.repository.QuestionRepository;


@WebMvcTest(controllers = QuestionController.class)
public class QuestionControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private QuestionRepository questionRepository;
	
	@MockBean
	private QuestionController questionController;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void getQuestions() throws Exception {
		
		List<Question> questions = new ArrayList<>();
		questions.add(createQuestion());
		when(questionController.getQuestions()).thenReturn(questions);
		
		MvcResult mvcResult = mockMvc.perform(
					get("/questions/getQuestions")
					.content("application/json")
				)
				.andExpect(status().isOk())
				.andReturn();
		
		List<Question> questionsResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(),
				new TypeReference<List<Question>>() {});
		
		Assertions.assertThat(questionsResponse.size()).isEqualTo(1);
		
	}
		
	@WithMockUser("jeremy")
	@Test
	void addQuestion_whenRequiredDataPresent_thenReturns200() throws Exception {
		
		Question question = createQuestion();
		
		mockMvc.perform(
					post("/questions/addQuestion")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(question))
				)
				.andExpect(status().isOk());
		
	}
	
	@WithMockUser("jeremy")
	@Test
	void addQuestion_whenRequiredDataNotPresent_thenBadRequest() throws Exception {
		
		Question question = createQuestion();
		question.setQuestion(null);
		
		String content = objectMapper.writeValueAsString(question);

		mockMvc.perform(
					post("/questions/addQuestion")
					.contentType("application/json")
					.content(content)
				)
				.andExpect(status().isBadRequest());
		
	}
	
	private Question createQuestion() {
		
		Question question = new Question();
		
		question.setQuestion("pregunta test 1");
		question.setCategory("botanica");
		question.setSubcategory("semilla");
		question.setAnswer1("respuesta 1");
		question.setAnswer2("respuesta 2");
		question.setAnswer3("respuesta 3");
		question.setAnswer4("respuesta 4");
		question.setType("selección múltiple");
		question.setCreatedBy("Maria");
		question.setCorrectAnswer("respuesta 1");
		question.setExplanation("explicación 1");
		
		return question;
		
	}

}
