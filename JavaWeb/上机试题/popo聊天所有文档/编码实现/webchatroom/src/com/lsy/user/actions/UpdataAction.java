package com.lsy.user.actions;

import com.lsy.userinfo_actionform.UserActionForm;
import com.lsy.util.IPTimeStamp;
import com.lsy.vo.User;
import com.sly.user.business.UserManager;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdataAction extends Action {

	/* �޸ĸ����û�����Ϣ
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
		// ������IP��ʱ�䣬3λ�������ɵ�һ��ʵ��
		 String ph_oldName = (String) request.getParameter("ph_oldName");// ��þ���Ƭ������
		 // System.out.println("����Ƭ������: "+ph_oldName );
		
		 FormFile photo = (FormFile) saf.getPhoto();// �ӱ��л�ȡ��Ƭ����Ϣ
		 BeanUtils.copyProperties(user, saf);// ���ռ��������赽��Bean��
		
		 if (!photo.getFileName().equals("")) {
		 String ph_last = photo.getFileName().split("\\.")[1];// �����Ƭ�ĺ�׺��
		 String photoName = its.getIPTimeStampRand() + "." + ph_last;// ��ͼƬ�Զ���������
		 user.setPhoto(photoName);// ����Ƭ�Ķ��ˣ�������Ƭ�������赽���У��浽���ݿ���
		 /* ����ɾ������Ƭ */
		 java.io.File f = new java.io.File(request.getSession()
		 .getServletContext().getRealPath("/photo/")+ ph_oldName);
		 if (f.exists()) {
		 f.delete();// ɾ��f·���µľ���Ƭ
		// System.out.println("��ͼƬ��ɾ����ϣ�����");
		 }
		
		 /* �����ĵ�����Ƭ���浽ָ����Ŀ¼�� */
		 FileOutputStream fos = new FileOutputStream(request.getSession()
		 .getServletContext().getRealPath("/photo/") + photoName);
		 fos.write(photo.getFileData());
		 fos.flush();
		 fos.close();
		
		 } else {
		 user.setPhoto(ph_oldName);// ��ͼƬû�б��Ķ�����ԭ����ͼƬ�����ٱ��浽���ݿ��У�
		 }
		
		 flag = UserManager.getInstance().updataUser(user);
		 if(flag){
			 
			 
			 response.sendRedirect("user.do?command=FindByQq&qq="+saf.getQq());
				
				
		 }else{
			 PrintWriter out;
				try {
					out = response.getWriter();
					out
							.println("<script language='javascript'>alert('��������æ�����ϸ���ʧ�ܣ����Ժ�....');window.location.href='updataUserInfo.jsp';</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
			 
		 }
		 
		 return null;
		 }
	

}
