package com.lsy.user.actions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.lsy.userinfo_actionform.UserActionForm;
import com.lsy.util.IPTimeStamp;
import com.lsy.vo.User;
import com.sly.user.business.UserManager;

public class UpdataAction extends Action {

	/* 修改更新用户的信息
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 boolean flag = false;
		 UserActionForm saf = (UserActionForm) form;
		 User user = new User();
		 IPTimeStamp its = new IPTimeStamp(request.getRemoteAddr()); //
		// 产生由IP、时间，3位随机数组成的一个实例
		 String ph_oldName = (String) request.getParameter("ph_oldName");// 获得旧照片的名字
		 // System.out.println("旧照片的名字: "+ph_oldName );
		
		 FormFile photo = (FormFile) saf.getPhoto();// 从表单中获取照片的信息
		 BeanUtils.copyProperties(user, saf);// 将收集的数据设到表单Bean中
		
		 if (!photo.getFileName().equals("")) {
		 String ph_last = photo.getFileName().split("\\.")[1];// 获得照片的后缀名
		 String photoName = its.getIPTimeStampRand() + "." + ph_last;// 给图片自动重新命名
		 user.setPhoto(photoName);// 若照片改动了，将新照片的名字设到表单中，存到数据库中
		 /* 并且删除旧照片 */
		 java.io.File f = new java.io.File(request.getSession()
		 .getServletContext().getRealPath("/photo/")+ ph_oldName);
		 if (f.exists()) {
		 f.delete();// 删除f路径下的旧照片
		// System.out.println("旧图片已删除完毕！！！");
		 }
		
		 /* 将更改的新照片保存到指定的目录下 */
		 FileOutputStream fos = new FileOutputStream(request.getSession()
		 .getServletContext().getRealPath("/photo/") + photoName);
		 fos.write(photo.getFileData());
		 fos.flush();
		 fos.close();
		
		 } else {
		 user.setPhoto(ph_oldName);// 若图片没有被改动，将原来的图片名称再保存到数据库中，
		 }
		
		 flag = UserManager.getInstance().updataUser(user);
		 if(flag){
			 
			 
			 response.sendRedirect("user.do?command=FindByQq&qq="+saf.getQq());
				
				
		 }else{
			 PrintWriter out;
				try {
					out = response.getWriter();
					out
							.println("<script language='javascript'>alert('服务器繁忙，资料更新失败，请稍候....');window.location.href='updataUserInfo.jsp';</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
			 
		 }
		 
		 return null;
		 }
	

}
