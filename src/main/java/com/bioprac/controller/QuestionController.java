package com.bioprac.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bioprac.model.Question;
import com.bioprac.repository.QuestionRepository;

@RestController
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/getQuestions")
	@ResponseBody
	public Iterable<Question> getQuestions() {
		
		return questionRepository.findAll();
	}
	
	@PostMapping("/addQuestion")
	public Map<String, Object> addQuestion(@Valid @RequestBody Question question) {
		
		questionRepository.save(question);
		
		Map<String, Object> responseMap = new HashMap<>();
		
		responseMap.put("success", true);
		responseMap.put("questionId", question.getId());
		
		return responseMap;
		
	}
	
	@GetMapping("/getQuestions/{category}")
	@ResponseBody
	public Iterable<Question> getQuestions(@PathVariable String category) {
		
		return questionRepository.findAllByCategory(category);
		
	}

	
	@DeleteMapping("/deleteQuestion/{id}")
	public Map<String, Object> deleteQuestion(@PathVariable @Min(1) int id) {
		
		questionRepository.deleteById(id);
		
		Map<String, Object> responseMap = new HashMap<>();
		
		responseMap.put("success", true);
		responseMap.put("deletedQuestionId", id);
		
		return responseMap;
		
	}
	
	
	@PutMapping("/updateQuestion")
	public Map<String, Object> updateQuestion(@Valid @RequestBody Question question) {
		
		Optional<Question> optionalQuestion = questionRepository.findById(question.getId());
		
		Map<String, Object> responseMap = new HashMap<>();
		
		if(optionalQuestion.isPresent()) {
			question.setUpdatedAt(LocalDateTime.now());
			questionRepository.save(question);
			responseMap.put("success", true);
			responseMap.put("successMessage","Question updated");
		}else {
			responseMap.put("success", false);
			responseMap.put("successMessage", "No such question");
		}
		
		return responseMap;
		
	}

}
