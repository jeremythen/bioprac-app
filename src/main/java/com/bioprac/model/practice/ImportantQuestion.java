package com.bioprac.model.practice;

import com.bioprac.model.question.Question;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "important_question")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"question_id", "user_name"}))
public class ImportantQuestion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, name = "user_name")
	private String userName;

	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;

	//@Column(nullable = false, name = "question_id")
	//private int questionId;

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
	private String practiceSubcategory;

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

	public String getPracticeSubcategory() {
		return practiceSubcategory;
	}

	public void setPracticeSubcategory(String practiceSubsubject) {
		this.practiceSubcategory = practiceSubsubject;
	}

	public String getPracticeType() {
		return practiceType;
	}

	public void setPracticeType(String practiceType) {
		this.practiceType = practiceType;
	}
}
