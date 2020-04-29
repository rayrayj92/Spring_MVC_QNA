package com.spring.qna.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter implements SessionNames {
	
	private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(); //Per Browser
		
		if(session.getAttribute(LOGIN) == null) {
			String uri = request.getRequestURI();
			String query = request.getQueryString();
			
			if(!StringUtils.isEmpty(query)) {
				uri += "?" + query;
			}
			
			session.setAttribute(ATTEMPTED, uri);
			log.info("로그인을 하세요.");
			response.sendRedirect("/qna/login");
		}
		
		return true;
	}
	
}
