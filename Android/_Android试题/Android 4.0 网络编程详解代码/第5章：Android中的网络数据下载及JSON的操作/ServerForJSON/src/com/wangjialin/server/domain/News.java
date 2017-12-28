package com.wangjialin.server.domain;

public class News {
	private Integer id;
	private String title;
	private Integer timelength;
	
	public News(Integer id, String title, Integer timelength) {
		this.id = id;
		this.title = title;
		this.timelength = timelength;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getTimelength() {
		return timelength;
	}
	public void setTimelength(Integer timelength) {
		this.timelength = timelength;
	}
	
}
