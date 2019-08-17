package com.bioprac.controller.question;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bioprac.model.question.ImportantQuestions;
import com.bioprac.repository.question.ImportantQuestionsRepository;

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
		
		importantQuestionRepository.save(importantQuestion);
		
		Map<String, Object> responseMap = new HashMap<>();
		
		responseMap.put("success", "true");
		responseMap.put("importantQuestionID", importantQuestion.getId());
		
		return responseMap;
		
	}
	
	@DeleteMapping("/deleteImportantQuestion/{id}")
	public Map<String, Object> deleteImportantQuestion(@PathVariable int id) {
		
		importantQuestionRepository.deleteById(id);
		
		Map<String, Object> responseMap = new HashMap<>();
		
		responseMap.put("success", true);
		responseMap.put("deletedImportantQuestionId", id);
		
		return responseMap;
		
	}
	

}
