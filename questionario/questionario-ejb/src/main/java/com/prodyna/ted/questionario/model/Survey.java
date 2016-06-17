package com.prodyna.ted.questionario.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "survey")
public class Survey implements Serializable{

	private static final long serialVersionUID = -1299398422247575454L;

	@Id
	@Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@Column(name = "question_Id")
	private long questionId;

	@Column(name = "answer_Id")
	private long answerId;

	public Survey() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}
	
	
}
