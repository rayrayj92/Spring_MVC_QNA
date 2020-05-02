package com.spring.qna.controller;

import java.util.HashMap;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.spring.qna.dao.vo.User;
import com.spring.qna.interceptor.SessionNames;
import com.spring.qna.service.LoginService;

@Controller
public class UserController {
	
	@Autowired
	LoginService loginService;
	
	@Inject
	PasswordEncoder passwordEncoder;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public ModelAndView loginView() throws Exception{
		ModelAndView mv = new ModelAndView("login/login");
		mv.addObject("title", "로그인");
		return mv;
	}
	
	@RequestMapping(value = {"/loginPost"}, method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, 
			@RequestParam("password") String password, HttpServletRequest request, Model model) throws Exception {
		
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		inputMap.put("email", email);
		inputMap.put("password", password);
		
		resultMap = loginService.getUser(email);
		String bcryptPassword = (String) resultMap.get("PASSWORD");
		
		if(passwordEncoder.matches(password, bcryptPassword)) {
			model.addAttribute("loginResult", "Login Success");
			model.addAttribute("user",resultMap);
		} else {
			model.addAttribute("loginResult", "Login Fail");
		}
		
		return "login/loginPost";
	}
	
	@RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	public ModelAndView registerView() throws Exception {
		ModelAndView mv = new ModelAndView("login/register");
		mv.addObject("title", "회원가입");
		return mv;
	}
	
	@RequestMapping(value = {"/register"}, method = RequestMethod.POST)
	public String register(@RequestParam("email") String email, @RequestParam("fullname") String fullname, 
			@RequestParam("password") String password) throws Exception {
		
		String regx_email = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
		boolean result = Pattern.matches(regx_email, email);
		
		if(result && !fullname.equals("") && (password.length() >= 6)) {
			String bcryptPassword = passwordEncoder.encode(password); // BCrypt로 해싱 암호화, 10 rounds
			User user = new User(email, bcryptPassword, fullname, "User");
			loginService.insertUser(user);
			log.info(">>> Register Success <<<");
			return "redirect:/login";
		}
		
		return "redirect:/register";
	}
	
	@RequestMapping(value = "/logout")
	public String Logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.removeAttribute(SessionNames.LOGIN);
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, SessionNames.LOGIN_COOKIE);
			
			if(loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				
				response.addCookie(loginCookie);
			}
			
			return "redirect:/login";
		}
		
		return "500";
	}
	
	@RequestMapping(value = "/register/check", method = RequestMethod.GET)
	@ResponseBody
	public String emailCheck(@RequestParam("email") String email) throws Exception{
		int tmp = loginService.checkUser(email);
		String ans = Integer.toString(tmp);
		return ans;
	}
}
