package com.prodyna.ted.questionario.dao;

public class Token {

	private String token;
	private String url;
	
	public Token() {
	}
	
	public Token(String token, String url) {
		super();
		this.token = token;
		this.url = url;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
}
