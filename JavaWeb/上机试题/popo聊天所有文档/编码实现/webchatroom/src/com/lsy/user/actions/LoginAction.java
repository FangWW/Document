/**
 * 
 */
package com.lsy.user.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import org.json.JSONObject;

import com.lsy.userinfo_actionform.UserActionForm;
import com.lsy.vo.User;
import com.sly.user.business.UserManager;
import com.lsy.filter.EncodingFilter;
import com.lsy.model.UserInfo;
import com.lsy.servlet.Messages;

/**
 * 用户登录验证
 */
public class LoginAction extends DispatchAction {

	/*
	 * 数据库查询，验证登录帐号，密码
	 */

	private String json;

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		UserActionForm uaf = (UserActionForm) form;
		//System.out.println("000000000跳到Action这边来了");
		User user = null;
		String qq = uaf.getQq();
		String password = uaf.getPassword();
		// json = new
		// String(request.getParameter("json").getBytes("ISO8859-1"),"UTF-8");
		// JSONObject jsonObj = new JSONObject(json); //将JSON格式的字符串构造成JSON对象
		// String qq = jsonObj.getString("qq"); //获取JSON对象中的qq属性的值
		// String password = jsonObj.getString("password");
		// //获取JSON对象中的password属性的值

		//System.out.println("qq=" + qq);
		UserInfo userinfo = UserInfo.getInstance(); // 获得UserInfo类的对象
		Vector vector = userinfo.getList();
		boolean flag = true; // 标记是否登录的变量
		// 判断用户是否登录
		//System.out.println("vector=" + vector);
		for (int i = 0; i < vector.size(); i++) {
			user = (User) vector.elementAt(i);
			
			if (qq.equals(user.getQq())) {

				PrintWriter out;
				try {
					out = response.getWriter();
					out
							.println("<script language='javascript'>alert('该用户已登录');window.location.href='index.jsp';</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
				flag = false;
				
				break;
			}
		}

		if (flag) {
			user = UserManager.getInstance().userLogin(qq, password);
		}

		if (user != null) {

			// 登录成功，转到聊天页面
			//System.out.println("User=" + user.getNickname() + " "+ user.getQq() + " " + user.getPhoto());
			// 将用户信息保存到session中
			session.setAttribute("user_info", user);
			 // 创建一个HashMap对象，用来保存每个用户的session id
			EncodingFilter.put(user.getQq(), session);
			response.sendRedirect("Messages?action=loginRoom");
			// return mapping.findForward("success");
			// response.sendRedirect("/chatjsp/MyJsp.jsp");
			// //request.getRequestDispatcher("../Messages?action=loginRoom").forward(request,
			// response);
			// System.out.println("oooo没有找到路径ooo");
			// //response.sendRedirect("Messages?action=loginRoom");

		} else {
			// 密码或id错误，要回到login.jsp页面
			PrintWriter out;
			try {
				out = response.getWriter();
				out
						.println("<script language='javascript'>alert('账号或密码错误，请重新登录');window.location.href='index.jsp';</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			// json = "{ msg:'登录失败,账号或密码错误!'}";

		}

		// response.setCharacterEncoding("UTF-8");
		// response.getWriter().write(json);
		return null;
	}

}
