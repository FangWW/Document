package com.webapp.upload;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

/**
 * <p>Title: 上传文件</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: UploadForm.java</p>
 * @author 杜江
 * @version 1.0
 */
public class UploadForm extends ActionForm
{
    public static final String ERROR_PROPERTY_MAX_LENGTH_EXCEEDED = "org.apache.struts.webapp.upload.MaxLengthExceeded";

    protected String theText;
    protected String queryParam;
    protected FormFile theFile;

    public String getTheText() {
        return theText;
    }

    public void setTheText(String theText) {
        this.theText = theText;
    }

    public String getQueryParam() {
        return queryParam;
    }
    public void setQueryParam(String queryParam) {
        this.queryParam = queryParam;
    }

    public FormFile getTheFile() {
        return theFile;
    }

    public void setTheFile(FormFile theFile) {
        this.theFile = theFile;
    }
/**
 *<br>方法说明：数据校验
 *<br>输入参数：
 *<br>返回类型：
 */ 
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
    {
        ActionErrors errors = null;
        //has the maximum length been exceeded?
        Boolean maxLengthExceeded = (Boolean)
                request.getAttribute(MultipartRequestHandler.ATTRIBUTE_MAX_LENGTH_EXCEEDED);
        if ((maxLengthExceeded != null) && (maxLengthExceeded.booleanValue()))
        {
            errors = new ActionErrors();
            errors.add(ERROR_PROPERTY_MAX_LENGTH_EXCEEDED, new ActionError("maxLengthExceeded"));
        }
        return errors;

    }
}
