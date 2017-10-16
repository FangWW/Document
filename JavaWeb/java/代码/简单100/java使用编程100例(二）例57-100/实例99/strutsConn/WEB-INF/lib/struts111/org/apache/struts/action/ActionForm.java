///////////////////////////////////////////////////////////
// DeJaved by mDeJava v1.0. Copyright 1999 MoleSoftware. //
//       To download last version of this software:      //
//            http://molesoftware.hypermatr.net          //
//               e-mail:molesoftware@mail.ru             //
///////////////////////////////////////////////////////////

package org.apache.struts.action;

import java.io.Serializable;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.upload.MultipartRequestHandler;

// Referenced classes of package org.apache.struts.action:
//            ActionServletWrapper, ActionServlet, ActionMapping, ActionErrors

public abstract class ActionForm
    implements Serializable
{

    protected transient ActionServlet servlet = null;
    protected transient MultipartRequestHandler multipartRequestHandler = null;

    public ActionForm()
    {
        servlet = null;
    }

    protected ActionServlet getServlet()
    {
        return servlet;
    }

    public ActionServletWrapper getServletWrapper()
    {
        return new ActionServletWrapper(getServlet());
    }

    public MultipartRequestHandler getMultipartRequestHandler()
    {
        return multipartRequestHandler;
    }

    public void setServlet(ActionServlet servlet)
    {
        this.servlet = servlet;
    }

    public void setMultipartRequestHandler(MultipartRequestHandler multipartRequestHandler)
    {
        this.multipartRequestHandler = multipartRequestHandler;
    }

    public void reset(ActionMapping mapping, ServletRequest request)
    {
        try
        {
            reset(mapping, (HttpServletRequest)request);
        }
        catch(ClassCastException e) { }
    }

    public void reset(ActionMapping actionmapping, HttpServletRequest httpservletrequest)
    {
    }

    public ActionErrors validate(ActionMapping mapping, ServletRequest request)
    {
        return validate(mapping, (HttpServletRequest)request);
        ClassCastException e;
        e;
        return null;
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
    {
        return null;
    }
}
