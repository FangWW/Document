package com.wangjialin.server.service.implement;

import java.util.ArrayList;
import java.util.List;

import com.wangjialin.server.domain.News;
import com.wangjialin.server.service.XMLService;


public class XMLServiceBean implements XMLService {
	/**
	 * 获取最新的视频资讯
	 * @return
	 */
	public List<News> getLastNews(){
		List<News> newes = new ArrayList<News>();
		newes.add(new News(10, "wangjialin", 20));
		newes.add(new News(45, "jialingege", 10));
		newes.add(new News(89, "android", 50));
		return newes;
	}
}
