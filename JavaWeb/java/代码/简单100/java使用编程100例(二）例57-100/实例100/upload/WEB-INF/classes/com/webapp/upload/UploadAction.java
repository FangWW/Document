package com.webapp.upload;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * <p>Title: �ϴ��ļ�</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: UploadAction.java</p>
 * @author �Ž�
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

            //��ȡtext����
            String text = theForm.getTheText();

            //��ȡ���ݵĲ���
            String queryValue = theForm.getQueryParam();

            //����ϴ����ļ�
            FormFile file = theForm.getTheFile();
            //��ȡ�ϴ��ļ���
            String fileName= file.getFileName();
            //��ȡ�ϴ��ļ�����
            String contentType = file.getContentType();
         
            //��ȡ�ϴ��ļ��ߴ��С
            String size = (file.getFileSize() + " bytes");

            String data = null;

            try {
            	//��ȡ�����ļ�·������web.xml������
            	String path = servlet.getServletConfig().getInitParameter("uploadpath");
                //��ȡ�ļ�����
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                InputStream stream = file.getInputStream();
                //д��ָ�����ļ�
                 OutputStream bos = new FileOutputStream(path+fileName);
                 int bytesRead = 0;
                 byte[] buffer = new byte[8192];
                 while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                     bos.write(buffer, 0, bytesRead);
                 }
                 bos.close();
                 data =  path+fileName ;
                
                //�ر���
                stream.close();
            }
            catch (FileNotFoundException fnfe) {
                return null;
            }
            catch (IOException ioe) {
                return null;
            }

            //�����ݱ��浽request���ṩdisplay.jsp�ļ�ʵ��
            request.setAttribute("text", text);
            request.setAttribute("queryValue", queryValue);
            request.setAttribute("fileName", fileName);
            request.setAttribute("contentType", contentType);
            request.setAttribute("size", size);
            request.setAttribute("data", data);

            //������ʱ�ļ�
            file.destroy();

            //��ʾdisplay.jsp
            return mapping.findForward("display");
        }

        return null;
    }
}