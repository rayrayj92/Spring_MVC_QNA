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
	public void insertUser(User user) throws Exception {
		userDAO.insertUser(user);
	}

	@Override
	public HashMap<String, Object> getUser(String email) throws Exception {
		return userDAO.getUser(email);
	}

	@Override
	public int checkUser(String email) throws Exception {
		return userDAO.checkUser(email);
	}

}
