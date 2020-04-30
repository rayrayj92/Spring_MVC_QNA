package com.spring.qna.interceptor;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RoleInterceptor extends HandlerInterceptorAdapter implements SessionNames {
	
	private static final Logger log = LoggerFactory.getLogger(RoleInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(); //Per Browser
		String role = "";
		
		try {
			HashMap<String, Object> loginMap = (HashMap<String, Object>) session.getAttribute(LOGIN);
			role = (String) loginMap.get("ROLE");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 로그인을 안했거나 관리자가 아니면 block.html로 이동
		if(!role.equals("Admin") || session.getAttribute(LOGIN) == null) {
			response.sendRedirect("/qna/admin/block"); // Msg >> 관리자 권한이 없습니다.
			return false;
		}
		
		return true;
	}
	
}
