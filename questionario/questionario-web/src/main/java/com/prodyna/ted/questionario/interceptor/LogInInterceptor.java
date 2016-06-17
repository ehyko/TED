package com.prodyna.ted.questionario.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.prodyna.ted.questionario.exception.UserNotLoggedInException;
import com.prodyna.ted.questionario.service.LogInService;

@LogInBinding
@Interceptor
public class LogInInterceptor {

	@Inject
	private LogInService logInService;
	
    @AroundInvoke
    public Object checkLogIn(InvocationContext ic) throws Exception {
    	
    	List<String> userTokenList = new ArrayList<String>();
    	Object[] parameters = ic.getParameters();
    	for (Object parameter : parameters) {
    		try {
				String token = (String) parameter;
				userTokenList.add(token);
			} catch (Exception ignore) {
			}
    	}
    	
    	Object proceed;
		try {
			logInService.checkLogIn(userTokenList);
			proceed = ic.proceed();
		} catch (Exception e) {
			proceed = Response.status(Status.FORBIDDEN).build();
		}
    	
		return proceed;
    }
}
