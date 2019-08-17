package com.bioprac.model.question;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
	@Column(name="created_by", length = 25)
	private String createdBy;
	
	@NotBlank
	@Column(length = 25)
	private String category;
	
	private String subcategory;
	
	@NotBlank
	@Column(length = 25)
	private String type;
	
	@NotBlank
	@Column(length = 1000, nullable = false, unique = true)
	private String question;
	
	@NotBlank
	@Column(length = 100)
	private String answer1;
	
	@NotBlank
	@Column(length = 100)
	private String answer2;
	
	@NotBlank
	@Column(length = 100)
	private String answer3;
	
	@NotBlank
	@Column(length = 100)
	private String answer4;
	
	@NotBlank
	@Column(name = "correct_answer", length = 100)
	private String correctAnswer;
	
	@NotBlank
	private String explanation;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(length = 15)
	private String status;
	
	@Column(name = "edited_by", length = 25)
	private String editedBy;
	
	private String source;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public int getId() {
		return id;
	}
	public Question setId(int id) {
		this.id = id;
		return this;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public Question setCreatedBy(String created_by) {
		this.createdBy = created_by;
		return this;
	}
	public String getCategory() {
		return category;
	}
	public Question setCategory(String category) {
		this.category = category;
		return this;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public Question setSubcategory(String subcategory) {
		this.subcategory = subcategory;
		return this;
	}
	public String getType() {
		return type;
	}
	public Question setType(String type) {
		this.type = type;
		return this;
	}
	public String getQuestion() {
		return question;
	}
	public Question setQuestion(String qusetion) {
		this.question = qusetion;
		return this;
	}
	public String getAnswer1() {
		return answer1;
	}
	public Question setAnswer1(String answer1) {
		this.answer1 = answer1;
		return this;
	}
	public String getAnswer2() {
		return answer2;
	}
	public Question setAnswer2(String answer2) {
		this.answer2 = answer2;
		return this;
	}
	public String getAnswer3() {
		return answer3;
	}
	public Question setAnswer3(String answer3) {
		this.answer3 = answer3;
		return this;
	}
	public String getAnswer4() {
		return answer4;
	}
	public Question setAnswer4(String answer4) {
		this.answer4 = answer4;
		return this;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public Question setCorrectAnswer(String correct_answer) {
		this.correctAnswer = correct_answer;
		return this;
	}
	public String getExplanation() {
		return explanation;
	}
	public Question setExplanation(String explanation) {
		this.explanation = explanation;
		return this;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public Question setImageUrl(String image_url) {
		this.imageUrl = image_url;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public Question setStatus(String status) {
		this.status = status;
		return this;
	}
	public String getEditedBy() {
		return editedBy;
	}
	public Question setEditedBy(String edited_by) {
		this.editedBy = edited_by;
		return this;
	}
	public String getSource() {
		return source;
	}
	public Question setSource(String source) {
		this.source = source;
		return this;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public Question setCreatedAt(LocalDateTime created_at) {
		this.createdAt = created_at;
		return this;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public Question setUpdatedAt(LocalDateTime updated_at) {
		this.updatedAt = updated_at;
		return this;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", createdBy=" + createdBy + ", category=" + category + ", subcategory="
				+ subcategory + ", type=" + type + ", question=" + question + ", answer1=" + answer1 + ", answer2="
				+ answer2 + ", answer3=" + answer3 + ", answer4=" + answer4 + ", correctAnswer=" + correctAnswer
				+ ", explanation=" + explanation + ", imageUrl=" + imageUrl + ", status=" + status + ", editedBy="
				+ editedBy + ", source=" + source + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

		
}
