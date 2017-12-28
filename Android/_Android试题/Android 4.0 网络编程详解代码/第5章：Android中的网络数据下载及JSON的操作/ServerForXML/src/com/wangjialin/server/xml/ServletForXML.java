package com.wangjialin.server.xml;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangjialin.server.domain.News;
import com.wangjialin.server.service.XMLService;
import com.wangjialin.server.service.implement.XMLServiceBean;


/**
 * Servlet implementation class NewsListServlet
 */
public class ServletForXML extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private XMLService newsService = new XMLServiceBean();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<News> newes = newsService.getLastNews();//获取最新的视频资讯
		
			request.setAttribute("newes", newes);
			request.getRequestDispatcher("/WEB-INF/page/news.jsp").forward(request, response);
	}

}
