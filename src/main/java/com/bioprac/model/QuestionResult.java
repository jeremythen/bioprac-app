package com.bioprac.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "Question_Result")
public class QuestionResult {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@Column(name = "created_by", length = 25)
	private String createdBy;
	
	//@OneToMany(targetEntity = Question.class, mappedBy = "question_id")
	//@ManyToOne(targetEntity = Question.class)
	//@JoinColumn(name = "id", nullable = false)
	
	@NotNull
	private int question;
	
	@Column(nullable = false, length = 15)
	private String result;
	
	@Column(nullable = false, name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getQuestion() {
		return question;
	}

	public void setQuestion(int question) {
		this.question = question;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "QuestionResult [id=" + id + ", createdBy=" + createdBy + ", question=" + question + ", result=" + result
				+ ", createdAt=" + createdAt + "]";
	}

}
