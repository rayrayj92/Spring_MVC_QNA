package com.spring.qna.dao;

import java.util.HashMap;
import java.util.List;

import com.spring.qna.dao.vo.Comment;
import com.spring.qna.dao.vo.Qna;

public interface QnaDAO {
	// 질문을 생성 - insert qna_bbs
	public void create(Qna qna) throws Exception;
	
	// 질문 리스트로 리턴
	public List<HashMap<String, Object>> getList(HashMap<String, Object> inputMap) throws Exception;
	
	// 총 질문 개수
	public int getTotalNum(HashMap<String, Object> inputMap) throws Exception;
	
	// 질문 내용 자세히 보기
	public HashMap<String, Object> getDetail(long id) throws Exception;
	
	// 답글 쓰기
	public void insertComment(Comment cmt) throws Exception;
	
	// 답글 리스트로 리턴
	public List<HashMap<String, Object>> getComment(long id) throws Exception;
	
	// 조회수 증가
	public void updateView(long id) throws Exception;
	
	// 내 질문들 리스트로 리턴
	public List<HashMap<String, Object>> getMyList(HashMap<String, Object> inputMap) throws Exception;
	
	// 내 질문 총 개수
	public int getMyListTotalNum(HashMap<String, Object> inputMap) throws Exception;
	
	// 질문 수정
	public void updateList(HashMap<String, Object> inputMap) throws Exception;
	
	// 질문 삭제
	public void deleteList(List<Long> ids) throws Exception;
	
	// 관리자가 Pinned한 공지사항 리스트로 리턴
	public List<HashMap<String, Object>> getPinned() throws Exception;
	
	// 이미 좋아요를 눌렀는지 확인
	public int checkLikes(HashMap<String, Object> inputMap) throws Exception;
	
	// 좋아요 삽입
	public void insertLikes(HashMap<String, Object> inputMap) throws Exception;
	
	// 좋아요 숫자 개수 수정
	public void updateBbsLikes(HashMap<String, Object> inputMap) throws Exception;
	
	// 좋아요 취소
	public void deleteLikes(HashMap<String, Object> inputMap) throws Exception;
}
