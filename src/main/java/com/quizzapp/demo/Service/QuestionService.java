package com.quizzapp.demo.Service;
import java.util.List;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizzapp.demo.Model.Questions;
import com.quizzapp.demo.Model.Quiz;
import com.quizzapp.demo.dao.QuestionDao;
import com.quizzapp.demo.dao.QuizDao;
@Service
public class QuestionService {
@Autowired
private QuestionDao questionsdao;
@Autowired
private QuizDao quizDao;
public ResponseEntity<List<Questions>> getallquestions(){
	return new ResponseEntity<>(questionsdao.findAll(),HttpStatus.OK);
}
public ResponseEntity<List<Questions>> findbycategory(String category) {
	
	return new ResponseEntity<>(questionsdao.findByCategory(category),HttpStatus.OK);
}
public String addquestion(Questions quns) {
	questionsdao.save(quns);
	System.out.println(quns);
	return "added successfully";
}
public String deletequestion(Integer id) {
	questionsdao.deleteById(id);
	return "question is deleted successfully";
}
public String UpdateQuestion(Integer id,String newquestion) {
	Questions q1 = questionsdao.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
    q1.SetQuestion(newquestion);
    questionsdao.save(q1);
    return "updated successfully";
}

public ResponseEntity<String> createquiz(String quizName,List<Integer>questionIds){
	List<Questions> questions = questionsdao.findAllById(questionIds);

    if (questions.size() != questionIds.size()) {
        return new ResponseEntity<>("Some questions were not found", HttpStatus.BAD_REQUEST);
    }

    // Create and save the new quiz
    Quiz quiz = new Quiz();
    quiz.setQuizName(quizName);
    quiz.setQuestions(Set.copyOf(questions)); // Use Set to avoid duplicates

    quizDao.save(quiz);
    return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
}
}
