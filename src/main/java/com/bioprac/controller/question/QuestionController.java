package com.bioprac.controller.question;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bioprac.model.question.Question;
import com.bioprac.repository.question.QuestionRepository;
import com.bioprac.util.BiopracResponse;

@RestController
@RequestMapping("/questions")
public class QuestionController {

	private QuestionRepository questionRepository;

	public QuestionController(@Autowired QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
	
	@GetMapping()
	@ResponseBody
	public Iterable<Question> getQuestions() {

		logger.info("In getQuestions()");
		logger.error("Error in getQuestions");
		logger.warn("Warn in getQuestions");
		logger.debug("Debug in getQuestions");
		logger.trace("Trace in getQuestions");

		return questionRepository.findAll();
	}

	@DeleteMapping
	public ResponseEntity<?> deleteQuestions() {

		return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);

	}
	
	@PostMapping()
	public ResponseEntity<?> addQuestion(@Valid @RequestBody Question question) {


		
		questionRepository.save(question);
		
		Map<String, Object> responseMap = new HashMap<>();
		
		responseMap.put("questionId", question.getId());
		
		BiopracResponse biopracResponse = new BiopracResponse(true, "Question added Successfully", responseMap);
		
		return ResponseEntity.ok(biopracResponse);
		
	}

	@GetMapping("/{id}")
	public Question getQuestionById(@PathVariable int id) {

		Optional<Question> optionalQuestion = questionRepository.findById(id);

		return optionalQuestion.orElse(null);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteQuestion(@PathVariable @Min(1) int id) {

		Map<String, Object> responseMap = new HashMap<>();

		Optional<Question> optionalQuestion = questionRepository.findById(id);

		if(optionalQuestion.isPresent()) {
			questionRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}

		String responseMessage = "Question with id " + id + " doesn't exit.";
		responseMap.put("message", responseMessage);
		return ResponseEntity.badRequest().body(responseMap);
		
	}

	@GetMapping("/author/{userName}")
	public Iterable<Question> getAuthorsQuestions(@PathVariable String userName) {
		return questionRepository.findAllByCreatedBy(userName);
	}

	@GetMapping("/category/{category}")
	public Iterable<Question> getQuestionsByCategory(@PathVariable String category) {
		return questionRepository.findAllByCategory(category);
	}

	@GetMapping("/category/{category}/subcategory/{subcategory}")
	public Iterable<Question> getQuestionsByCategoryAndSubcategory(@PathVariable String category, @PathVariable String subcategory) {
		return questionRepository.findAllByCategoryAndSubcategory(category, subcategory);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateQuestion(@PathVariable @Min(1) int id, @Valid @RequestBody Question question) {
		
		Optional<Question> optionalQuestion = questionRepository.findById(id);
		
		Map<String, Object> responseMap = new HashMap<>();
		
		if(optionalQuestion.isPresent()) {
			question.setUpdatedAt(LocalDateTime.now());
			questionRepository.save(question);
			responseMap.put("success", true);
			responseMap.put("successMessage","Question updated");
		} else {
			responseMap.put("success", false);
			responseMap.put("successMessage", "No such question");
		}
		
		BiopracResponse biopracResponse = new BiopracResponse(true, "Question updated Successfully", responseMap);
		
		return ResponseEntity.ok(biopracResponse);
		
	}

}
