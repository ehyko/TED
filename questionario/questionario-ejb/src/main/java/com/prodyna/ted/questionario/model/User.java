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


@NamedQueries({
	@NamedQuery(name = User.QUERY_FIND_TOKEN, query = "SELECT u.id FROM User u WHERE u.token in :tokenList")
})
@Entity
@Table(name = "user")
public class User implements Serializable{

	private static final long serialVersionUID = 7017954552355465612L;
	
	public  static final String QUERY_FIND_TOKEN = "User.findToken";
	
	@Id
	@Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@Column(length = 128)
	private String token;

	@Column(length = 128)
	private String email;
	
	@Column
	private boolean admin;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	
}
