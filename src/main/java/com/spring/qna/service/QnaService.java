package com.spring.qna.service;

import java.util.HashMap;
import java.util.List;

import com.spring.qna.dao.vo.Comment;
import com.spring.qna.dao.vo.Qna;

public interface QnaService {
	public void create(Qna qna);
	public List<HashMap<String, Object>> getList(HashMap<String, Object> inputMap);
	public int getTotalNum();
	public HashMap<String, Object> getDetail(long id);
	public void insertComment(Comment cmt);
	public List<HashMap<String, Object>> getComment(long id);
}
