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

@Data
@Entity
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("question_title")
    private String questionTitle;

    @JsonProperty("question")
    private String question;

    @JsonProperty("option1")
    private String option1;

    @JsonProperty("option2")
    private String option2;

    @JsonProperty("option3")
    private String option3;

    @JsonProperty("option4")
    private String option4;

    @JsonProperty("answer")
    private String answer;

    @JsonProperty("difficulty_level")
    private String difficultyLevel;

    @JsonProperty("category")
    private String category;

    @ManyToMany
    @JoinTable(
        name = "quiz_questions",
        joinColumns = @JoinColumn(name = "question_id"),
        inverseJoinColumns = @JoinColumn(name = "quiz_id")
    )
    private Set<Quiz> quizzes;

    @Override
    public String toString() {
        return "Questions{" +
                "id=" + id +
                ", questionTitle='" + questionTitle + '\'' +
                ", question='" + question + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", answer='" + answer + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

	public void SetQuestion(String newquestion) {
		this.question=newquestion;
		
	}
}
