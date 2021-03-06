package com.spring.qna.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter implements SessionNames {
	
	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(); //Per Browser
		
		if(session.getAttribute(LOGIN) != null) {
			session.removeAttribute(LOGIN);
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerMethod method = (HandlerMethod) handler;
		log.info("Bean >> " + method.getBean() + ", Method >> " + method.getMethod());
		log.info("Model >> " + modelAndView);
		
		/**
		 * HashMap<String, Object> user = 
		 * 			(HashMap<String, Object>) modelAndView.getModelMap().get("user");
		 * "type safety unchecked cast from object" 경고
		 *  아래 코드로 대체
		 * */ 
		HttpSession session = request.getSession();
		HashMap<String, Object> user = new HashMap<String, Object>();
		Object tmp = modelAndView.getModelMap().get("user");
		if(tmp instanceof HashMap<?,?>) {
			for(Map.Entry<?, ?> element : ((HashMap<?,?>) tmp).entrySet()) {
				user.put((String)element.getKey(), element.getValue());
			}
		}
		
		log.info("LoginInterceptor User Info >> " + user);
		
		if(user != null) {
			session.setAttribute(LOGIN, user);

			Cookie loginCookie = new Cookie(LOGIN_COOKIE, session.getId());
			loginCookie.setPath("/");
			loginCookie.setMaxAge(7 * 24 * 60 * 60);
				
			response.addCookie(loginCookie);
			
			String attempted = (String) session.getAttribute(ATTEMPTED);
			if(!StringUtils.isEmpty(attempted)) {
				response.sendRedirect(attempted);
				session.removeAttribute(ATTEMPTED);
			} else {
				response.sendRedirect("/qna/main?p=1&t=car&k=&st=");
			}
		}
	}
	
}
