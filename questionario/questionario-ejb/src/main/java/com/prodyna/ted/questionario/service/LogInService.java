package com.prodyna.ted.questionario.service;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.prodyna.ted.questionario.exception.UserNotLoggedInException;
import com.prodyna.ted.questionario.model.User;

@Stateless
public class LogInService {

	@Inject
	private EntityManager em;
	
	public void checkLogIn(List<String> userTokenList) throws UserNotLoggedInException{
	
		try {
			
			TypedQuery<Long> typedQuery = em.createNamedQuery(User.QUERY_FIND_TOKEN, Long.class);
			typedQuery.setParameter("tokenList", userTokenList);
			typedQuery.getSingleResult();
		
		} catch (NoResultException e) {
			
			throw new UserNotLoggedInException();
		}
	}

	public User logIn(String email) {
		
		String token= UUID.randomUUID().toString();
		User user = new User();
		user.setEmail(email);
		user.setToken(token);
		
		em.persist(user);
		
		return user;
	}

}
