package com.quizzapp.demo.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Set;

import org.springframework.http.ResponseEntity;

@Data
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("quiz_name")
    private String quizName;

    @ManyToMany
    @JoinTable(
        name = "quiz_questions",
        joinColumns = @JoinColumn(name = "quiz_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private Set<Questions> questions;

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", quizName='" + quizName + '\'' +
                '}';
    }
    public Set<Questions> getQuestions() {
        return this.questions;
    }
	public void setQuizName(String quizName2) {
		this.quizName=quizName2;
		
	}

	public void setQuestions(Set<Questions> copyOf) {
		this.questions=copyOf;
	}


	public void save(Quiz quiz) {
		Quiz q=new Quiz();
		q=quiz;
		
	}

}
