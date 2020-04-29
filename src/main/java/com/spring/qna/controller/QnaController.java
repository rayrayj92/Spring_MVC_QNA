package com.spring.qna.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.qna.dao.vo.Comment;
import com.spring.qna.dao.vo.Qna;
import com.spring.qna.interceptor.SessionNames;
import com.spring.qna.service.QnaService;

@Controller
public class QnaController {
	
	private static final Logger log = LoggerFactory.getLogger(QnaController.class);
	
	@Autowired
	QnaService qnaService;
	
	@RequestMapping(value = {"/car"}, method = RequestMethod.GET)
	public ModelAndView carView(@RequestParam("p") int p) {
		ModelAndView mv = new ModelAndView("qna/index");
		mv.addObject("title", "QnA");
		
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		ArrayList<HashMap<String, Object>> resultMap = new ArrayList<>();
		
		int totalNum = qnaService.getTotalNum(); // 총 'car'의 게시물의 개수
		int itemPerPage = 5; // 5개씩 보여주기
		int pageNum = totalNum / itemPerPage; // 총 몇 페이지가 필요한지 계산
		int paginationMax = 5; // 처음 1 2 3 4 5 다음 , 
		int startNum = p; // DB Range Start Value
		
		// 총 몇 페이지가 필요한지 계산
		if(totalNum % itemPerPage > 0) {
			pageNum++;
		}
		
		// 1,6,11,16.... 페이지의 시작번호
		int startPage = ((p-1) / paginationMax) * paginationMax + 1;
		
		// 5,10,15,20.... 페이지의 끝번호
		//int endPage = startPage + paginationMax - 1;
		
		// DB Range End Value
		int lastNum = itemPerPage * p; 
		
		/*
		 * 예) 만약 2번째 페이지면 6~10 사이의 게시물을 검색해야함.
		 * 예) 2번째 페이지 = 6, 3번째 페이지 = 11 .... 
		 * */ 
		if(startNum > 1) {
			startNum = (lastNum - itemPerPage) + 1;
		}
		
		/*
		 * DB Input
		 * */
		inputMap.put("type", "car");
		inputMap.put("title", "%");
		inputMap.put("author", "%");
		inputMap.put("start", startNum);
		inputMap.put("end", lastNum);
		resultMap = (ArrayList<HashMap<String, Object>>) qnaService.getList(inputMap);
		/*
		 * ModelAndView Input
		 * */
		mv.addObject("list", resultMap);
		mv.addObject("start", startPage);
		mv.addObject("type", "car");
		mv.addObject("last", pageNum);
		mv.addObject("p", p); // 현재 페이지
		
		return mv;
	}
	
	@RequestMapping(value = {"/search"}, method = RequestMethod.GET)
	public void search(@RequestParam("s") String search,
			@RequestParam("p") String p) {
		log.info("p >> " + p);
	}
	
	@RequestMapping(value = {"/comment"}, method = RequestMethod.POST)
	public String comment(@RequestParam("comment") String cmt, 
			@RequestParam("id") String id,
			HttpServletRequest request) {
		
		long bbs_id = Long.parseLong(id);
		Date date = new Date();
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, Object> ansMap = (HashMap<String, Object>) session.getAttribute(SessionNames.LOGIN);
		String author_id = "";
		String author = "";
		
		//세션 값이 null이 아니면 로그인이 되어 있는 상태
		if(ansMap != null) {
			author = (String) ansMap.get("FULLNAME");
			author_id = (String) ansMap.get("EMAIL");
			Comment comment = new Comment(cmt, date, author_id, bbs_id, author);
			qnaService.insertComment(comment);
			return "redirect:/detail?id=" + bbs_id;
		}
		// 세션값을 Interceptor로 처리를 하려니까, 에러가 발생 
		return "redirect:/login";
	}
	
	@RequestMapping(value = {"/detail"}, method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam("id") long id) {
		HashMap<String, Object> detailMap = new HashMap<String, Object>();
		ArrayList<HashMap<String, Object>> commentMap = new ArrayList<>();
		detailMap = qnaService.getDetail(id);
		commentMap = (ArrayList<HashMap<String, Object>>) qnaService.getComment(id);
		ModelAndView mv = new ModelAndView("qna/detail");
		mv.addObject("title", "QnA");
		mv.addObject("list", detailMap);
		mv.addObject("comment", commentMap);
		mv.addObject("commentNum", commentMap.size()); // 총 댓글 수
		
		return mv;
	}
	
	@RequestMapping(value = {"/create"}, method = RequestMethod.GET)
	public ModelAndView createView() {
		ModelAndView mv = new ModelAndView("qna/create");
		mv.addObject("title", "질문 작성");
		return mv;
	}
	
	@RequestMapping(value = {"/create"}, method = RequestMethod.POST)
	public String create(@RequestParam("type") String type, 
			@RequestParam("title") String title,
			@RequestParam("content") String content, HttpServletRequest request) {
		
		Date regdate = new Date();
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, Object> ansMap = (HashMap<String, Object>) session.getAttribute(SessionNames.LOGIN);
		String author = (String) ansMap.get("FULLNAME");
		String auth_email = (String) ansMap.get("EMAIL");
		int likes = 0, pinned = 0;
		long hit = 0;
		
		Qna qna = new Qna(title, content, author, regdate, hit, likes, pinned, type, auth_email);	
		qnaService.create(qna);
		
		return "redirect:/car?p=1";
	}
}
