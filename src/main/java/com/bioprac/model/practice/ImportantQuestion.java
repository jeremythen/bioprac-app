package com.bioprac.model.practice;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity(name = "important_question")
public class ImportantQuestion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, name = "user_name")
	private String userName;

	@NotBlank
	@Column(nullable = false, name = "question_id")
	private int questionId;

	@Column(nullable = false, name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();

	@NotBlank
	@Column(nullable = false, name = "practice_type")
	private String practiceType;

	@NotBlank
	@Column(nullable = false, name = "practice_category")
	private String practiceCategory;

	@NotBlank
	@Column(name = "practice_subcategory")
	private String practiceSucategory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getPracticeCategory() {
		return practiceCategory;
	}

	public void setPracticeCategory(String practiceSubject) {
		this.practiceCategory = practiceSubject;
	}

	public String getPracticeSucategory() {
		return practiceSucategory;
	}

	public void setPracticeSucategory(String practiceSubsubject) {
		this.practiceSucategory = practiceSubsubject;
	}

	public String getPracticeType() {
		return practiceType;
	}

	public void setPracticeType(String practiceType) {
		this.practiceType = practiceType;
	}
}
