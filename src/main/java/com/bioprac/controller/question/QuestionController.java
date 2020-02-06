package com.bioprac.controller.question;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/getQuestions")
	@ResponseBody
	public Iterable<Question> getQuestions() {
		
		return questionRepository.findAll();
	}
	
	
     
	
	@PostMapping("/addQuestion")
	public ResponseEntity<?> addQuestion(@Valid @RequestBody Question question) {
		
		questionRepository.save(question);
		
		Map<String, Object> responseMap = new HashMap<>();
		
		responseMap.put("questionId", question.getId());
		
		BiopracResponse biopracResponse = new BiopracResponse(true, "Question added Successfully", responseMap);
		
		return ResponseEntity.ok(biopracResponse);
		
	}
	
	@GetMapping("/getQuestions/{category}")
	@ResponseBody
	public Iterable<Question> getQuestions(@PathVariable String category) {
		
		return questionRepository.findAllByCategory(category);
		
	}

	
	@DeleteMapping("/deleteQuestion/{id}")
	public ResponseEntity<?> deleteQuestion(@PathVariable @Min(1) int id) {
		
		questionRepository.deleteById(id);
		
		Map<String, Object> responseMap = new HashMap<>();
		
		responseMap.put("deletedQuestionId", id);
		
		BiopracResponse biopracResponse = new BiopracResponse(true, "Question deleted Successfully", responseMap);
		
		return ResponseEntity.ok(biopracResponse);
		
	}
	
	
	@PutMapping("/updateQuestion")
	public ResponseEntity<?> updateQuestion(@Valid @RequestBody Question question) {
		
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
		
		BiopracResponse biopracResponse = new BiopracResponse(true, "Question updated Successfully", responseMap);
		
		return ResponseEntity.ok(biopracResponse);
		
	}

}