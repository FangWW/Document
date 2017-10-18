/**
 * 
 */
package com.lsy.user.actions;


import java.io.FileOutputStream;
import java.util.Random;

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

/**
 * @author 赖声渊
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
		
		IPTimeStamp its = new IPTimeStamp(request.getRemoteAddr()); // 产生由IP、时间，3位随机数组成的一个实例
		FormFile photo = (FormFile) uaf.getPhoto();// 获取到图片
		photoName=photo.getFileName();
		//若用户没有选择头像，则设为默认头像
		if (photoName.equals("")){
			
			photoName = "default_photo.jpg ";
			
		}
		
        String ph_last = photoName.split("\\.")[1];// 获得照片的后缀名
			// System.out.println("图片后缀名: "+ph_last );
		photoName = its.getIPTimeStampRand() + "." + ph_last;// 给图片自动重新命名
			//System.out.println("图片自动重新命名: " + photoName);
		
		
		//自动产生一个qq帐号
		 int number=new Random().nextInt(100000);
		 qq= "66".concat(String.valueOf(number));
		//将formbean中的数据赋值给user
		BeanUtils.copyProperties(user, uaf);
		user.setPhoto(photoName);
		user.setQq(qq);
		flag = UserManager.getInstance().userReg(user);
		
		//在指定的目录下创建photo文件夹
		java.io.File   myFilePath   =   new   java.io.File( request.getSession().getServletContext().getRealPath("/photo/")); 
	    if(!myFilePath.exists()) {
	          myFilePath.mkdir();
		 }
	    
		if (!photoName.equals("")) {

			//将图片保存到指定的文件夹中
			//FileOutputStream fos = new FileOutputStream (myFilePath+"/"+ photoName);
			// System.out.println("myFilePath: " + myFilePath);
			//fos.write(photo.getFileData());
			//fos.flush();
			//fos.close();
			//System.out.println("图片已上传完毕！！！");
		}
		request.setAttribute("qq", qq);
		request.setAttribute("flag", new Boolean(flag));
		return mapping.findForward("userReg_success");

	
		
	}

}
