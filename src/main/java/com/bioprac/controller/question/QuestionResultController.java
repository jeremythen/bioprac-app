package com.bioprac.controller.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bioprac.model.question.QuestionResult;
import com.bioprac.repository.question.QuestionResultRepository;

@RestController
@RequestMapping("/questions/results")
public class QuestionResultController {
	
	@Autowired
	private QuestionResultRepository questionResultRepository;
	
	@GetMapping("/getResults")
	public Iterable<QuestionResult> getResults() {
		
		return questionResultRepository.findAll();
		
	}
	
	@GetMapping("/getResults/{id}")
	public Map<String, Object> getResults(@PathVariable int id) {
		
		Optional<QuestionResult> optionalQuestionResult = questionResultRepository.findById(id);
		
		Map<String, Object> responseMap = new HashMap<>();
		
		if(optionalQuestionResult.isPresent()) {
			responseMap.put("questionResult", optionalQuestionResult.get());
		} else {
			responseMap.put("questionResult", null);
		}
		
		return responseMap;
		
	}
	
	@GetMapping("/getResultsForUser/{createdBy}")
	public Iterable<QuestionResult> getResults(@PathVariable String createdBy) {
		
		return questionResultRepository.findAllByCreatedBy(createdBy);
		
	}
	
	@PostMapping("/addResult")
	public Map<String, Object> addQuestionResult(@Valid @RequestBody QuestionResult questionResult) {
		
		questionResultRepository.save(questionResult);
		
		Map<String, Object> responseMap = new HashMap<>();
		
		responseMap.put("success", true);
		responseMap.put("questionResultId", questionResult.getId());
		
		return responseMap;
		
	}

}
