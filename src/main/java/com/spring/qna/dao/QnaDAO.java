package com.spring.qna.dao;

import java.util.HashMap;
import java.util.List;

import com.spring.qna.dao.vo.Comment;
import com.spring.qna.dao.vo.Qna;

public interface QnaDAO {
	// 질문을 생성 - insert qna_bbs
	public void create(Qna qna);
	
	// 질문 리스트로 리턴
	public List<HashMap<String, Object>> getList(HashMap<String, Object> inputMap);
	
	// 총 질문 개수
	public int getTotalNum(HashMap<String, Object> inputMap);
	
	// 질문 내용 자세히 보기
	public HashMap<String, Object> getDetail(long id);
	
	// 답글 쓰기
	public void insertComment(Comment cmt);
	
	// 답글 리스트로 리턴
	public List<HashMap<String, Object>> getComment(long id);
	
	// 조회수 증가
	public void updateView(long id);
	
	// 내 질문들 리스트로 리턴
	public List<HashMap<String, Object>> getMyList(HashMap<String, Object> inputMap);
	
	// 내 질문 총 개수
	public int getMyListTotalNum(HashMap<String, Object> inputMap);
	
	// 질문 수정
	public void updateList(HashMap<String, Object> inputMap);
}
