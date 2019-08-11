package com.bioprac.controller;

import static org.springframework.data.domain.ExampleMatcher.matchingAny;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bioprac.model.ImportantQuestions;
import com.bioprac.repository.ImportantQuestionsRepository;

@RestController
@RequestMapping("/questions/important")
public class ImportantQuestionsController {
	
	@Autowired
	private ImportantQuestionsRepository importantQuestionRepository;
	
	@GetMapping("/getMyImportantQuestions/{createdBy}")
	public Iterable<ImportantQuestions> getMyImportantQuestions(@PathVariable String createdBy) {
		
		return importantQuestionRepository.findAllByCreatedBy(createdBy);
		
	}
	
	@PostMapping("/addImportantQuestion")
	public Map<String, Object> addImportantQuestion(@Valid @RequestBody ImportantQuestions importantQuestion) {
		
		System.out.format("addImportantQuestion importantQuestion: %s%n", importantQuestion);
		
		importantQuestionRepository.save(importantQuestion);
		
		Map<String, Object> responseMap = new HashMap<>();
		
		responseMap.put("success", "true");
		responseMap.put("importantQuestionID", importantQuestion.getId());
		
		return responseMap;
		
	}
	
	@DeleteMapping("/deleteImportantQuestion/{id}")
	public Map<String, Object> deleteImportantQuestion(@PathVariable int id) {
		
		System.out.format("deleteImportantQuestion id: %s%n", id);
		
		importantQuestionRepository.deleteById(id);
		
		Map<String, Object> responseMap = new HashMap<>();
		
		responseMap.put("success", true);
		responseMap.put("deletedImportantQuestionId", id);
		
		return responseMap;
		
	}
	

}
