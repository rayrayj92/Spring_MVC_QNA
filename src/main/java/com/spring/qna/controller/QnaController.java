package com.spring.qna.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@RequestMapping(value = {"/mypage"}, method = RequestMethod.GET)
	public ModelAndView mypageView(@RequestParam("p") String page,
			@RequestParam("k") String keyword,
			HttpServletRequest request) throws Exception {
		
		ArrayList<HashMap<String, Object>> resultMap = new ArrayList<>();
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		
		ModelAndView mv = new ModelAndView("qna/mypage");
		mv.addObject("title", "내가 쓴 글");
		
		if(keyword.equals("") || keyword == null) {
			keyword = "%";
		}

		/**
		 * HashMap<String, Object> loginMap = 
		 * 			(HashMap<String, Object>) session.getAttribute(LOGIN);
		 * "type safety unchecked cast from object" 경고
		 *  아래 코드로 대체
		 * */
		HttpSession session = request.getSession();
		HashMap<String, Object> loginMap = new HashMap<String, Object>();
		Object tmp = session.getAttribute(SessionNames.LOGIN);
		if(tmp instanceof HashMap<?,?>) {
			for(Map.Entry<?, ?> element : ((HashMap<?,?>) tmp).entrySet()) {
				loginMap.put((String)element.getKey(), element.getValue());
			}
		}
		
		String author_email = (String) loginMap.get("EMAIL");
		
		HashMap<String, Object> totalNumMap = new HashMap<String, Object>();
		totalNumMap.put("author_email", author_email);
		totalNumMap.put("title", keyword);
		
		int totalNum = qnaService.getMyListTotalNum(totalNumMap); // 결과물의 게시물의 개수
		int p = Integer.parseInt(page);
		int itemPerPage = 5; // 5개씩 보여주기
		int pageNum = totalNum / itemPerPage; // 총 몇 페이지가 필요한지 계산
		int paginationMax = 5; // 처음 1 2 3 4 5 다음 , 
		int startNum = p; // DB Range Start Value
		

		// 총 몇 페이지가 필요한지 계산
		if(totalNum % itemPerPage > 0) {
			pageNum++;
		} else if(pageNum == 0) {
			pageNum = 1;
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
		inputMap.put("author_email", author_email);
		inputMap.put("title", keyword);
		inputMap.put("start", startNum);
		inputMap.put("end", lastNum);
		resultMap = (ArrayList<HashMap<String, Object>>) qnaService.getMyList(inputMap);
		
		/*
		 * ModelAndView Input
		 * */
		mv.addObject("list", resultMap);
		mv.addObject("start", startPage);
		mv.addObject("last", pageNum);
		mv.addObject("p", p); // 현재 페이지
		mv.addObject("keyword", keyword);
		
		return mv;
	}
	
	@RequestMapping(value = {"/main"}, method = RequestMethod.GET)
	public ModelAndView mainView(@RequestParam("p") String page, @RequestParam("t") String type
			,@RequestParam("k") String keyword, @RequestParam("st") String searchType) throws Exception {
		
		ModelAndView mv = new ModelAndView("qna/index");
		mv.addObject("title", "QnA");
		
		String titleInput = "%", authorInput ="%";
		
		switch(searchType) {
			case "title":
				titleInput = keyword;
				break;
			case "author":
				authorInput = keyword;
				break;
			default:
				break;
		}
		
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		HashMap<String, Object> totalNumMap = new HashMap<String, Object>();
		// 질문 리스트
		ArrayList<HashMap<String, Object>> resultMap = new ArrayList<>();
		// 공지사항 리스트
		ArrayList<HashMap<String, Object>> pinnedMap = new ArrayList<>();
		
		totalNumMap.put("type", type);
		totalNumMap.put("title", titleInput);
		totalNumMap.put("author", authorInput);

		int p = Integer.parseInt(page);
		int totalNum = qnaService.getTotalNum(totalNumMap); // 결과물의 게시물의 개수
		int itemPerPage = 5; // 5개씩 보여주기
		int pageNum = totalNum / itemPerPage; // 총 몇 페이지가 필요한지 계산
		int paginationMax = 5; // 처음 1 2 3 4 5 다음 , 
		int startNum = p; // DB Range Start Value
		
		// 총 몇 페이지가 필요한지 계산
		if(totalNum % itemPerPage > 0) {
			pageNum++;
		} else if(pageNum == 0) {
			pageNum = 1;
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
		inputMap.put("type", type);
		inputMap.put("title", titleInput);
		inputMap.put("author", authorInput);
		inputMap.put("start", startNum);
		inputMap.put("end", lastNum);
		resultMap = (ArrayList<HashMap<String, Object>>) qnaService.getList(inputMap);
		pinnedMap = (ArrayList<HashMap<String, Object>>) qnaService.getPinned();
		/*
		 * ModelAndView Input
		 * */
		mv.addObject("pinned", pinnedMap);
		mv.addObject("list", resultMap);
		mv.addObject("start", startPage);
		mv.addObject("type", type);
		mv.addObject("last", pageNum);
		mv.addObject("p", p); // 현재 페이지
		mv.addObject("keyword", keyword);
		mv.addObject("searchType", searchType);
		
		return mv;
	}
	
	@RequestMapping(value = {"/comment"}, method = RequestMethod.POST)
	public String comment(@RequestParam("comment") String cmt, 
			@RequestParam("id") String id,
			HttpServletRequest request) throws Exception {
		
		long bbs_id = Long.parseLong(id);
		Date date = new Date();
		
		/**
		 * HashMap<String, Object> loginMap = 
		 * 			(HashMap<String, Object>) session.getAttribute(LOGIN);
		 * "type safety unchecked cast from object" 경고
		 *  아래 코드로 대체
		 * */
		HttpSession session = request.getSession();
		HashMap<String, Object> loginMap = new HashMap<String, Object>();
		Object tmp = session.getAttribute(SessionNames.LOGIN);
		if(tmp instanceof HashMap<?,?>) {
			for(Map.Entry<?, ?> element : ((HashMap<?,?>) tmp).entrySet()) {
				loginMap.put((String)element.getKey(), element.getValue());
			}
		}
		
		String author_email = "";
		String author = "";
		
		//세션 값이 null이 아니면 로그인이 되어 있는 상태
		if(loginMap != null) {
			author = (String) loginMap.get("FULLNAME");
			author_email = (String) loginMap.get("EMAIL");
			Comment comment = new Comment(cmt, date, author_email, bbs_id, author);
			qnaService.insertComment(comment);
		}
		return "redirect:/detail?id=" + bbs_id;
	}
	
	@RequestMapping(value = {"/detail"}, method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam("id") long id, HttpServletRequest request) throws Exception {
		HashMap<String, Object> detailMap = new HashMap<String, Object>();
		ArrayList<HashMap<String, Object>> commentMap = new ArrayList<>();
		String email = "";
		BigDecimal user_id = new BigDecimal(0);
		
		/**
		 * HashMap<String, Object> loginMap = 
		 * 			(HashMap<String, Object>) session.getAttribute(LOGIN);
		 * "type safety unchecked cast from object" 경고
		 *  아래 코드로 대체
		 * */
		HttpSession session = request.getSession();
		HashMap<String, Object> loginMap = new HashMap<String, Object>();
		Object tmp = session.getAttribute(SessionNames.LOGIN);
		if(tmp instanceof HashMap<?,?>) {
			for(Map.Entry<?, ?> element : ((HashMap<?,?>) tmp).entrySet()) {
				loginMap.put((String)element.getKey(), element.getValue());
			}
		}
		
		if(loginMap != null) {
			email = (String) loginMap.get("EMAIL");
			user_id = (BigDecimal) loginMap.get("ID");
		}
		
		qnaService.updateView(id);
		detailMap = qnaService.getDetail(id);
		commentMap = (ArrayList<HashMap<String, Object>>) qnaService.getComment(id);
		ModelAndView mv = new ModelAndView("qna/detail");
		mv.addObject("title", "QnA");
		mv.addObject("list", detailMap);
		mv.addObject("comment", commentMap);
		mv.addObject("commentNum", commentMap.size()); // 총 댓글 수
		mv.addObject("email", email);
		mv.addObject("user_id", user_id);
		
		return mv;
	}
	
	@RequestMapping(value = {"/edit"}, method = RequestMethod.GET)
	public ModelAndView editView(@RequestParam("id") long id) throws Exception {
		HashMap<String, Object> detailMap = new HashMap<String, Object>();
		detailMap = qnaService.getDetail(id);
		ModelAndView mv = new ModelAndView("qna/edit");
		mv.addObject("title", "QnA");
		mv.addObject("list", detailMap);
		
		return mv;
	}
	
	@RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
	public String edit(@RequestParam("id") long id, @RequestParam("type") String type, 
			@RequestParam("title") String title, @RequestParam("content") String content) throws Exception {
		HashMap<String, Object> editMap = new HashMap<String, Object>();
		editMap.put("title", title);
		editMap.put("content", content);
		editMap.put("type", type);
		editMap.put("id", id);
		qnaService.updateList(editMap);
		
		return "redirect:/mypage?p=1&k=";
	}
	
	@RequestMapping(value = {"/create"}, method = RequestMethod.GET)
	public ModelAndView createView() throws Exception {
		ModelAndView mv = new ModelAndView("qna/create");
		mv.addObject("title", "질문 작성");
		return mv;
	}
	
	@RequestMapping(value = {"/create"}, method = RequestMethod.POST)
	public String create(@RequestParam("type") String type, 
			@RequestParam("title") String title,
			@RequestParam("content") String content, HttpServletRequest request) throws Exception {
		
		Date regdate = new Date();
		
		/**
		 * HashMap<String, Object> loginMap = 
		 * 			(HashMap<String, Object>) session.getAttribute(LOGIN);
		 * "type safety unchecked cast from object" 경고
		 *  아래 코드로 대체
		 * */
		HttpSession session = request.getSession();
		HashMap<String, Object> loginMap = new HashMap<String, Object>();
		Object tmp = session.getAttribute(SessionNames.LOGIN);
		if(tmp instanceof HashMap<?,?>) {
			for(Map.Entry<?, ?> element : ((HashMap<?,?>) tmp).entrySet()) {
				loginMap.put((String)element.getKey(), element.getValue());
			}
		}
		
		String author = (String) loginMap.get("FULLNAME");
		String auth_email = (String) loginMap.get("EMAIL");
		int likes = 0, pinned = 0;
		long hit = 0;
		
		Qna qna = new Qna(title, content, author, regdate, hit, likes, pinned, type, auth_email);	
		qnaService.create(qna);
		
		return "redirect:/main?p=1&t=car&k=&st=";
	}
	
	@RequestMapping(value = {"/delete"}, method = RequestMethod.POST)
	public String delete(@RequestParam("del-id") long[] ids) throws Exception {
		
		if(ids.length == 0){
			return "redirect:/mypage?p=1&k=";
		}
		List<Long> list = new ArrayList<Long>();
		
		for(long i: ids){
			list.add(i);
		}
		
		qnaService.deleteList(list);
		log.info("Delete Success");
		
		return "redirect:/mypage?p=1&k=";
	}
	
	@RequestMapping(value = {"/likes"}, method = RequestMethod.POST)
	public String likes(@RequestParam("bbs_id") long bbs_id, 
			@RequestParam("user_id") long user_id) throws Exception {
		
		HashMap<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("bbs_id", bbs_id);
		updateMap.put("id", bbs_id);
		
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		inputMap.put("bbs_id", bbs_id);
		inputMap.put("user_id", user_id);
		int ans = qnaService.checkLikes(inputMap);
		
		if(ans == 0) {
			qnaService.insertLikes(inputMap);
			qnaService.updateBbsLikes(updateMap);
			log.info("Likes Implemented");
		} else {
			qnaService.deleteLikes(inputMap);
			qnaService.updateBbsLikes(updateMap);
			log.info("UnLikes Implemented");
		}

		return "redirect:/detail?id="+bbs_id;
	}
	
	@RequestMapping(value = {"/commentDelete"}, method = RequestMethod.POST)
	public String commentDelete(@RequestParam("id") long id, 
			@RequestParam("bbs_id") long bbs_id, 
			@RequestParam("author_email") String author_email,
			HttpServletRequest request) throws Exception {
		
		/**
		 * HashMap<String, Object> loginMap = 
		 * 			(HashMap<String, Object>) session.getAttribute(LOGIN);
		 * "type safety unchecked cast from object" 경고
		 *  아래 코드로 대체
		 * */
		HttpSession session = request.getSession();
		HashMap<String, Object> loginMap = new HashMap<String, Object>();
		Object tmp = session.getAttribute(SessionNames.LOGIN);
		if(tmp instanceof HashMap<?,?>) {
			for(Map.Entry<?, ?> element : ((HashMap<?,?>) tmp).entrySet()) {
				loginMap.put((String)element.getKey(), element.getValue());
			}
		}
		
		String session_email = (String) loginMap.get("EMAIL");
		
		if(session_email.equals(author_email)) {
			qnaService.deleteComment(id);
		}

		return "redirect:/detail?id="+bbs_id;
	}
}
