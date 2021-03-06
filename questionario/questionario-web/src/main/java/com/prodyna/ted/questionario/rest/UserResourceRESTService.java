package com.prodyna.ted.questionario.rest;

import java.net.URI;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.prodyna.ted.questionario.dao.Token;
import com.prodyna.ted.questionario.model.User;
import com.prodyna.ted.questionario.service.LogInService;

@Path("/")
@RequestScoped
public class UserResourceRESTService {

	@Inject
	private LogInService logInService;
	
	@POST
	public Response generateUserToken(User newUser){
		Response response;
		try {
			User user = logInService.logIn(newUser.getEmail());
			Token token = new Token(user.getToken(), Response.created(URI.create("/survey?token=" + user.getToken())).build().getLocation().toString());
			response = Response.status(Status.CREATED).entity(token).build();
		} catch (Exception e) {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}
}
