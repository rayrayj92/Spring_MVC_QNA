package com.spring.qna.controller.test;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.qna.dao.vo.Qna;
import com.spring.qna.service.QnaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class QnaControllerTest {
	
	@Autowired
	QnaService qnaService;
	
	private Qna qna = new Qna();
	
	@Before
	public void init() throws Exception {
		String title = "ABC";
		String content = "CBA";
		String author = "Jack";
		Date regdate = new Date();
		long hit = 123456;
		int likes = 12;
		int pinned = 1;
		String type = "car";
		String author_email = "abc@abc.com";
		
		qna.setTitle(title);
		qna.setContent(content);
		qna.setRegdate(regdate);
		qna.setAuthor(author);
		qna.setHit(hit);
		qna.setLikes(likes);
		qna.setPinned(pinned);
		qna.setType(type);
		qna.setAuthor_email(author_email);
	}

	@Test
	public void insert() throws Exception {
		qnaService.create(qna);
	}
}
