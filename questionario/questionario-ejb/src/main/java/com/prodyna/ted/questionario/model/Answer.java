package com.prodyna.ted.questionario.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Answer")
public class Answer implements Serializable{

	private static final long serialVersionUID = -1299398422247575454L;

	@Id
	@Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@Column(name = "question_Id")
	private Long questionId;
	
	@Column
	private String text;
	
	public Answer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
