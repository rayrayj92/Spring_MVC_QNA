package com.spring.qna.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionController {
	
	@RequestMapping("/error/error404.html")
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public String pageNotFound() {
		return "/error/error404.html";
	}
	
	@RequestMapping("/error/error.html")
	public String error(Model model, HttpServletResponse response) {
		model.addAttribute("response", response);
		return "/error/error";
	}

}
