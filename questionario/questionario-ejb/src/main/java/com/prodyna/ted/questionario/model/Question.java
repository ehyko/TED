package com.prodyna.ted.questionario.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@NamedQueries({ @NamedQuery(
        name = Question.QUERY_FIND_ALL,
        query = "SELECT q FROM Question q")
})
@Entity
@Table(name = "question")
public class Question implements Serializable{

	private static final long serialVersionUID = -4383347524587592224L;

	public static final String QUERY_FIND_ALL = "Question.findAll";
	@Id
	@Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@Column
	private String text;
	
	@Fetch(FetchMode.SELECT)
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "question_Id")
    private List<Answer> answerList;
	
    public Question() {

    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}
    
    
}
