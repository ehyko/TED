package com.prodyna.ted.questionario.interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.prodyna.ted.questionario.exception.UserNotLoggedInException;
import com.prodyna.ted.questionario.service.LogInService;

@LogInBinding
@Interceptor
public class LogInInterceptor {

	@Inject
	private LogInService logInService;
	
    @AroundInvoke
    public Object checkLogIn(InvocationContext ic) throws Exception {
    	
    	String userToken = null;
    	Object[] parameters = ic.getParameters();
    	for (Object parameter : parameters) {
			if (parameter.equals("token")) {
				userToken = (String) parameter;
				break;
			}
		}
    	
    	Object proceed;
		if (userToken != null ){
    		logInService.checkLogIn(userToken);
    		proceed = ic.proceed();
    	}else {
    		throw new UserNotLoggedInException();
    	}
		
		return proceed;
    }
}
