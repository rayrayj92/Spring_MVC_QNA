package com.spring.qna.service;

public interface AdminService {
	// 공지사항으로 등록, Pinned
	public void makePinned(long id) throws Exception;
}
