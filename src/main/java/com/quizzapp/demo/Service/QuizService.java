package com.quizzapp.demo.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizzapp.demo.Model.Questions;
import com.quizzapp.demo.Model.Quiz;
import com.quizzapp.demo.dao.QuizDao;

import jakarta.persistence.EntityNotFoundException;

@Service
public class QuizService {
@Autowired
QuizDao quizdao;
public ResponseEntity<List<Quiz>> ListQuiz(){
	List<Quiz>qz=quizdao.findAll();
	return new ResponseEntity(qz,HttpStatus.OK);
}
public ResponseEntity<String> deletequiz(Integer Id){
	quizdao.deleteById(Id);
	return new ResponseEntity("deleted Successfully",HttpStatus.OK);
}
public ResponseEntity<Set<Questions>> listquizquestion(Integer id) {
	Quiz quiz = quizdao.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Quiz not found with id: " + id));
    return new ResponseEntity(quiz.getQuestions(),HttpStatus.OK);
}
}
