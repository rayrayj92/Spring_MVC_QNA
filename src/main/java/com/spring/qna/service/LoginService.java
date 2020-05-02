package com.spring.qna.service;

import java.util.HashMap;

import com.spring.qna.dao.vo.User;

public interface LoginService {
	// 회원 등록
	public void insertUser(User user) throws Exception;
	
	// 회원 HashMap으로 리턴
	public HashMap<String, Object> getUser(String email) throws Exception;
	
	// 이미 등록된 유지인지 이메일 체크
	public int checkUser(String email) throws Exception;
}
