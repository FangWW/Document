/**
 * 
 */
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

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ����Ԩ
 *
 */
public class UserRegiterAction extends Action {

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		UserActionForm uaf=(UserActionForm) form;
		User user = new User();
		boolean flag = false;
		String photoName = "";
		String qq="";
		
		IPTimeStamp its = new IPTimeStamp(request.getRemoteAddr()); // ������IP��ʱ�䣬3λ�������ɵ�һ��ʵ��
		FormFile photo = (FormFile) uaf.getPhoto();// ��ȡ��ͼƬ
		photoName=photo.getFileName();
		//���û�û��ѡ��ͷ������ΪĬ��ͷ��
		if (photoName.equals("")){
			
			photoName = "default_photo.jpg ";
			
		}
		
        String ph_last = photoName.split("\\.")[1];// �����Ƭ�ĺ�׺��
			// System.out.println("ͼƬ��׺��: "+ph_last );
		photoName = its.getIPTimeStampRand() + "." + ph_last;// ��ͼƬ�Զ���������
			//System.out.println("ͼƬ�Զ���������: " + photoName);
		
		
		//�Զ�����һ��qq�ʺ�
		 int number=new Random().nextInt(100000);
		 qq= "66".concat(String.valueOf(number));
		//��formbean�е����ݸ�ֵ��user
		BeanUtils.copyProperties(user, uaf);
		user.setPhoto(photoName);
		user.setQq(qq);
		flag = UserManager.getInstance().userReg(user);
		
		//��ָ����Ŀ¼�´���photo�ļ���
		java.io.File   myFilePath   =   new   java.io.File( request.getSession().getServletContext().getRealPath("/photo/")); 
	    if(!myFilePath.exists()) {
	          myFilePath.mkdir();
		 }
	    
		if (!photoName.equals("")) {

			//��ͼƬ���浽ָ�����ļ�����
			//FileOutputStream fos = new FileOutputStream (myFilePath+"/"+ photoName);
			// System.out.println("myFilePath: " + myFilePath);
			//fos.write(photo.getFileData());
			//fos.flush();
			//fos.close();
			//System.out.println("ͼƬ���ϴ���ϣ�����");
		}
		request.setAttribute("qq", qq);
		request.setAttribute("flag", new Boolean(flag));
		return mapping.findForward("userReg_success");

	
		
	}

}
