package com.wangjialin.server.service;

import java.util.List;

import com.wangjialin.server.domain.News;


public interface XMLService {

	/**
	 * 获取最新的视频资讯
	 * @return
	 */
	public List<News> getLastNews();

}