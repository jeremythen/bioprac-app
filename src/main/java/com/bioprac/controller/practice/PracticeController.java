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
    public ImportantQuestion saveNewUserImportantQuestion(@Valid @RequestBody ImportantQuestion importantQuestion) {

        importantQuestionRepository.save(importantQuestion);

        return importantQuestion;

    }

    @DeleteMapping("/questions/important/{importantQuestionId}")
    public ResponseEntity deleteImportantQuestionById(@PathVariable int importantQuestionId) {

        importantQuestionRepository.deleteById(importantQuestionId);

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/questions/result/{userName}")
    public Iterable<PracticeResult> getUserResultQuestionByUserId(@PathVariable String userName) {

        return practiceResultRepository.findAllByUserName(userName);

    }

    @PostMapping("/questions/result")
    public PracticeResult saveNewPracticeResult(@Valid @RequestBody PracticeResult practiceResult) {

        practiceResultRepository.save(practiceResult);

        return practiceResult;

    }

    @GetMapping("/questions/new/{userName}")
    public Iterable<Question> getUserNewQuestions(@PathVariable String userName) {

        return questionRepository.findAll();

    }

}
