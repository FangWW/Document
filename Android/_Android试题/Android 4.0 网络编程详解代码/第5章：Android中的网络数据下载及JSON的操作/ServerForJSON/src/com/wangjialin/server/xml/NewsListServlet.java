package com.wangjialin.server.xml;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangjialin.server.domain.News;
import com.wangjialin.server.service.NewsService;
import com.wangjialin.server.service.implement.NewsServiceBean;


/**
 * Servlet implementation class NewsListServlet
 */
public class NewsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsService newsService = new NewsServiceBean();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<News> newes = newsService.getLastNews();//获取最新的视频资讯
			//   [{id:20,title:"xxx",timelength:90},{id:10,title:"xbx",timelength:20}]
			StringBuilder json = new StringBuilder();
			json.append('[');
			for(News news : newes){
				json.append('{');
				json.append("id:").append(news.getId()).append(",");
				json.append("title:\"").append(news.getTitle()).append("\",");
				json.append("timelength:").append(news.getTimelength());
				json.append("},");
			}
			json.deleteCharAt(json.length() - 1);
			json.append(']');
			request.setAttribute("json", json.toString());
			request.getRequestDispatcher("/WEB-INF/page/jsonnewslist.jsp").forward(request, response);
	}

}
