
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

import java.util.List;

import io.swagger.annotations.Api;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CREATED;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.prodyna.ted.questionario.interceptor.LogInBinding;
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
	private QuestionarioService questionarioService;

    @Path("question")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Question> findAllQuestions(@QueryParam("token") String token) {
        return questionarioService.findAllQuestions();
    }
    
	@Path("question")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveQuestion(@QueryParam("token") String token, Question question) {
		return questionarioService.storeQuestion(question) ? status(CREATED).build() : status(BAD_REQUEST).build();
	}

	@Path("question")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateQuestion(@QueryParam("token") String token, Question question) {
		return questionarioService.updateQuestion(question) ? ok().build() : status(BAD_REQUEST).build();
	}

	@Path("question")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveSurveyAnswer(@QueryParam("token") String token, List<Survey> surveys) {
		return questionarioService.storeSurvey(surveys) ? ok().build() : status(BAD_REQUEST).build();
	}

}
