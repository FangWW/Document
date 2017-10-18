package com.lsy.servlet;

import com.lsy.filter.EncodingFilter;
import com.lsy.model.UserInfo;
import com.lsy.vo.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Messages extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getParameter("action");

		if ("loginRoom".equals(action)) { // 登录时，写入系统公告
			this.loginRoom(request, response);
		} else if ("sendMessage".equals(action)) { // 发送聊天信息
			this.sendMessages(request, response);
		} else if ("getMessages".equals(action)) { // 从XML文件中读取聊天信息
			this.getMessages(request, response);
		} else if ("getOwnMessages".equals(action)) { // 从XML文件中读取私聊信息
			this.getOwnMessages(request, response);
		}
	}

	// 登录时，写入系统公告

	public void loginRoom(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		User user_info = (User) session.getAttribute("user_info");
		String user_qq = user_info.getQq(); // 获得登录QQ账号
		String nickname = user_info.getNickname(); // 获得昵称
		UserInfo user = UserInfo.getInstance(); // 获得UserInfo类的对象
		session.setMaxInactiveInterval(600);		//设置Session的过期时间为10分钟
		// 保存用户信息

		UserListener ul = new UserListener(); // 创建UserListener的对象
		ul.setUser(user_info); // 添加用户整个对象（用户包括：qq、nickname、photo 等）
		user.addUser(ul.getUser()); // 添加用户用户整个对象到UserInfo类的对象中
		session.setMaxInactiveInterval(600);		//设置Session的过期时间为10分钟
		session.setAttribute("user", ul); // 将UserListener对象绑定到Session中
		session.setAttribute("user_qq", user_qq);// 保存当前登录的用户名
		session.setAttribute("nickname", nickname);
		session.setAttribute("loginTime", new Date().toLocaleString()); // 保存登录时间
		ServletContext application = getServletContext();

		String sourceMessage = "";

		if (null != application.getAttribute("message")) {
			sourceMessage = application.getAttribute("message").toString();// 获取Application中的信息
		}
		sourceMessage += "系统公告：<font color='gray'> " + nickname + "(" + user_qq
				+ ")走进了聊天室！</font><br>";
		application.setAttribute("message", sourceMessage); // 将更新后的信息再次保存到Application中
		try {
			request.getRequestDispatcher("login_ok.jsp").forward(request,
					response);
		} catch (Exception ex) {
			Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null,
					ex);
		}

	}

	// 发送聊天信息
	@SuppressWarnings("unchecked")
	public void sendMessages(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Random random = new Random();
		String from = request.getParameter("from"); // 发言人
		String face = request.getParameter("face"); // 表情
		String to = request.getParameter("to"); // 接收者
		String color = request.getParameter("color"); // 字体颜色
		String content = request.getParameter("content"); // 发言内容
		String sendTime = new Date().toLocaleString(); // 发言时间
		ServletContext application = getServletContext();
		String key = "";
		String nickname="";
		if (!to.equals("所有人")) {
			int len = to.length();
			key = to.substring(len - 8, len - 1);
            nickname= to.substring(0,len - 9);
           
		}
		// 实现公聊，将公聊的内容存放到application
		if (to.equals("所有人") || "".equals(to)) {

			// System.out.println("公聊to ==" + to);
			String sourceMessage = application.getAttribute("message").toString();
			try {
				// 发言时间
				sourceMessage += "<font color='blue'><strong>" + from
						+ "</strong></font><font color='#CC0000'>" + " " + face
						+ " " + "<font color='green'>说</font>    " + "</font>（"
						+ sendTime + "）<br>" + "<font color='" + color + "'>"
						+ "  " + content + "<br>";
				application.setAttribute("message", sourceMessage);
				request.getRequestDispatcher(
						"Messages?action=getMessages&nocache="
								+ random.nextInt(10000)).forward(request,
						response);
			} catch (Exception ex) {
				Logger.getLogger(Messages.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		} else {
			// 实现私聊，将私聊内容存放到session中
			String ownMessage = "";
			String Msg = "";
			HttpSession usersession = EncodingFilter.getValue(key);
			
			// if (null != usersession.getAttribute("ownMessage")) {
			// ownMessage = usersession.getAttribute("ownMessage").toString();
			// // 获取各个用户的session中的信息
			// System.out.println("null!=ownMessage--------->"+ownMessage);
			// }

			try {
				// 将私聊信息保存到与私聊人的session中

				{
					ownMessage = "<font color='blue'><strong>" + from
							+ "</strong></font><font color='#CC0000'>" + "  "
							+ face + "  " + "</font>对我说<font color='green'>"
							+ "</font>（" + sendTime + "）<br>" + "<font color='"
							+ color + "'>" + "  " + content + "<br>";

					// 保存私聊信息（数组）
					List listsession = (List) usersession.getAttribute("ownMessage");
					if (listsession == null) {
						listsession = new ArrayList();
					}
					listsession.add(ownMessage);
					usersession.setAttribute("ownMessage", listsession);
					//session.setAttribute("ownMessage", listsession);
				}

				{
					Msg = "<font color='blue'><strong> 我" + " "
							+ "</strong></font><font color='#CC0000'>" + "  "
							+ face + "  " + "</font>对"+" "+ nickname +"说<font color='green'>"
							+ "</font>（" + sendTime + "）<br>" + "<font color='"
							+ color + "'>" + "  " + content + "<br>";

					// 保存私聊信息（数组）
					List listsession = (List) session.getAttribute("ownMessage");
					if (listsession == null) {
						listsession = new ArrayList();
					}
					listsession.add(Msg);
					session.setAttribute("ownMessage", listsession);
				}

				// session.setAttribute("ownmessage", ownMessage);
				request.getRequestDispatcher(
						"Messages?action=getOwnMessages&nocache="
								+ random.nextInt(10000)).forward(request,
						response);
			} catch (Exception ex) {
				Logger.getLogger(Messages.class.getName()).log(Level.SEVERE,
						null, ex);
			}

		}

	}

	// 将页面重定向到显示聊天信息的页面
	public void getMessages(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		// 转向公聊页面

		try {

			request.getRequestDispatcher("content.jsp").forward(request,
					response);

		} catch (Exception ex) {
			Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null,
					ex);
		}

	}

	// 将页面重定向到显示聊天信息的私聊页面
	public void getOwnMessages(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");

		try {

			request.getRequestDispatcher("owncontent.jsp").forward(request,
					response);

		} catch (Exception ex) {
			Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null,
					ex);
		}

	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet 方法。单击左侧的 + 号以编辑代码。">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
