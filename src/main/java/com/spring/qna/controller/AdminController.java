package com.spring.qna.controller;

import java.util.HashMap;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AdminController {
	
	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value = {"/admin/main"}, method = RequestMethod.GET)
	public ModelAndView adminView() {
		ModelAndView mv = new ModelAndView("qna/admin/main");
		mv.addObject("title", "관리자");
		return mv;
	}
	
	@RequestMapping(value = {"/admin/block"}, method = RequestMethod.GET)
	public ModelAndView blockView() {
		ModelAndView mv = new ModelAndView("qna/admin/block");
		mv.addObject("title", "관리자");
		return mv;
	}
	
}
