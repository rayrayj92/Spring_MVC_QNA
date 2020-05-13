package com.spring.qna.controller.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.spring.qna.service.QnaService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/db-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class QnaControllerTest {
	
	@Autowired
	QnaService qnaService;
	
	@Before
	public void init() throws Exception {

	}

	@Test
	public void read() throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = qnaService.getDetail(243L);
		assertEquals("관리자", resultMap.get("AUTHOR"));
	}
	
	@Test
	public void countComment() throws Exception{
		ArrayList<HashMap<String, Object>> resultMap = new ArrayList<>();
		resultMap = (ArrayList<HashMap<String, Object>>) qnaService.countComment();
		System.out.println(resultMap.stream().map(p -> p.get("CNT")).collect(Collectors.toList()));
	}
}
