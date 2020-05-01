package com.spring.qna.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.qna.dao.AdminDAO;
import com.spring.qna.service.AdminService;

@Service("AdminService")
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminDAO adminDAO;

	@Override
	public void makePinned(long id) throws Exception {
		adminDAO.makePinned(id);
	}
}
