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
package com.prodyna.ted.questionario.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.prodyna.ted.questionario.model.Question;
import com.prodyna.ted.questionario.model.Survey;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class QuestionarioService {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	public List<Question> findAllQuestions() {
		TypedQuery<Question> typedQuery = em.createNamedQuery(Question.QUERY_FIND_ALL, Question.class);
		List<Question> resultList = typedQuery.getResultList();
		return resultList;
	}

	public void storeSurvey(Survey survey) {
		em.persist(survey);
	}

	public boolean storeSurvey(List<Survey> surveys) {
		try {
			for (Survey survey : surveys) {
				em.persist(survey);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean storeQuestion(Question question) {
		try {
			em.persist(question);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean updateQuestion(Question question) {
		try {
			em.merge(question);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
