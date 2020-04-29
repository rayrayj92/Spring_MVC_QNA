package com.spring.qna.dao;

import java.util.HashMap;

import com.spring.qna.dao.vo.User;

public interface UserDAO {
	public void insertUser(User user);
	public HashMap<String, Object> getUser(HashMap<String, Object> input);
	public int checkUser(String email);
}
