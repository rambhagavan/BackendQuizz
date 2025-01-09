package com.quizzapp.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizzapp.demo.Model.Questions;
import com.quizzapp.demo.Model.Quiz;

public interface QuizDao extends JpaRepository<Quiz,Integer> {

}
