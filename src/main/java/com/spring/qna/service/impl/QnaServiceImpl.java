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
	public void create(Qna qna) throws Exception {
		qnaDAO.create(qna);
	}

	@Override
	public List<HashMap<String, Object>> getList(HashMap<String, Object> inputMap) throws Exception {
		return qnaDAO.getList(inputMap);
	}

	@Override
	public int getTotalNum(HashMap<String, Object> inputMap) throws Exception {
		return qnaDAO.getTotalNum(inputMap);
	}

	@Override
	public HashMap<String, Object> getDetail(long id) throws Exception {
		return qnaDAO.getDetail(id);
	}

	@Override
	public void insertComment(Comment cmt) throws Exception {
		qnaDAO.insertComment(cmt);
	}

	@Override
	public List<HashMap<String, Object>> getComment(long id) throws Exception {
		return qnaDAO.getComment(id);
	}

	@Override
	public void updateView(long id) throws Exception {
		qnaDAO.updateView(id);
	}

	@Override
	public List<HashMap<String, Object>> getMyList(HashMap<String, Object> inputMap) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.getMyList(inputMap);
	}

	@Override
	public int getMyListTotalNum(HashMap<String, Object> inputMap) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.getMyListTotalNum(inputMap);
	}

	@Override
	public void updateList(HashMap<String, Object> inputMap) throws Exception {
		qnaDAO.updateList(inputMap);
	}

	@Override
	public void deleteList(List<Long> ids) throws Exception {
		qnaDAO.deleteList(ids);
	}

	@Override
	public List<HashMap<String, Object>> getPinned() throws Exception {
		return qnaDAO.getPinned();
	}

	@Override
	public int checkLikes(HashMap<String, Object> inputMap) throws Exception {
		return qnaDAO.checkLikes(inputMap);
	}

	@Override
	public void insertLikes(HashMap<String, Object> inputMap) throws Exception {
		qnaDAO.insertLikes(inputMap);
	}

	@Override
	public void updateBbsLikes(HashMap<String, Object> inputMap) throws Exception {
		qnaDAO.updateBbsLikes(inputMap);
	}

	@Override
	public void deleteLikes(HashMap<String, Object> inputMap) throws Exception {
		qnaDAO.deleteLikes(inputMap);
	}

}
