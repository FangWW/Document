package com.webapp.upload;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
/**
 * <p>Title: 上传文件</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: UploadAction.java</p>
 * @author 杜江
 * @version 1.0
 */
public class UploadAction extends Action
{
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws Exception {

        if (form instanceof UploadForm) {

            UploadForm theForm = (UploadForm) form;

            //获取text数据
            String text = theForm.getTheText();

            //获取传递的参数
            String queryValue = theForm.getQueryParam();

            //获得上传的文件
            FormFile file = theForm.getTheFile();
            //获取上传文件名
            String fileName= file.getFileName();
            //获取上传文件类型
            String contentType = file.getContentType();
         
            //获取上传文件尺寸大小
            String size = (file.getFileSize() + " bytes");

            String data = null;

            try {
            	//获取保存文件路径，在web.xml中配置
            	String path = servlet.getServletConfig().getInitParameter("uploadpath");
                //获取文件数据
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                InputStream stream = file.getInputStream();
                //写入指定的文件
                 OutputStream bos = new FileOutputStream(path+fileName);
                 int bytesRead = 0;
                 byte[] buffer = new byte[8192];
                 while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                     bos.write(buffer, 0, bytesRead);
                 }
                 bos.close();
                 data =  path+fileName ;
                
                //关闭流
                stream.close();
            }
            catch (FileNotFoundException fnfe) {
                return null;
            }
            catch (IOException ioe) {
                return null;
            }

            //将数据保存到request以提供display.jsp文件实用
            request.setAttribute("text", text);
            request.setAttribute("queryValue", queryValue);
            request.setAttribute("fileName", fileName);
            request.setAttribute("contentType", contentType);
            request.setAttribute("size", size);
            request.setAttribute("data", data);

            //生成临时文件
            file.destroy();

            //显示display.jsp
            return mapping.findForward("display");
        }

        return null;
    }
}