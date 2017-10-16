///////////////////////////////////////////////////////////
// DeJaved by mDeJava v1.0. Copyright 1999 MoleSoftware. //
//       To download last version of this software:      //
//            http://molesoftware.hypermatr.net          //
//               e-mail:molesoftware@mail.ru             //
///////////////////////////////////////////////////////////

package org.apache.struts.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import org.apache.commons.beanutils.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;
import org.apache.struts.config.*;
import org.apache.struts.upload.MultipartRequestHandler;
import org.apache.struts.upload.MultipartRequestWrapper;

// Referenced classes of package org.apache.struts.util:
//            MessageResources, ErrorMessages

public class RequestUtils
{

    protected static Log log = null;
    private static MessageResources messages = MessageResources.getMessageResources("org.apache.struts.util.LocalStrings");
    private static final String PREFIXES_KEY = "org.apache.struts.util.PREFIXES";
    private static Method encode = null;
    private static Map scopes = null;
    static Class class$org$apache$struts$util$RequestUtils = null; /* synthetic field */
    static Class class$java$lang$String = null; /* synthetic field */
    static Class class$java$net$URLEncoder = null; /* synthetic field */

    public RequestUtils()
    {
    }

    public static URL absoluteURL(HttpServletRequest request, String path)
        throws MalformedURLException
    {
        return new URL(serverURL(request), request.getContextPath() + path);
    }

    public static Class applicationClass(String className)
        throws ClassNotFoundException
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if(classLoader == null)
            classLoader = (class$org$apache$struts$util$RequestUtils != null ? class$org$apache$struts$util$RequestUtils : (class$org$apache$struts$util$RequestUtils = class$("org.apache.struts.util.RequestUtils"))).getClassLoader();
        return classLoader.loadClass(className);
    }

    public static Object applicationInstance(String className)
        throws ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        return applicationClass(className).newInstance();
    }

    public static Map computeParameters(PageContext pageContext, String paramId, String paramName, String paramProperty, String paramScope, String name, String property, String scope, 
            boolean transaction)
        throws JspException
    {
        if(paramId == null && name == null && !transaction)
            return null;
        Map map = null;
        try
        {
            if(name != null)
                map = (Map)lookup(pageContext, name, property, scope);
        }
        catch(ClassCastException e)
        {
            saveException(pageContext, e);
            throw new JspException(messages.getMessage("parameters.multi", name, property, scope));
        }
        catch(JspException e)
        {
            saveException(pageContext, e);
            throw e;
        }
        Map results = null;
        if(map != null)
            results = new HashMap(map);
        else
            results = new HashMap();
        if(paramId != null && paramName != null)
        {
            Object paramValue = null;
            try
            {
                paramValue = lookup(pageContext, paramName, paramProperty, paramScope);
            }
            catch(JspException e)
            {
                saveException(pageContext, e);
                throw e;
            }
            if(paramValue != null)
            {
                String paramString = null;
                if(paramValue instanceof String)
                    paramString = (String)paramValue;
                else
                    paramString = paramValue.toString();
                Object mapValue = results.get(paramId);
                if(mapValue == null)
                    results.put(paramId, paramString);
                else
                if(mapValue instanceof String)
                {
                    String newValues[] = new String[2];
                    newValues[0] = (String)mapValue;
                    newValues[1] = paramString;
                    results.put(paramId, newValues);
                }
                else
                {
                    String oldValues[] = (String[])mapValue;
                    String newValues[] = new String[oldValues.length + 1];
                    System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
                    newValues[oldValues.length] = paramString;
                    results.put(paramId, newValues);
                }
            }
        }
        if(transaction)
        {
            HttpSession session = pageContext.getSession();
            String token = null;
            if(session != null)
                token = (String)session.getAttribute("org.apache.struts.action.TOKEN");
            if(token != null)
                results.put("org.apache.struts.taglib.html.TOKEN", token);
        }
        return results;
    }

    public static String computeURL(PageContext pageContext, String forward, String href, String page, Map params, String anchor, boolean redirect)
        throws MalformedURLException
    {
        return computeURL(pageContext, forward, href, page, null, params, anchor, redirect);
    }

    public static String computeURL(PageContext pageContext, String forward, String href, String page, String action, Map params, String anchor, boolean redirect)
        throws MalformedURLException
    {
        return computeURL(pageContext, forward, href, page, action, params, anchor, redirect, true);
    }

    public static String computeURL(PageContext pageContext, String forward, String href, String page, String action, Map params, String anchor, boolean redirect, 
            boolean encodeSeparator)
        throws MalformedURLException
    {
        int n = 0;
        if(forward != null)
            n++;
        if(href != null)
            n++;
        if(page != null)
            n++;
        if(action != null)
            n++;
        if(n != 1)
            throw new MalformedURLException(messages.getMessage("computeURL.specifier"));
        ModuleConfig config = (ModuleConfig)pageContext.getRequest().getAttribute("org.apache.struts.action.MODULE");
        if(config == null)
        {
            config = (ModuleConfig)pageContext.getServletContext().getAttribute("org.apache.struts.action.MODULE");
            pageContext.getRequest().setAttribute("org.apache.struts.action.MODULE", config);
        }
        StringBuffer url = new StringBuffer();
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        if(forward != null)
        {
            ForwardConfig fc = config.findForwardConfig(forward);
            if(fc == null)
                throw new MalformedURLException(messages.getMessage("computeURL.forward", forward));
            if(fc.getRedirect())
                redirect = true;
            if(fc.getPath().startsWith("/"))
            {
                url.append(request.getContextPath());
                url.append(forwardURL(request, fc));
            }
            else
            {
                url.append(fc.getPath());
            }
        }
        else
        if(href != null)
            url.append(href);
        else
        if(action != null)
        {
            url.append(getActionMappingURL(action, pageContext));
        }
        else
        {
            url.append(request.getContextPath());
            url.append(pageURL(request, page));
        }
        if(anchor != null)
        {
            String temp = url.toString();
            int hash = temp.indexOf(35);
            if(hash >= 0)
                url.setLength(hash);
            url.append('#');
            url.append(encodeURL(anchor));
        }
        if(params != null && params.size() > 0)
        {
            String temp = url.toString();
            int hash = temp.indexOf(35);
            if(hash >= 0)
            {
                anchor = temp.substring(hash + 1);
                url.setLength(hash);
                temp = url.toString();
            }
            else
            {
                anchor = null;
            }
            String separator = null;
            if(redirect)
                separator = "&";
            else
            if(encodeSeparator)
                separator = "&amp;";
            else
                separator = "&";
            boolean question = temp.indexOf(63) >= 0;
            for(Iterator keys = params.keySet().iterator(); keys.hasNext();)
            {
                String key = (String)keys.next();
                Object value = params.get(key);
                if(value == null)
                {
                    if(!question)
                    {
                        url.append('?');
                        question = true;
                    }
                    else
                    {
                        url.append(separator);
                    }
                    url.append(encodeURL(key));
                    url.append('=');
                }
                else
                if(value instanceof String)
                {
                    if(!question)
                    {
                        url.append('?');
                        question = true;
                    }
                    else
                    {
                        url.append(separator);
                    }
                    url.append(encodeURL(key));
                    url.append('=');
                    url.append(encodeURL((String)value));
                }
                else
                if(value instanceof String[])
                {
                    String values[] = (String[])value;
                    for(int i = 0; i < values.length; i++)
                    {
                        if(!question)
                        {
                            url.append('?');
                            question = true;
                        }
                        else
                        {
                            url.append(separator);
                        }
                        url.append(encodeURL(key));
                        url.append('=');
                        url.append(encodeURL(values[i]));
                    }

                }
                else
                {
                    if(!question)
                    {
                        url.append('?');
                        question = true;
                    }
                    else
                    {
                        url.append(separator);
                    }
                    url.append(encodeURL(key));
                    url.append('=');
                    url.append(encodeURL(value.toString()));
                }
            }

            if(anchor != null)
            {
                url.append('#');
                url.append(encodeURL(anchor));
            }
        }
        if(pageContext.getSession() != null)
        {
            HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
            if(redirect)
                return response.encodeRedirectURL(url.toString());
            else
                return response.encodeURL(url.toString());
        }
        else
        {
            return url.toString();
        }
    }

    public static String getActionMappingName(String action)
    {
        String value = action;
        int question = action.indexOf("?");
        if(question >= 0)
            value = value.substring(0, question);
        int slash = value.lastIndexOf("/");
        int period = value.lastIndexOf(".");
        if(period >= 0 && period > slash)
            value = value.substring(0, period);
        if(value.startsWith("/"))
            return value;
        else
            return "/" + value;
    }

    public static String getActionMappingURL(String action, PageContext pageContext)
    {
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        StringBuffer value = new StringBuffer(request.getContextPath());
        ModuleConfig config = (ModuleConfig)pageContext.getRequest().getAttribute("org.apache.struts.action.MODULE");
        if(config != null)
            value.append(config.getPrefix());
        String servletMapping = (String)pageContext.getAttribute("org.apache.struts.action.SERVLET_MAPPING", 4);
        if(servletMapping != null)
        {
            String queryString = null;
            int question = action.indexOf("?");
            if(question >= 0)
                queryString = action.substring(question);
            String actionMapping = getActionMappingName(action);
            if(servletMapping.startsWith("*."))
            {
                value.append(actionMapping);
                value.append(servletMapping.substring(1));
            }
            else
            if(servletMapping.endsWith("/*"))
            {
                value.append(servletMapping.substring(0, servletMapping.length() - 2));
                value.append(actionMapping);
            }
            else
            if(servletMapping.equals("/"))
                value.append(actionMapping);
            if(queryString != null)
                value.append(queryString);
        }
        else
        {
            if(!action.startsWith("/"))
                value.append("/");
            value.append(action);
        }
        return value.toString();
    }

    public static ActionForm createActionForm(HttpServletRequest request, ActionMapping mapping, ModuleConfig moduleConfig, ActionServlet servlet)
    {
        FormBeanConfig config;
        ActionForm instance;
        String attribute = mapping.getAttribute();
        if(attribute == null)
            return null;
        String name = mapping.getName();
        config = moduleConfig.findFormBeanConfig(name);
        if(config == null)
            return null;
        if(log.isDebugEnabled())
            log.debug(" Looking for ActionForm bean instance in scope '" + mapping.getScope() + "' under attribute key '" + attribute + "'");
        instance = null;
        HttpSession session = null;
        if("request".equals(mapping.getScope()))
        {
            instance = (ActionForm)request.getAttribute(attribute);
        }
        else
        {
            session = request.getSession();
            instance = (ActionForm)session.getAttribute(attribute);
        }
        if(instance == null)
            break MISSING_BLOCK_LABEL_404;
        if(config.getDynamic())
        {
            String className = ((DynaBean)instance).getDynaClass().getName();
            if(className.equals(config.getName()))
            {
                if(log.isDebugEnabled())
                {
                    log.debug(" Recycling existing DynaActionForm instance of type '" + className + "'");
                    log.trace(" --> " + instance);
                }
                return instance;
            }
            break MISSING_BLOCK_LABEL_404;
        }
        Class configClass = applicationClass(config.getType());
        if(!configClass.isAssignableFrom(instance.getClass()))
            break MISSING_BLOCK_LABEL_404;
        if(log.isDebugEnabled())
        {
            log.debug(" Recycling existing ActionForm instance of class '" + instance.getClass().getName() + "'");
            log.trace(" --> " + instance);
        }
        return instance;
        Throwable t;
        t;
        log.error(servlet.getInternal().getMessage("formBean", config.getType()), t);
        return null;
        if(config.getDynamic())
            try
            {
                DynaActionFormClass dynaClass = DynaActionFormClass.createDynaActionFormClass(config);
                instance = (ActionForm)dynaClass.newInstance();
                ((DynaActionForm)instance).initialize(mapping);
                if(log.isDebugEnabled())
                {
                    log.debug(" Creating new DynaActionForm instance of type '" + config.getType() + "'");
                    log.trace(" --> " + instance);
                }
            }
            // Misplaced declaration of exception variable
            catch(DynaActionFormClass dynaClass)
            {
                log.error(servlet.getInternal().getMessage("formBean", config.getType()), dynaClass);
                return null;
            }
        else
            try
            {
                instance = (ActionForm)applicationInstance(config.getType());
                if(log.isDebugEnabled())
                {
                    log.debug(" Creating new ActionForm instance of type '" + config.getType() + "'");
                    log.trace(" --> " + instance);
                }
            }
            // Misplaced declaration of exception variable
            catch(DynaActionFormClass dynaClass)
            {
                log.error(servlet.getInternal().getMessage("formBean", config.getType()), dynaClass);
                return null;
            }
        instance.setServlet(servlet);
        return instance;
    }

    public static Object lookup(PageContext pageContext, String name, String scopeName)
        throws JspException
    {
        if(scopeName == null)
            return pageContext.findAttribute(name);
        return pageContext.getAttribute(name, getScope(scopeName));
        JspException e;
        e;
        saveException(pageContext, e);
        throw e;
    }

    public static int getScope(String scopeName)
        throws JspException
    {
        Integer scope = (Integer)scopes.get(scopeName.toLowerCase());
        if(scope == null)
            throw new JspException(messages.getMessage("lookup.scope", scope));
        else
            return scope.intValue();
    }

    public static Object lookup(PageContext pageContext, String name, String property, String scope)
        throws JspException
    {
        Object bean;
        bean = lookup(pageContext, name, scope);
        if(bean == null)
        {
            JspException e = null;
            if(scope == null)
                e = new JspException(messages.getMessage("lookup.bean.any", name));
            else
                e = new JspException(messages.getMessage("lookup.bean", name, scope));
            saveException(pageContext, e);
            throw e;
        }
        if(property == null)
            return bean;
        return PropertyUtils.getProperty(bean, property);
        IllegalAccessException e;
        e;
        saveException(pageContext, e);
        throw new JspException(messages.getMessage("lookup.access", property, name));
        InvocationTargetException e;
        e;
        Throwable t = e.getTargetException();
        if(t == null)
            t = e;
        saveException(pageContext, t);
        throw new JspException(messages.getMessage("lookup.target", property, name));
        NoSuchMethodException e;
        e;
        saveException(pageContext, e);
        throw new JspException(messages.getMessage("lookup.method", property, name));
    }

    public static Locale retrieveUserLocale(PageContext pageContext, String locale)
    {
        Locale userLocale = null;
        HttpSession session = pageContext.getSession();
        if(locale == null)
            locale = "org.apache.struts.action.LOCALE";
        if(session != null)
            userLocale = (Locale)session.getAttribute(locale);
        if(userLocale == null)
            userLocale = pageContext.getRequest().getLocale();
        return userLocale;
    }

    public static String message(PageContext pageContext, String bundle, String locale, String key)
        throws JspException
    {
        return message(pageContext, bundle, locale, key, null);
    }

    public static String message(PageContext pageContext, String bundle, String locale, String key, Object args[])
        throws JspException
    {
        MessageResources resources = retrieveMessageResources(pageContext, bundle, false);
        Locale userLocale = retrieveUserLocale(pageContext, locale);
        if(args == null)
            return resources.getMessage(userLocale, key);
        else
            return resources.getMessage(userLocale, key, args);
    }

    private static MessageResources retrieveMessageResources(PageContext pageContext, String bundle, boolean checkPageScope)
        throws JspException
    {
        MessageResources resources = null;
        if(bundle == null)
            bundle = "org.apache.struts.action.MESSAGE";
        if(checkPageScope)
            resources = (MessageResources)pageContext.getAttribute(bundle, 1);
        if(resources == null)
            resources = (MessageResources)pageContext.getAttribute(bundle, 2);
        if(resources == null)
            resources = (MessageResources)pageContext.getAttribute(bundle, 4);
        if(resources == null)
        {
            JspException e = new JspException(messages.getMessage("message.bundle", bundle));
            saveException(pageContext, e);
            throw e;
        }
        else
        {
            return resources;
        }
    }

    public static void populate(Object bean, HttpServletRequest request)
        throws ServletException
    {
        populate(bean, null, null, request);
    }

    public static void populate(Object bean, String prefix, String suffix, HttpServletRequest request)
        throws ServletException
    {
        HashMap properties = new HashMap();
        Enumeration names = null;
        Map multipartParameters = null;
        String contentType = request.getContentType();
        String method = request.getMethod();
        boolean isMultipart = false;
        if(contentType != null && contentType.startsWith("multipart/form-data") && method.equalsIgnoreCase("POST"))
        {
            ActionServletWrapper servlet;
            if(bean instanceof ActionForm)
                servlet = ((ActionForm)bean).getServletWrapper();
            else
                throw new ServletException("bean that's supposed to be populated from a multipart request is not of type \"org.apache.struts.action.ActionForm\", but type \"" + bean.getClass().getName() + "\"");
            MultipartRequestHandler multipartHandler = getMultipartHandler(request);
            ((ActionForm)bean).setMultipartRequestHandler(multipartHandler);
            if(multipartHandler != null)
            {
                isMultipart = true;
                servlet.setServletFor(multipartHandler);
                multipartHandler.setMapping((ActionMapping)request.getAttribute("org.apache.struts.action.mapping.instance"));
                multipartHandler.handleRequest(request);
                Boolean maxLengthExceeded = (Boolean)request.getAttribute("org.apache.struts.upload.MaxLengthExceeded");
                if(maxLengthExceeded != null && maxLengthExceeded.booleanValue())
                    return;
                multipartParameters = getAllParametersForMultipartRequest(request, multipartHandler);
                names = Collections.enumeration(multipartParameters.keySet());
            }
        }
        if(!isMultipart)
            names = request.getParameterNames();
        while(names.hasMoreElements()) 
        {
            String name = (String)names.nextElement();
            String stripped = name;
            if(prefix != null)
            {
                if(!stripped.startsWith(prefix))
                    continue;
                stripped = stripped.substring(prefix.length());
            }
            if(suffix != null)
            {
                if(!stripped.endsWith(suffix))
                    continue;
                stripped = stripped.substring(0, stripped.length() - suffix.length());
            }
            if(isMultipart)
                properties.put(stripped, multipartParameters.get(name));
            else
                properties.put(stripped, request.getParameterValues(name));
        }

        try
        {
            BeanUtils.populate(bean, properties);
        }
        catch(Exception e)
        {
            throw new ServletException("BeanUtils.populate", e);
        }
    }

    private static MultipartRequestHandler getMultipartHandler(HttpServletRequest request)
        throws ServletException
    {
        MultipartRequestHandler multipartHandler = null;
        String multipartClass = (String)request.getAttribute("org.apache.struts.action.mapping.multipartclass");
        request.removeAttribute("org.apache.struts.action.mapping.multipartclass");
        if(multipartClass != null)
        {
            try
            {
                multipartHandler = (MultipartRequestHandler)applicationInstance(multipartClass);
            }
            catch(ClassNotFoundException cnfe)
            {
                log.error("MultipartRequestHandler class \"" + multipartClass + "\" in mapping class not found, " + "defaulting to global multipart class");
            }
            catch(InstantiationException ie)
            {
                log.error("InstantiaionException when instantiating MultipartRequestHandler \"" + multipartClass + "\", " + "defaulting to global multipart class, exception: " + ie.getMessage());
            }
            catch(IllegalAccessException iae)
            {
                log.error("IllegalAccessException when instantiating MultipartRequestHandler \"" + multipartClass + "\", " + "defaulting to global multipart class, exception: " + iae.getMessage());
            }
            if(multipartHandler != null)
                return multipartHandler;
        }
        ModuleConfig moduleConfig = (ModuleConfig)request.getAttribute("org.apache.struts.action.MODULE");
        multipartClass = moduleConfig.getControllerConfig().getMultipartClass();
        if(multipartClass != null)
        {
            try
            {
                multipartHandler = (MultipartRequestHandler)applicationInstance(multipartClass);
            }
            catch(ClassNotFoundException cnfe)
            {
                throw new ServletException("Cannot find multipart class \"" + multipartClass + "\"" + ", exception: " + cnfe.getMessage());
            }
            catch(InstantiationException ie)
            {
                throw new ServletException("InstantiaionException when instantiating multipart class \"" + multipartClass + "\", exception: " + ie.getMessage());
            }
            catch(IllegalAccessException iae)
            {
                throw new ServletException("IllegalAccessException when instantiating multipart class \"" + multipartClass + "\", exception: " + iae.getMessage());
            }
            if(multipartHandler != null)
                return multipartHandler;
        }
        return multipartHandler;
    }

    private static Map getAllParametersForMultipartRequest(HttpServletRequest request, MultipartRequestHandler multipartHandler)
    {
        Map parameters = new HashMap();
        Hashtable elements = multipartHandler.getAllElements();
        String key;
        for(Enumeration enum = elements.keys(); enum.hasMoreElements(); parameters.put(key, elements.get(key)))
            key = (String)enum.nextElement();

        if(request instanceof MultipartRequestWrapper)
        {
            request = ((MultipartRequestWrapper)request).getRequest();
            String key;
            for(Enumeration enum = request.getParameterNames(); enum.hasMoreElements(); parameters.put(key, request.getParameterValues(key)))
                key = (String)enum.nextElement();

        }
        else
        {
            log.debug("Gathering multipart parameters for unwrapped request");
        }
        return parameters;
    }

    public static boolean present(PageContext pageContext, String bundle, String locale, String key)
        throws JspException
    {
        MessageResources resources = retrieveMessageResources(pageContext, bundle, true);
        Locale userLocale = retrieveUserLocale(pageContext, locale);
        return resources.isPresent(userLocale, key);
    }

    public static String printableURL(URL url)
    {
        if(url.getHost() != null)
            return url.toString();
        String file = url.getFile();
        String ref = url.getRef();
        if(ref == null)
        {
            return file;
        }
        else
        {
            StringBuffer sb = new StringBuffer(file);
            sb.append('#');
            sb.append(ref);
            return sb.toString();
        }
    }

    public static String actionURL(HttpServletRequest request, ActionConfig action, String pattern)
    {
        StringBuffer sb = new StringBuffer();
        if(pattern.endsWith("/*"))
        {
            sb.append(pattern.substring(0, pattern.length() - 2));
            sb.append(action.getPath());
        }
        else
        if(pattern.startsWith("*."))
        {
            ModuleConfig appConfig = (ModuleConfig)request.getAttribute("org.apache.struts.action.MODULE");
            sb.append(appConfig.getPrefix());
            sb.append(action.getPath());
            sb.append(pattern.substring(1));
        }
        else
        {
            throw new IllegalArgumentException(pattern);
        }
        return sb.toString();
    }

    public static String forwardURL(HttpServletRequest request, ForwardConfig forward)
    {
        String path = forward.getPath();
        StringBuffer sb = new StringBuffer();
        if(forward.getContextRelative())
        {
            if(!path.startsWith("/"))
                sb.append("/");
            sb.append(path);
            return sb.toString();
        }
        ModuleConfig moduleConfig = (ModuleConfig)request.getAttribute("org.apache.struts.action.MODULE");
        String forwardPattern = moduleConfig.getControllerConfig().getForwardPattern();
        if(forwardPattern == null)
        {
            sb.append(moduleConfig.getPrefix());
            if(!path.startsWith("/"))
                sb.append("/");
            sb.append(path);
        }
        else
        {
            boolean dollar = false;
            for(int i = 0; i < forwardPattern.length(); i++)
            {
                char ch = forwardPattern.charAt(i);
                if(dollar)
                {
                    switch(ch)
                    {
                    default:
                        break;

                    case 77: // 'M'
                        sb.append(moduleConfig.getPrefix());
                        break;

                    case 80: // 'P'
                        if(!path.startsWith("/"))
                            sb.append("/");
                        sb.append(path);
                        break;

                    case 36: // '$'
                        sb.append('$');
                        break;

                    }
                    dollar = false;
                }
                else
                if(ch == '$')
                    dollar = true;
                else
                    sb.append(ch);
            }

        }
        return sb.toString();
    }

    public static String pageURL(HttpServletRequest request, String page)
    {
        StringBuffer sb = new StringBuffer();
        ModuleConfig moduleConfig = (ModuleConfig)request.getAttribute("org.apache.struts.action.MODULE");
        String pagePattern = moduleConfig.getControllerConfig().getPagePattern();
        if(pagePattern == null)
        {
            sb.append(moduleConfig.getPrefix());
            sb.append(page);
        }
        else
        {
            boolean dollar = false;
            for(int i = 0; i < pagePattern.length(); i++)
            {
                char ch = pagePattern.charAt(i);
                if(dollar)
                {
                    switch(ch)
                    {
                    case 77: // 'M'
                        sb.append(moduleConfig.getPrefix());
                        break;

                    case 80: // 'P'
                        sb.append(page);
                        break;

                    case 36: // '$'
                        sb.append('$');
                        break;

                    }
                    dollar = false;
                }
                else
                if(ch == '$')
                    dollar = true;
                else
                    sb.append(ch);
            }

        }
        return sb.toString();
    }

    public static URL requestURL(HttpServletRequest request)
        throws MalformedURLException
    {
        StringBuffer url = new StringBuffer();
        String scheme = request.getScheme();
        int port = request.getServerPort();
        if(port < 0)
            port = 80;
        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        if(scheme.equals("http") && port != 80 || scheme.equals("https") && port != 443)
        {
            url.append(':');
            url.append(port);
        }
        url.append(request.getRequestURI());
        return new URL(url.toString());
    }

    public static URL serverURL(HttpServletRequest request)
        throws MalformedURLException
    {
        StringBuffer url = new StringBuffer();
        String scheme = request.getScheme();
        int port = request.getServerPort();
        if(port < 0)
            port = 80;
        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        if(scheme.equals("http") && port != 80 || scheme.equals("https") && port != 443)
        {
            url.append(':');
            url.append(port);
        }
        return new URL(url.toString());
    }

    public static void saveException(PageContext pageContext, Throwable exception)
    {
        pageContext.setAttribute("org.apache.struts.action.EXCEPTION", exception, 2);
    }

    public static void selectApplication(String prefix, HttpServletRequest request, ServletContext context)
    {
        selectModule(prefix, request, context);
    }

    public static void selectModule(String prefix, HttpServletRequest request, ServletContext context)
    {
        ModuleConfig config = (ModuleConfig)context.getAttribute("org.apache.struts.action.MODULE" + prefix);
        if(config != null)
            request.setAttribute("org.apache.struts.action.MODULE", config);
        else
            request.removeAttribute("org.apache.struts.action.MODULE");
        MessageResources resources = (MessageResources)context.getAttribute("org.apache.struts.action.MESSAGE" + prefix);
        if(resources != null)
            request.setAttribute("org.apache.struts.action.MESSAGE", resources);
        else
            request.removeAttribute("org.apache.struts.action.MESSAGE");
    }

    public static void selectApplication(HttpServletRequest request, ServletContext context)
    {
        selectModule(request, context);
    }

    public static void selectModule(HttpServletRequest request, ServletContext context)
    {
        String prefix = getModuleName(request, context);
        selectModule(prefix, request, context);
    }

    public static String getModuleName(HttpServletRequest request, ServletContext context)
    {
        String matchPath = (String)request.getAttribute("javax.servlet.include.servlet_path");
        if(matchPath == null)
            matchPath = request.getServletPath();
        return getModuleName(matchPath, context);
    }

    public static String getModuleName(String matchPath, ServletContext context)
    {
        if(log.isDebugEnabled())
            log.debug("Get module name for path " + matchPath);
        String prefix = "";
        String prefixes[] = getModulePrefixes(context);
        for(int lastSlash = 0; prefix.equals("") && (lastSlash = matchPath.lastIndexOf("/")) > 0;)
        {
            matchPath = matchPath.substring(0, lastSlash);
            for(int i = 0; i < prefixes.length; i++)
            {
                if(!matchPath.equals(prefixes[i]))
                    continue;
                prefix = prefixes[i];
                break;
            }

        }

        if(log.isDebugEnabled())
            log.debug("Module name found: " + (prefix.equals("") ? "default" : prefix));
        return prefix;
    }

    public static ModuleConfig getRequestModuleConfig(HttpServletRequest request)
    {
        return (ModuleConfig)request.getAttribute("org.apache.struts.action.MODULE");
    }

    public static ModuleConfig getModuleConfig(HttpServletRequest request, ServletContext context)
    {
        ModuleConfig moduleConfig = (ModuleConfig)request.getAttribute("org.apache.struts.action.MODULE");
        if(moduleConfig == null)
            moduleConfig = (ModuleConfig)context.getAttribute("org.apache.struts.action.MODULE");
        return moduleConfig;
    }

    public static ModuleConfig getModuleConfig(PageContext pageContext)
    {
        return getModuleConfig((HttpServletRequest)pageContext.getRequest(), pageContext.getServletContext());
    }

    public static String[] getApplicationPrefixes(ServletContext context)
    {
        return getModulePrefixes(context);
    }

    public static synchronized String[] getModulePrefixes(ServletContext context)
    {
        String prefixes[] = (String[])context.getAttribute("org.apache.struts.util.PREFIXES");
        if(prefixes != null)
            return prefixes;
        ArrayList list = new ArrayList();
        for(Enumeration names = context.getAttributeNames(); names.hasMoreElements();)
        {
            String name = (String)names.nextElement();
            if(name.startsWith("org.apache.struts.action.MODULE"))
            {
                String prefix = name.substring("org.apache.struts.action.MODULE".length());
                if(prefix.length() > 0)
                    list.add(prefix);
            }
        }

        prefixes = (String[])list.toArray(new String[list.size()]);
        context.setAttribute("org.apache.struts.util.PREFIXES", prefixes);
        return prefixes;
    }

    public static ActionMessages getActionMessages(PageContext pageContext, String paramName)
        throws JspException
    {
        ActionMessages am = new ActionMessages();
        Object value = pageContext.findAttribute(paramName);
        try
        {
            if(value != null)
                if(value instanceof String)
                    am.add("org.apache.struts.action.GLOBAL_MESSAGE", new ActionMessage((String)value));
                else
                if(value instanceof String[])
                {
                    String keys[] = (String[])value;
                    for(int i = 0; i < keys.length; i++)
                        am.add("org.apache.struts.action.GLOBAL_MESSAGE", new ActionMessage(keys[i]));

                }
                else
                if(value instanceof ErrorMessages)
                {
                    String keys[] = ((ErrorMessages)value).getErrors();
                    if(keys == null)
                        keys = new String[0];
                    for(int i = 0; i < keys.length; i++)
                        am.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError(keys[i]));

                }
                else
                if(value instanceof ActionMessages)
                    am = (ActionMessages)value;
                else
                    throw new JspException(messages.getMessage("actionMessages.errors", value.getClass().getName()));
        }
        catch(JspException e)
        {
            throw e;
        }
        catch(Exception e) { }
        return am;
    }

    public static ActionErrors getActionErrors(PageContext pageContext, String paramName)
        throws JspException
    {
        ActionErrors errors = new ActionErrors();
        Object value = pageContext.findAttribute(paramName);
        try
        {
            if(value != null)
                if(value instanceof String)
                    errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError((String)value));
                else
                if(value instanceof String[])
                {
                    String keys[] = (String[])value;
                    for(int i = 0; i < keys.length; i++)
                        errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError(keys[i]));

                }
                else
                if(value instanceof ErrorMessages)
                {
                    String keys[] = ((ErrorMessages)value).getErrors();
                    if(keys == null)
                        keys = new String[0];
                    for(int i = 0; i < keys.length; i++)
                        errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError(keys[i]));

                }
                else
                if(value instanceof ActionErrors)
                    errors = (ActionErrors)value;
                else
                    throw new JspException(messages.getMessage("actionErrors.errors", value.getClass().getName()));
        }
        catch(JspException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            log.debug(e, e);
        }
        return errors;
    }

    public static String encodeURL(String url)
    {
        if(encode != null)
            return (String)encode.invoke(null, new Object[] {
                url, "UTF-8"
            });
        break MISSING_BLOCK_LABEL_66;
        IllegalAccessException e;
        e;
        log.debug("Could not find Java 1.4 encode method.  Using deprecated version.", e);
        break MISSING_BLOCK_LABEL_66;
        InvocationTargetException e;
        e;
        log.debug("Could not find Java 1.4 encode method. Using deprecated version.", e);
        return URLEncoder.encode(url);
    }

    public static boolean isXhtml(PageContext pageContext)
    {
        String xhtml = (String)pageContext.getAttribute("org.apache.struts.globals.XHTML", 1);
        return "true".equalsIgnoreCase(xhtml);
    }

    static Class class$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    static 
    {
        log = LogFactory.getLog(class$org$apache$struts$util$RequestUtils != null ? class$org$apache$struts$util$RequestUtils : (class$org$apache$struts$util$RequestUtils = class$("org.apache.struts.util.RequestUtils")));
        scopes = new HashMap();
        try
        {
            Class args[] = {
                class$java$lang$String != null ? class$java$lang$String : (class$java$lang$String = class$("java.lang.String")), class$java$lang$String != null ? class$java$lang$String : (class$java$lang$String = class$("java.lang.String"))
            };
            encode = (class$java$net$URLEncoder != null ? class$java$net$URLEncoder : (class$java$net$URLEncoder = class$("java.net.URLEncoder"))).getMethod("encode", args);
        }
        catch(NoSuchMethodException e)
        {
            log.debug("Could not find Java 1.4 encode method.  Using deprecated version.", e);
        }
        scopes.put("page", new Integer(1));
        scopes.put("request", new Integer(2));
        scopes.put("session", new Integer(3));
        scopes.put("application", new Integer(4));
    }
}
