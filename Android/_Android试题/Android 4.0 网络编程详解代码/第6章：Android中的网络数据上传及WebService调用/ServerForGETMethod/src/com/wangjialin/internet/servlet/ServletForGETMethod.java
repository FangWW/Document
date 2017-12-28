package com.wangjialin.internet.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ServletForGETMethod")
public class ServletForGETMethod extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String name= request.getParameter("name");
//			String name= new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
			String age= request.getParameter("age");
			System.out.println("name: " + name );
			System.out.println("age: " + age );
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
