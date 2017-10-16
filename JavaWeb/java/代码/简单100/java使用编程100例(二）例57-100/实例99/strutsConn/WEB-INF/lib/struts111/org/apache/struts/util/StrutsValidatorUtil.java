///////////////////////////////////////////////////////////
// DeJaved by mDeJava v1.0. Copyright 1999 MoleSoftware. //
//       To download last version of this software:      //
//            http://molesoftware.hypermatr.net          //
//               e-mail:molesoftware@mail.ru             //
///////////////////////////////////////////////////////////

package org.apache.struts.util;

import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.validator.*;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.validator.Resources;

// Referenced classes of package org.apache.struts.util:
//            MessageResources

/* This class has attribute 'Deprecated' */

public class StrutsValidatorUtil
{

    public static String SERVLET_CONTEXT_KEY = "javax.servlet.ServletContext";
    public static String HTTP_SERVLET_REQUEST_KEY = "javax.servlet.http.HttpServletRequest";
    public static String ACTION_ERRORS_KEY = "org.apache.struts.action.ActionErrors";

    public StrutsValidatorUtil()
    {
    }

    public static ValidatorResources getValidatorResources(ServletContext application)
    {
        return Resources.getValidatorResources(application);
    }

    public static ValidatorResources getValidatorResources(ServletContext application, HttpServletRequest request)
    {
        return Resources.getValidatorResources(application, request);
    }

    public static MessageResources getMessageResources(ServletContext application)
    {
        return Resources.getMessageResources(application);
    }

    public static MessageResources getMessageResources(HttpServletRequest request)
    {
        return Resources.getMessageResources(request);
    }

    public static Locale getLocale(HttpServletRequest request)
    {
        return Resources.getLocale(request);
    }

    public static String getMessage(MessageResources messages, Locale locale, String key)
    {
        return Resources.getMessage(messages, locale, key);
    }

    public static String getMessage(HttpServletRequest request, String key)
    {
        return Resources.getMessage(request, key);
    }

    public static String getMessage(MessageResources messages, Locale locale, ValidatorAction va, Field field)
    {
        return Resources.getMessage(messages, locale, va, field);
    }

    public static ActionError getActionError(HttpServletRequest request, ValidatorAction va, Field field)
    {
        return Resources.getActionError(request, va, field);
    }

    public static String[] getArgs(String actionName, MessageResources messages, Locale locale, Field field)
    {
        return Resources.getArgs(actionName, messages, locale, field);
    }

    public static Validator initValidator(String key, Object bean, ServletContext application, HttpServletRequest request, ActionErrors errors, int page)
    {
        return Resources.initValidator(key, bean, application, request, errors, page);
    }

}
