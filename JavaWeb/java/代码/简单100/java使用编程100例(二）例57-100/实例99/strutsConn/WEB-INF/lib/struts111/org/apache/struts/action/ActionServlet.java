///////////////////////////////////////////////////////////
// DeJaved by mDeJava v1.0. Copyright 1999 MoleSoftware. //
//       To download last version of this software:      //
//            http://molesoftware.hypermatr.net          //
//               e-mail:molesoftware@mail.ru             //
///////////////////////////////////////////////////////////

package org.apache.struts.action;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
import org.apache.commons.beanutils.*;
import org.apache.commons.beanutils.converters.*;
import org.apache.commons.collections.FastHashMap;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.config.*;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.apache.struts.util.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

// Referenced classes of package org.apache.struts.action:
//            ActionFormBeans, ActionForwards, ActionMappings, RequestProcessor, 
//            PlugIn, ActionFormBean, ActionForward, ActionMapping, 
//            DynaActionFormClass

public class ActionServlet extends HttpServlet
{

    protected String config = null;
    protected Digester configDigester = null;
    protected boolean convertNull = false;
    protected FastHashMap dataSources = null;
    protected int debug = 0;
    protected MessageResources internal = null;
    protected String internalName = null;
    protected static Log log = null;
    protected RequestProcessor processor = null;
    protected String registrations[] = {
        "-//Apache Software Foundation//DTD Struts Configuration 1.0//EN", "/org/apache/struts/resources/struts-config_1_0.dtd", "-//Apache Software Foundation//DTD Struts Configuration 1.1//EN", "/org/apache/struts/resources/struts-config_1_1.dtd", "-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN", "/org/apache/struts/resources/web-app_2_2.dtd", "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN", "/org/apache/struts/resources/web-app_2_3.dtd"
    };
    protected String servletMapping = null;
    protected String servletName = null;
    static Class class$org$apache$struts$action$ActionServlet = null; /* synthetic field */
    static Class class$java$math$BigDecimal = null; /* synthetic field */
    static Class class$java$math$BigInteger = null; /* synthetic field */
    static Class class$java$lang$Boolean = null; /* synthetic field */
    static Class class$java$lang$Byte = null; /* synthetic field */
    static Class class$java$lang$Character = null; /* synthetic field */
    static Class class$java$lang$Double = null; /* synthetic field */
    static Class class$java$lang$Float = null; /* synthetic field */
    static Class class$java$lang$Integer = null; /* synthetic field */
    static Class class$java$lang$Long = null; /* synthetic field */
    static Class class$java$lang$Short = null; /* synthetic field */

    public ActionServlet()
    {
        config = "/WEB-INF/struts-config.xml";
        configDigester = null;
        convertNull = false;
        dataSources = new FastHashMap();
        debug = 0;
        internal = null;
        internalName = "org.apache.struts.action.ActionResources";
        processor = null;
        servletMapping = null;
        servletName = null;
    }

    public void destroy()
    {
        if(log.isDebugEnabled())
            log.debug(internal.getMessage("finalizing"));
        destroyModules();
        destroyDataSources();
        destroyInternal();
        getServletContext().removeAttribute("org.apache.struts.action.ACTION_SERVLET");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if(classLoader == null)
            classLoader = (class$org$apache$struts$action$ActionServlet != null ? class$org$apache$struts$action$ActionServlet : (class$org$apache$struts$action$ActionServlet = class$("org.apache.struts.action.ActionServlet"))).getClassLoader();
        try
        {
            LogFactory.release(classLoader);
        }
        catch(Throwable t) { }
    }

    public void init()
        throws ServletException
    {
        initInternal();
        initOther();
        initServlet();
        getServletContext().setAttribute("org.apache.struts.action.ACTION_SERVLET", this);
        ModuleConfig moduleConfig = initModuleConfig("", config);
        initModuleMessageResources(moduleConfig);
        initModuleDataSources(moduleConfig);
        initModulePlugIns(moduleConfig);
        moduleConfig.freeze();
        for(Enumeration names = getServletConfig().getInitParameterNames(); names.hasMoreElements();)
        {
            String name = (String)names.nextElement();
            if(name.startsWith("config/"))
            {
                String prefix = name.substring(6);
                moduleConfig = initModuleConfig(prefix, getServletConfig().getInitParameter(name));
                initModuleMessageResources(moduleConfig);
                initModuleDataSources(moduleConfig);
                initModulePlugIns(moduleConfig);
                moduleConfig.freeze();
            }
        }

        destroyConfigDigester();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        process(request, response);
    }

    public void addServletMapping(String servletName, String urlPattern)
    {
        if(log.isDebugEnabled())
            log.debug("Process servletName=" + servletName + ", urlPattern=" + urlPattern);
        if(servletName == null)
            return;
        if(servletName.equals(this.servletName))
            servletMapping = urlPattern;
    }

    public DataSource findDataSource(String key)
    {
        if(key == null)
            return (DataSource)dataSources.get("org.apache.struts.action.DATA_SOURCE");
        else
            return (DataSource)dataSources.get(key);
    }

    public ActionFormBean findFormBean(String name)
    {
        ActionFormBeans afb = (ActionFormBeans)getServletContext().getAttribute("org.apache.struts.action.FORM_BEANS");
        if(afb == null)
            return null;
        else
            return afb.findFormBean(name);
    }

    public ActionForward findForward(String name)
    {
        ActionForwards af = (ActionForwards)getServletContext().getAttribute("org.apache.struts.action.FORWARDS");
        if(af == null)
            return null;
        else
            return af.findForward(name);
    }

    public ActionMapping findMapping(String path)
    {
        ActionMappings am = (ActionMappings)getServletContext().getAttribute("org.apache.struts.action.MAPPINGS");
        if(am == null)
            return null;
        else
            return am.findMapping(path);
    }

    public int getDebug()
    {
        return debug;
    }

    public MessageResources getInternal()
    {
        return internal;
    }

    public MessageResources getResources()
    {
        return (MessageResources)getServletContext().getAttribute("org.apache.struts.action.MESSAGE");
    }

    public void log(String message, int level)
    {
        if(debug >= level)
            log(message);
    }

    protected void destroyApplications()
    {
        destroyModules();
    }

    protected void destroyModules()
    {
        ArrayList values = new ArrayList();
        for(Enumeration names = getServletContext().getAttributeNames(); names.hasMoreElements(); values.add(names.nextElement()));
        for(Iterator keys = values.iterator(); keys.hasNext();)
        {
            String name = (String)keys.next();
            Object value = getServletContext().getAttribute(name);
            if(value instanceof ModuleConfig)
            {
                ModuleConfig config = (ModuleConfig)value;
                try
                {
                    getRequestProcessor(config).destroy();
                }
                catch(ServletException e)
                {
                    log.error(e);
                }
                getServletContext().removeAttribute(name);
                PlugIn plugIns[] = (PlugIn[])getServletContext().getAttribute("org.apache.struts.action.PLUG_INS" + config.getPrefix());
                if(plugIns != null)
                {
                    for(int i = 0; i < plugIns.length; i++)
                    {
                        int j = plugIns.length - (i + 1);
                        plugIns[j].destroy();
                    }

                    getServletContext().removeAttribute("org.apache.struts.action.PLUG_INS" + config.getPrefix());
                }
            }
        }

    }

    protected void destroyConfigDigester()
    {
        configDigester = null;
    }

    protected void destroyDataSources()
    {
        FastHashMap fasthashmap = dataSources;
        JVM INSTR monitorenter ;
        for(Iterator keys = dataSources.keySet().iterator(); keys.hasNext();)
        {
            String key = (String)keys.next();
            getServletContext().removeAttribute(key);
            DataSource dataSource = findDataSource(key);
            if(dataSource instanceof GenericDataSource)
            {
                if(log.isDebugEnabled())
                    log.debug(internal.getMessage("dataSource.destroy", key));
                try
                {
                    ((GenericDataSource)dataSource).close();
                }
                catch(SQLException e)
                {
                    log.error(internal.getMessage("destroyDataSource", key), e);
                }
            }
        }

        dataSources.clear();
        fasthashmap;
        JVM INSTR monitorexit ;
        break MISSING_BLOCK_LABEL_151;
        Exception exception;
        exception;
        fasthashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void destroyInternal()
    {
        internal = null;
    }

    protected ApplicationConfig getApplicationConfig(HttpServletRequest request)
    {
        return new ApplicationConfig((ModuleConfigImpl)getModuleConfig(request));
    }

    protected ModuleConfig getModuleConfig(HttpServletRequest request)
    {
        ModuleConfig config = (ModuleConfig)request.getAttribute("org.apache.struts.action.MODULE");
        if(config == null)
            config = (ModuleConfig)getServletContext().getAttribute("org.apache.struts.action.MODULE");
        return config;
    }

    protected synchronized RequestProcessor getRequestProcessor(ModuleConfig config)
        throws ServletException
    {
        String key = "org.apache.struts.action.REQUEST_PROCESSOR" + config.getPrefix();
        RequestProcessor processor = (RequestProcessor)getServletContext().getAttribute(key);
        if(processor == null)
        {
            try
            {
                processor = (RequestProcessor)RequestUtils.applicationInstance(config.getControllerConfig().getProcessorClass());
            }
            catch(Exception e)
            {
                throw new UnavailableException("Cannot initialize RequestProcessor of class " + config.getControllerConfig().getProcessorClass() + ": " + e);
            }
            processor.init(this, config);
            getServletContext().setAttribute(key, processor);
        }
        return processor;
    }

    protected ApplicationConfig initApplicationConfig(String prefix, String path)
        throws ServletException
    {
        return new ApplicationConfig((ModuleConfigImpl)initModuleConfig(prefix, path));
    }

    protected ModuleConfig initModuleConfig(String prefix, String paths)
        throws ServletException
    {
        if(log.isDebugEnabled())
            log.debug("Initializing module path '" + prefix + "' configuration from '" + paths + "'");
        ModuleConfigFactory factoryObject = ModuleConfigFactory.createFactory();
        ModuleConfig config = factoryObject.createModuleConfig(prefix);
        String mapping = getServletConfig().getInitParameter("mapping");
        if(mapping != null)
            config.setActionMappingClass(mapping);
        Digester digester = initConfigDigester();
        String path;
        for(; paths.length() > 0; parseModuleConfigFile(prefix, paths, config, digester, path))
        {
            digester.push(config);
            path = null;
            int comma = paths.indexOf(44);
            if(comma >= 0)
            {
                path = paths.substring(0, comma).trim();
                paths = paths.substring(comma + 1);
            }
            else
            {
                path = paths.trim();
                paths = "";
            }
            if(path.length() < 1)
                break;
        }

        FormBeanConfig fbs[] = config.findFormBeanConfigs();
        for(int i = 0; i < fbs.length; i++)
            if(fbs[i].getDynamic())
                DynaActionFormClass.createDynaActionFormClass(fbs[i]);

        if(prefix.length() < 1)
        {
            defaultControllerConfig(config);
            defaultMessageResourcesConfig(config);
            defaultFormBeansConfig(config);
            defaultForwardsConfig(config);
            defaultMappingsConfig(config);
        }
        return config;
    }

    private void parseModuleConfigFile(String prefix, String paths, ModuleConfig config, Digester digester, String path)
        throws UnavailableException
    {
        InputStream input = null;
        URL url = getServletContext().getResource(path);
        InputSource is = new InputSource(url.toExternalForm());
        input = getServletContext().getResourceAsStream(path);
        is.setByteStream(input);
        digester.parse(is);
        getServletContext().setAttribute("org.apache.struts.action.MODULE" + prefix, config);
        MalformedURLException e;
        if(input != null)
            try
            {
                input.close();
            }
            // Misplaced declaration of exception variable
            catch(MalformedURLException e)
            {
                throw new UnavailableException(e.getMessage());
            }
        break MISSING_BLOCK_LABEL_271;
        e;
        handleConfigException(paths, e);
        IOException e;
        if(input != null)
            try
            {
                input.close();
            }
            // Misplaced declaration of exception variable
            catch(IOException e)
            {
                throw new UnavailableException(e.getMessage());
            }
        break MISSING_BLOCK_LABEL_271;
        e;
        handleConfigException(paths, e);
        SAXException e;
        if(input != null)
            try
            {
                input.close();
            }
            // Misplaced declaration of exception variable
            catch(SAXException e)
            {
                throw new UnavailableException(e.getMessage());
            }
        break MISSING_BLOCK_LABEL_271;
        e;
        handleConfigException(paths, e);
        if(input != null)
            try
            {
                input.close();
            }
            catch(IOException e)
            {
                throw new UnavailableException(e.getMessage());
            }
        break MISSING_BLOCK_LABEL_271;
        Exception exception;
        exception;
        if(input != null)
            try
            {
                input.close();
            }
            catch(IOException e)
            {
                throw new UnavailableException(e.getMessage());
            }
        throw exception;
    }

    private void handleConfigException(String paths, Exception e)
        throws UnavailableException
    {
        log.error(internal.getMessage("configParse", paths), e);
        throw new UnavailableException(internal.getMessage("configParse", paths));
    }

    protected void initApplicationDataSources(ModuleConfig config)
        throws ServletException
    {
        initModuleDataSources(config);
    }

    protected void initModuleDataSources(ModuleConfig config)
        throws ServletException
    {
        if(log.isDebugEnabled())
            log.debug("Initializing module path '" + config.getPrefix() + "' data sources");
        ServletContextWriter scw = new ServletContextWriter(getServletContext());
        DataSourceConfig dscs[] = config.findDataSourceConfigs();
        if(dscs == null)
            dscs = new DataSourceConfig[0];
        dataSources.setFast(false);
        for(int i = 0; i < dscs.length; i++)
        {
            if(log.isDebugEnabled())
                log.debug("Initializing module path '" + config.getPrefix() + "' data source '" + dscs[i].getKey() + "'");
            DataSource ds = null;
            try
            {
                ds = (DataSource)RequestUtils.applicationInstance(dscs[i].getType());
                BeanUtils.populate(ds, dscs[i].getProperties());
                if(ds instanceof GenericDataSource)
                    ((GenericDataSource)ds).open();
                ds.setLogWriter(scw);
            }
            catch(Exception e)
            {
                log.error(internal.getMessage("dataSource.init", dscs[i].getKey()), e);
                throw new UnavailableException(internal.getMessage("dataSource.init", dscs[i].getKey()));
            }
            getServletContext().setAttribute(dscs[i].getKey() + config.getPrefix(), ds);
            dataSources.put(dscs[i].getKey(), ds);
        }

        dataSources.setFast(true);
        if("".equals(config.getPrefix()))
            initDataSources();
    }

    protected void initApplicationPlugIns(ModuleConfig config)
        throws ServletException
    {
        initModulePlugIns(config);
    }

    protected void initModulePlugIns(ModuleConfig config)
        throws ServletException
    {
        if(log.isDebugEnabled())
            log.debug("Initializing module path '" + config.getPrefix() + "' plug ins");
        PlugInConfig plugInConfigs[] = config.findPlugInConfigs();
        PlugIn plugIns[] = new PlugIn[plugInConfigs.length];
        getServletContext().setAttribute("org.apache.struts.action.PLUG_INS" + config.getPrefix(), plugIns);
        for(int i = 0; i < plugIns.length; i++)
            try
            {
                plugIns[i] = (PlugIn)RequestUtils.applicationInstance(plugInConfigs[i].getClassName());
                BeanUtils.populate(plugIns[i], plugInConfigs[i].getProperties());
                try
                {
                    PropertyUtils.setProperty(plugIns[i], "currentPlugInConfigObject", plugInConfigs[i]);
                }
                catch(Exception e) { }
                plugIns[i].init(this, config);
            }
            catch(ServletException e)
            {
                throw e;
            }
            catch(Exception e)
            {
                String errMsg = internal.getMessage("plugIn.init", plugInConfigs[i].getClassName());
                log(errMsg, e);
                throw new UnavailableException(errMsg);
            }

    }

    protected void initApplicationMessageResources(ModuleConfig config)
        throws ServletException
    {
        initModuleMessageResources(config);
    }

    protected void initModuleMessageResources(ModuleConfig config)
        throws ServletException
    {
        MessageResourcesConfig mrcs[] = config.findMessageResourcesConfigs();
        for(int i = 0; i < mrcs.length; i++)
            if(mrcs[i].getFactory() != null && mrcs[i].getParameter() != null)
            {
                if(log.isDebugEnabled())
                    log.debug("Initializing module path '" + config.getPrefix() + "' message resources from '" + mrcs[i].getParameter() + "'");
                String factory = mrcs[i].getFactory();
                MessageResourcesFactory.setFactoryClass(factory);
                MessageResourcesFactory factoryObject = MessageResourcesFactory.createFactory();
                MessageResources resources = factoryObject.createResources(mrcs[i].getParameter());
                resources.setReturnNull(mrcs[i].getNull());
                getServletContext().setAttribute(mrcs[i].getKey() + config.getPrefix(), resources);
            }

    }

    protected Digester initConfigDigester()
        throws ServletException
    {
        if(configDigester != null)
            return configDigester;
        boolean validating = true;
        String value = getServletConfig().getInitParameter("validating");
        if("false".equalsIgnoreCase(value) || "no".equalsIgnoreCase(value) || "n".equalsIgnoreCase(value) || "0".equalsIgnoreCase(value))
            validating = false;
        configDigester = new Digester();
        configDigester.setNamespaceAware(true);
        configDigester.setValidating(validating);
        configDigester.setUseContextClassLoader(true);
        configDigester.addRuleSet(new ConfigRuleSet());
        for(int i = 0; i < registrations.length; i += 2)
        {
            URL url = getClass().getResource(registrations[i + 1]);
            if(url != null)
                configDigester.register(registrations[i], url.toString());
        }

        String rulesets = getServletConfig().getInitParameter("rulesets");
        if(rulesets == null)
            rulesets = "";
        rulesets = rulesets.trim();
        String ruleset = null;
        while(rulesets.length() > 0) 
        {
            int comma = rulesets.indexOf(",");
            if(comma < 0)
            {
                ruleset = rulesets.trim();
                rulesets = "";
            }
            else
            {
                ruleset = rulesets.substring(0, comma).trim();
                rulesets = rulesets.substring(comma + 1).trim();
            }
            if(log.isDebugEnabled())
                log.debug("Configuring custom Digester Ruleset of type " + ruleset);
            try
            {
                RuleSet instance = (RuleSet)RequestUtils.applicationInstance(ruleset);
                configDigester.addRuleSet(instance);
            }
            catch(Exception e)
            {
                log.error("Exception configuring custom Digester RuleSet", e);
                throw new ServletException(e);
            }
        }

        return configDigester;
    }

    protected void initDataSources()
        throws ServletException
    {
    }

    protected void initInternal()
        throws ServletException
    {
        try
        {
            internal = MessageResources.getMessageResources(internalName);
        }
        catch(MissingResourceException e)
        {
            log.error("Cannot load internal resources from '" + internalName + "'", e);
            throw new UnavailableException("Cannot load internal resources from '" + internalName + "'");
        }
    }

    protected void initOther()
        throws ServletException
    {
        String value = null;
        value = getServletConfig().getInitParameter("config");
        if(value != null)
            config = value;
        value = getServletConfig().getInitParameter("debug");
        if(value != null)
            try
            {
                debug = Integer.parseInt(value);
            }
            catch(NumberFormatException e)
            {
                debug = 0;
            }
        value = getServletConfig().getInitParameter("convertNull");
        if("true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) || "on".equalsIgnoreCase(value) || "y".equalsIgnoreCase(value) || "1".equalsIgnoreCase(value))
            convertNull = true;
        if(convertNull)
        {
            ConvertUtils.deregister();
            ConvertUtils.register(new BigDecimalConverter(null), class$java$math$BigDecimal != null ? class$java$math$BigDecimal : (class$java$math$BigDecimal = class$("java.math.BigDecimal")));
            ConvertUtils.register(new BigIntegerConverter(null), class$java$math$BigInteger != null ? class$java$math$BigInteger : (class$java$math$BigInteger = class$("java.math.BigInteger")));
            ConvertUtils.register(new BooleanConverter(null), class$java$lang$Boolean != null ? class$java$lang$Boolean : (class$java$lang$Boolean = class$("java.lang.Boolean")));
            ConvertUtils.register(new ByteConverter(null), class$java$lang$Byte != null ? class$java$lang$Byte : (class$java$lang$Byte = class$("java.lang.Byte")));
            ConvertUtils.register(new CharacterConverter(null), class$java$lang$Character != null ? class$java$lang$Character : (class$java$lang$Character = class$("java.lang.Character")));
            ConvertUtils.register(new DoubleConverter(null), class$java$lang$Double != null ? class$java$lang$Double : (class$java$lang$Double = class$("java.lang.Double")));
            ConvertUtils.register(new FloatConverter(null), class$java$lang$Float != null ? class$java$lang$Float : (class$java$lang$Float = class$("java.lang.Float")));
            ConvertUtils.register(new IntegerConverter(null), class$java$lang$Integer != null ? class$java$lang$Integer : (class$java$lang$Integer = class$("java.lang.Integer")));
            ConvertUtils.register(new LongConverter(null), class$java$lang$Long != null ? class$java$lang$Long : (class$java$lang$Long = class$("java.lang.Long")));
            ConvertUtils.register(new ShortConverter(null), class$java$lang$Short != null ? class$java$lang$Short : (class$java$lang$Short = class$("java.lang.Short")));
        }
    }

    protected void initServlet()
        throws ServletException
    {
        InputStream input;
        Exception exception;
        servletName = getServletConfig().getServletName();
        Digester digester = new Digester();
        digester.push(this);
        digester.setNamespaceAware(true);
        digester.setValidating(false);
        for(int i = 0; i < registrations.length; i += 2)
        {
            URL url = getClass().getResource(registrations[i + 1]);
            if(url != null)
                digester.register(registrations[i], url.toString());
        }

        digester.addCallMethod("web-app/servlet-mapping", "addServletMapping", 2);
        digester.addCallParam("web-app/servlet-mapping/servlet-name", 0);
        digester.addCallParam("web-app/servlet-mapping/url-pattern", 1);
        if(log.isDebugEnabled())
            log.debug("Scanning web.xml for controller servlet mapping");
        input = getServletContext().getResourceAsStream("/WEB-INF/web.xml");
        try
        {
            digester.parse(input);
        }
        catch(IOException e)
        {
            log.error(internal.getMessage("configWebXml"), e);
            throw new ServletException(e);
        }
        catch(SAXException e)
        {
            log.error(internal.getMessage("configWebXml"), e);
            throw new ServletException(e);
        }
        finally
        {
            if(input == null) goto _L0; else goto _L0
        }
        if(input != null)
            try
            {
                input.close();
            }
            catch(IOException e)
            {
                log.error(internal.getMessage("configWebXml"), e);
                throw new ServletException(e);
            }
        break MISSING_BLOCK_LABEL_313;
        try
        {
            input.close();
        }
        catch(IOException e)
        {
            log.error(internal.getMessage("configWebXml"), e);
            throw new ServletException(e);
        }
        throw exception;
        if(log.isDebugEnabled())
            log.debug("Mapping for servlet '" + servletName + "' = '" + servletMapping + "'");
        if(servletMapping != null)
            getServletContext().setAttribute("org.apache.struts.action.SERVLET_MAPPING", servletMapping);
        return;
    }

    protected void process(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        RequestUtils.selectModule(request, getServletContext());
        getRequestProcessor(getModuleConfig(request)).process(request, response);
    }

    private void defaultControllerConfig(ModuleConfig config)
    {
        String value = null;
        ControllerConfig cc = config.getControllerConfig();
        value = getServletConfig().getInitParameter("bufferSize");
        if(value != null)
            cc.setBufferSize(Integer.parseInt(value));
        value = getServletConfig().getInitParameter("content");
        if(value != null)
            cc.setContentType(value);
        value = getServletConfig().getInitParameter("locale");
        if(value != null)
            if("true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value))
                cc.setLocale(true);
            else
                cc.setLocale(false);
        value = getServletConfig().getInitParameter("maxFileSize");
        if(value != null)
            cc.setMaxFileSize(value);
        value = getServletConfig().getInitParameter("nocache");
        if(value != null)
            if("true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value))
                cc.setNocache(true);
            else
                cc.setNocache(false);
        value = getServletConfig().getInitParameter("multipartClass");
        if(value != null)
            cc.setMultipartClass(value);
        value = getServletConfig().getInitParameter("tempDir");
        if(value != null)
            cc.setTempDir(value);
    }

    private void defaultFormBeansConfig(ModuleConfig config)
    {
        FormBeanConfig fbcs[] = config.findFormBeanConfigs();
        ActionFormBeans afb = new ActionFormBeans();
        afb.setFast(false);
        for(int i = 0; i < fbcs.length; i++)
            afb.addFormBean((ActionFormBean)fbcs[i]);

        afb.setFast(true);
        getServletContext().setAttribute("org.apache.struts.action.FORM_BEANS", afb);
    }

    private void defaultForwardsConfig(ModuleConfig config)
    {
        org.apache.struts.config.ForwardConfig fcs[] = config.findForwardConfigs();
        ActionForwards af = new ActionForwards();
        af.setFast(false);
        for(int i = 0; i < fcs.length; i++)
            af.addForward((ActionForward)fcs[i]);

        af.setFast(true);
        getServletContext().setAttribute("org.apache.struts.action.FORWARDS", af);
    }

    private void defaultMappingsConfig(ModuleConfig config)
    {
        org.apache.struts.config.ActionConfig acs[] = config.findActionConfigs();
        ActionMappings am = new ActionMappings();
        am.setServlet(this);
        am.setFast(false);
        for(int i = 0; i < acs.length; i++)
            am.addMapping((ActionMapping)acs[i]);

        am.setFast(true);
        getServletContext().setAttribute("org.apache.struts.action.MAPPINGS", am);
    }

    private void defaultMessageResourcesConfig(ModuleConfig config)
    {
        String value = null;
        MessageResourcesConfig mrc = config.findMessageResourcesConfig("org.apache.struts.action.MESSAGE");
        if(mrc == null)
        {
            mrc = new MessageResourcesConfig();
            mrc.setKey("org.apache.struts.action.MESSAGE");
            config.addMessageResourcesConfig(mrc);
        }
        value = getServletConfig().getInitParameter("application");
        if(value != null)
            mrc.setParameter(value);
        value = getServletConfig().getInitParameter("factory");
        if(value != null)
            mrc.setFactory(value);
        value = getServletConfig().getInitParameter("null");
        if(value != null)
            if(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes"))
                mrc.setNull(true);
            else
                mrc.setNull(false);
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
        log = LogFactory.getLog(class$org$apache$struts$action$ActionServlet != null ? class$org$apache$struts$action$ActionServlet : (class$org$apache$struts$action$ActionServlet = class$("org.apache.struts.action.ActionServlet")));
    }
}
