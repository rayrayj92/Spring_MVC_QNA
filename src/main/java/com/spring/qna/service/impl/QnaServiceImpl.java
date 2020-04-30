package com.spring.qna.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.qna.dao.QnaDAO;
import com.spring.qna.dao.vo.Comment;
import com.spring.qna.dao.vo.Qna;
import com.spring.qna.service.QnaService;

@Service("QnaService")
public class QnaServiceImpl implements QnaService{
	
	@Autowired
	QnaDAO qnaDAO;
	
	@Override
	public void create(Qna qna) {
		qnaDAO.create(qna);
	}

	@Override
	public List<HashMap<String, Object>> getList(HashMap<String, Object> inputMap) {
		return qnaDAO.getList(inputMap);
	}

	@Override
	public int getTotalNum(HashMap<String, Object> inputMap) {
		return qnaDAO.getTotalNum(inputMap);
	}

	@Override
	public HashMap<String, Object> getDetail(long id) {
		return qnaDAO.getDetail(id);
	}

	@Override
	public void insertComment(Comment cmt) {
		qnaDAO.insertComment(cmt);
	}

	@Override
	public List<HashMap<String, Object>> getComment(long id) {
		return qnaDAO.getComment(id);
	}

	@Override
	public void updateView(long id) {
		qnaDAO.updateView(id);
	}

	@Override
	public List<HashMap<String, Object>> getMyList(HashMap<String, Object> inputMap) {
		// TODO Auto-generated method stub
		return qnaDAO.getMyList(inputMap);
	}

	@Override
	public int getMyListTotalNum(HashMap<String, Object> inputMap) {
		// TODO Auto-generated method stub
		return qnaDAO.getMyListTotalNum(inputMap);
	}

	@Override
	public void updateList(HashMap<String, Object> inputMap) {
		qnaDAO.updateList(inputMap);
	}

}
