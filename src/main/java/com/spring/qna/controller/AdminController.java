package com.spring.qna.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.qna.service.AdminService;

@Controller
public class AdminController {
	
	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping(value = {"/admin/main"}, method = RequestMethod.GET)
	public ModelAndView adminView() throws Exception {
		ModelAndView mv = new ModelAndView("qna/admin/main");
		mv.addObject("title", "관리자");
		log.info(" >>>> 관리자 페이지 <<<< ");
		return mv;
	}
	
	@RequestMapping(value = {"/admin/block"}, method = RequestMethod.GET)
	public ModelAndView blockView() throws Exception {
		ModelAndView mv = new ModelAndView("qna/admin/block");
		mv.addObject("title", "관리자");
		log.info("관리자 권한이 없는 사용자가 들어왔습니다.");
		return mv;
	}
	
	@RequestMapping(value = {"/admin/pinned"}, method = RequestMethod.GET)
	public String pinned(@RequestParam("id") long id) throws Exception {
		try {
			adminService.makePinned(id);
			log.info("ID >> " + id + " 해당 질문을 공지사항으로 등록했습니다.");
		} catch(Exception e) {
			e.getStackTrace();
		}
		return "redirect:/mypage?p=1&k=";
	}
}
