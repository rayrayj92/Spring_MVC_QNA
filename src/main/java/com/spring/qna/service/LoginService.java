package com.spring.qna.service;

import java.util.HashMap;

import com.spring.qna.dao.vo.User;

public interface LoginService {
	public void insertUser(User user);
	public HashMap<String, Object> getUser(HashMap<String, Object> input);
	public int checkUser(String email);
}
