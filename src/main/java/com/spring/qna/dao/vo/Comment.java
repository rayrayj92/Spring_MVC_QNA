package com.spring.qna.dao.vo;

import java.util.Date;

public class Comment {
	private long id;
	private String cmt;
	private Date regdate;
	private String author_id;
	private long bbs_id;
	private String author;
	
	public Comment(String cmt, Date regdate, String author_id, long bbs_id, String author) {
		this.cmt = cmt;
		this.regdate = regdate;
		this.author_id = author_id;
		this.bbs_id = bbs_id;
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCmt() {
		return cmt;
	}

	public void setCmt(String cmt) {
		this.cmt = cmt;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}

	public long getBbs_id() {
		return bbs_id;
	}

	public void setBbs_id(long bbs_id) {
		this.bbs_id = bbs_id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
