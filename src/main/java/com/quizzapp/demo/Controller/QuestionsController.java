package com.quizzapp.demo.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.quizzapp.demo.Model.Questions;
import com.quizzapp.demo.Model.Quiz;
import com.quizzapp.demo.Service.QuestionService;
import com.quizzapp.demo.Service.QuizService;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/Questions")
public class QuestionsController {

    @Autowired
    private QuestionService questions;
    @Autowired 
    private QuizService qz;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/allquestions")
    public ResponseEntity<List<Questions>> getAllQuestions() {
        return questions.getallquestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category) {
        return questions.findbycategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Questions quns) {
    	System.out.println("some thing..44");
        String result = questions.addquestion(quns);
        System.out.println("some thing..46");
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        String result = questions.deletequestion(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Integer id, @RequestBody String updatedQuestion) {
        String result = questions.UpdateQuestion(id, updatedQuestion);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/createquiz/{tablename}")
    public ResponseEntity<String> createQuiz(@PathVariable String tablename, @RequestBody List<Integer> ids) {
    	
    	return questions.createquiz(tablename,ids);

    }
    @GetMapping("/listquiz")
    public ResponseEntity<List<Quiz>> ListAllQuiz(){
    	return qz.ListQuiz();
    }
    @DeleteMapping("/deletequiz/{id}")
    public ResponseEntity<String> deletequiz(@PathVariable String id){
        Integer quizId = Integer.parseInt(id); 
    	return qz.deletequiz(quizId);
    }
    @GetMapping("/listquizquestions/{id}")
    public ResponseEntity<Set<Questions>> listquizquestion(@PathVariable String id){
    	
    	Integer Id=Integer.parseInt(id);
    	return qz.listquizquestion(Id);
    }
}
