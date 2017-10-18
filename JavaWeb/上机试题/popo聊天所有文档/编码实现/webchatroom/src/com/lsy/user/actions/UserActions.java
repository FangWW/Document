/**
 * 
 */
package com.lsy.user.actions;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


import com.lsy.userinfo_actionform.UserActionForm;
import com.lsy.vo.User;
import com.sly.user.business.UserManager;

/**
 * @author Administrator
 * 
 */
public class UserActions extends DispatchAction {

	/*
	 * 通过QQ账号查找好友的信息
	 */
	public ActionForward FindByQq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("GBK");
		HttpSession session = request.getSession();
		UserActionForm uaf = (UserActionForm) form;// 获取登陆账号
		String qq = uaf.getQq();

		User user_info = (User) session.getAttribute("user_info");
		String my_qq = user_info.getQq();// 得到自己的账号

		//System.out.println("12122qqqqq=" + qq);
		//System.out.println("my_qq=" + my_qq);

		User user = UserManager.getInstance().findById(qq);// 返回好友信息
		// 将好友信息保存到request中
		request.setAttribute("user_info", user);
		if (qq.equals(my_qq)) {
			return mapping.findForward("updata_success");
		} else {
			return mapping.findForward("findByQq_success");
		}
	}
	
}
	
