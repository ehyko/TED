
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

import com.prodyna.ted.questionario.model.Question;
import com.prodyna.ted.questionario.model.Survey;
import com.prodyna.ted.questionario.service.QuestionarioService;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the members table.
 */
@RequestScoped
@Api(value = "/question", description = "REST aPI um die Umfrage zu managen")
public class MemberResourceRESTService {
    @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Inject
    private QuestionarioService questionarioService;

    @Inject
    QuestionarioService registration;

    @Path("/question")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Question> findAllQuestions() {
        return questionarioService.findAllQuestions();
	}

    @Path("/survey")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveSurveyAnswer(SurveyAnswer surveyAnswer) {
    	for (com.prodyna.ted.questionario.rest.MemberResourceRESTService.SurveyAnswer.Answer a : surveyAnswer.getAnswers()) {
    		Survey survey = new Survey();
    		survey.setAnswerId(a.getId());
    		survey.setQuestionId(a.getQuestionId());
    		questionarioService.storeSurvey(survey);
    	}
	}

	/**
	 * Creates a new member from the values provided. Performs validation, and
	 * will return a JAX-RS response with either 200 ok, or with a map of
	 * fields, and related errors.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createMember(Survey survey) {

		Response.ResponseBuilder builder = null;

		builder = Response.ok();

		return builder.build();
	}
	
	private class SurveyAnswer {

		private List<Answer> answers;

		public List<Answer> getAnswers() {
			return answers;
		}

		public void setAnswers(List<Answer> answers) {
			this.answers = answers;
		}

		public SurveyAnswer() {
		}
		
		private class Answer {

			private long questionId;

			private long id;

			public Answer() {
			}

			public long getQuestionId() {
				return questionId;
			}

			public void setQuestionId(long questionId) {
				this.questionId = questionId;
			}

			public long getId() {
				return id;
			}

			public void setId(long id) {
				this.id = id;
			}

		}


	}

	
}
