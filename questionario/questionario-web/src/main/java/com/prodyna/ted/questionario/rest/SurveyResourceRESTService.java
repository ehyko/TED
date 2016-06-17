
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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.prodyna.ted.questionario.model.Answer;
import com.prodyna.ted.questionario.model.Question;
import com.prodyna.ted.questionario.model.Survey;
import com.prodyna.ted.questionario.service.QuestionarioService;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the members table.
 */
@Path("/")
@RequestScoped
//@Api(value = "/question", description = "REST aPI um die Umfrage zu managen")
public class SurveyResourceRESTService {
    @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Inject
    private QuestionarioService questionarioService;

    @Inject
    QuestionarioService registration;

    @Path("question")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Question> findAllQuestions() {
        return questionarioService.findAllQuestions();
	}

    @Path("survey")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveSurveyAnswer(List<Survey> surveys) {
    	questionarioService.storeSurvey(surveys);
	}
}
