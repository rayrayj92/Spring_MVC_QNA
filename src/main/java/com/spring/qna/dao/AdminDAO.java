package com.spring.qna.dao;

public interface AdminDAO {
	// 공지사항으로 등록, Pinned
	public void makePinned(long id) throws Exception;
}
