package com.spring.qna.dao.vo;

import java.util.Date;

public class Qna {
	private long id;
	private String title;
	private String content;
	private String author;
	private Date regdate;
	private long hit;
	private int likes;
	private int pinned;
	private String type;
	private String author_email;
	
	public Qna() {
		// TODO Auto-generated constructor stub
	}

	public Qna(long id, String title, String content, String author, Date regdate, long hit, int likes, int pinned,
			String type, String author_email) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.regdate = regdate;
		this.hit = hit;
		this.likes = likes;
		this.pinned = pinned;
		this.type = type;
		this.author_email = author_email;
		
	}

	public Qna(String title, String content, String author, Date regdate, long hit, int likes, int pinned,
			String type, String author_email) {
		this.title = title;
		this.content = content;
		this.author = author;
		this.regdate = regdate;
		this.hit = hit;
		this.likes = likes;
		this.pinned = pinned;
		this.type = type;
		this.author_email = author_email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public long getHit() {
		return hit;
	}

	public void setHit(long hit) {
		this.hit = hit;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getPinned() {
		return pinned;
	}

	public void setPinned(int pinned) {
		this.pinned = pinned;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getAuthor_email() {
		return author_email;
	}

	public void setAuthor_email(String author_email) {
		this.author_email = author_email;
	}
	
}
