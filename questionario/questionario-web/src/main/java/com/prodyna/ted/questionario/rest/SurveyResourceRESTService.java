
/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.prodyna.ted.questionario.rest;

import io.swagger.annotations.Api;

import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CREATED;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.prodyna.ted.questionario.interceptor.LogInBinding;
import com.prodyna.ted.questionario.model.Answer;
import com.prodyna.ted.questionario.model.Question;
import com.prodyna.ted.questionario.model.Survey;
import com.prodyna.ted.questionario.service.QuestionarioService;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the
 * members table.
 */
@Path("/")
@RequestScoped
@LogInBinding
public class SurveyResourceRESTService {

	@Inject
	private Logger log;

	@Inject
	private Validator validator;

    @Inject
    private QuestionarioService registration;

	@Inject
	private QuestionarioService questionarioService;

    @Path("question")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Question> findAllQuestions(@PathParam("token") String token) {
        return questionarioService.findAllQuestions();
    
	@Path("question")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveQuestion(Question question) {
		return questionarioService.storeQuestion(question) ? status(CREATED).build() : status(BAD_REQUEST).build();
	}

	@Path("question")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateQuestion(Question question) {
		return questionarioService.updateQuestion(question) ? ok().build() : status(BAD_REQUEST).build();
	}

	@Path("question")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveSurveyAnswer(@PathParam("token") String token, List<Survey> surveys) {
		return questionarioService.storeSurvey(surveys) ? ok().build() : status(BAD_REQUEST).build();
	}

}
