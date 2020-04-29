package com.spring.qna.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.qna.dao.UserDAO;
import com.spring.qna.dao.vo.User;
import com.spring.qna.service.LoginService;

@Service("LoginService")
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	UserDAO userDAO;

	@Override
	public void insertUser(User user) {
		userDAO.insertUser(user);
		
	}

	@Override
	public HashMap<String, Object> getUser(HashMap<String, Object> input) {
		return userDAO.getUser(input);
	}

	@Override
	public int checkUser(String email) {
		return userDAO.checkUser(email);
	}

}
