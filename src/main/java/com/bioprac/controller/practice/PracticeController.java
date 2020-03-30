package com.bioprac.controller.practice;

import com.bioprac.model.practice.PracticeResult;
import com.bioprac.model.question.Question;
import com.bioprac.repository.practice.PracticeResultRepository;
import com.bioprac.repository.question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bioprac.model.practice.ImportantQuestion;
import com.bioprac.repository.practice.ImportantQuestionsRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/practice")
public class PracticeController {

    @Autowired
    private ImportantQuestionsRepository importantQuestionRepository;

    @Autowired
    private PracticeResultRepository practiceResultRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions/important/{userName}")
    public Iterable<ImportantQuestion> getUserImportantQuestions(@PathVariable String userName) {
        return importantQuestionRepository.findAllByUserName(userName);
    }

    @PostMapping("/questions/important")
    public ResponseEntity.HeadersBuilder saveNewUserImportantQuestion(@Valid @RequestBody ImportantQuestion importantQuestion) {

        importantQuestionRepository.save(importantQuestion);

        return ResponseEntity.noContent();

    }

    @DeleteMapping("/questions/important/{importantQuestionId}")
    public ResponseEntity.HeadersBuilder deleteImportantQuestionById(@PathVariable int importantQuestionId) {

        importantQuestionRepository.deleteById(importantQuestionId);

        return ResponseEntity.noContent();

    }

    @GetMapping("/questions/result/{userName}")
    public PracticeResult getUserResultQuestionByUserId(@PathVariable String userName) {

        return practiceResultRepository.findByUserName(userName);

    }

    @PostMapping("/questions/result")
    public ResponseEntity.HeadersBuilder saveNewPracticeResult(@Valid @RequestBody PracticeResult practiceResult) {

        practiceResultRepository.save(practiceResult);

        return ResponseEntity.noContent();

    }

    @GetMapping("/questions/new/{userId}")
    public Iterable<Question> getUserNewQuestions(@PathVariable int userId) {

        return questionRepository.findAll();

    }

}
