package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



public class RequestAuthInterceptor extends HandlerInterceptorAdapter {

	Logger logger=LoggerFactory.getLogger("StudentController.class");


	@Value("${mft.rest.username}")
	private String authUsername;

	@Value("${mft.rest.password}")
	private String authPassword;

	static final String NO_AUTH_ERROR = "Not Authorized! No Authentication passed in header";
	static final String INCORRECT_FORMAT_ERROR = "Incorrect format for Authentication! Format should be username:password";
	static final String INVALID_CRED_ERROR = "Authentication Error! Invalid Credentials";

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
		logger.debug("afterCompletion");
		super.afterCompletion(request, response, handler, exception);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("postHandle");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("preHandle");
		boolean reqValid = false;
		String headerAuth = request.getHeader("Authorization");	
		
		if (null == headerAuth) {
			logger.error(NO_AUTH_ERROR);
		} else {
			String[] splitHeaderAuth = headerAuth.split(":");
			if (splitHeaderAuth.length != 2) {
				logger.error(INCORRECT_FORMAT_ERROR);
			} else {
				String username = splitHeaderAuth[0];
				String password = splitHeaderAuth[1];
				if (authUsername.equals(username) && authPassword.equals(password)) {
					logger.info("Request Authenticated successfully for {}", request.getRequestURI());
					reqValid = true;
				} else {
					logger.error(INVALID_CRED_ERROR);
				}
			}
		}

		if (!reqValid) {
			logger.info("Authentication Failed for {}", request.getRequestURI());
			response.setStatus(600);
		}
		return reqValid;
	}
}
