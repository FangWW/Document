///////////////////////////////////////////////////////////
// DeJaved by mDeJava v1.0. Copyright 1999 MoleSoftware. //
//       To download last version of this software:      //
//            http://molesoftware.hypermatr.net          //
//               e-mail:molesoftware@mail.ru             //
///////////////////////////////////////////////////////////

package org.apache.struts.util;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package org.apache.struts.util:
//            MessageResourcesFactory

public abstract class MessageResources
    implements Serializable
{

    protected static Log log = null;
    protected String config = null;
    protected Locale defaultLocale = null;
    protected MessageResourcesFactory factory = null;
    protected HashMap formats = null;
    protected boolean returnNull = false;
    protected static MessageResourcesFactory defaultFactory = null;
    static Class class$org$apache$struts$util$MessageResources = null; /* synthetic field */

    public String getConfig()
    {
        return config;
    }

    public MessageResourcesFactory getFactory()
    {
        return factory;
    }

    public boolean getReturnNull()
    {
        return returnNull;
    }

    public void setReturnNull(boolean returnNull)
    {
        this.returnNull = returnNull;
    }

    public MessageResources(MessageResourcesFactory factory, String config)
    {
        this(factory, config, false);
    }

    public MessageResources(MessageResourcesFactory factory, String config, boolean returnNull)
    {
        this.config = null;
        defaultLocale = Locale.getDefault();
        this.factory = null;
        formats = new HashMap();
        this.returnNull = false;
        this.factory = factory;
        this.config = config;
        this.returnNull = returnNull;
    }

    public String getMessage(String key)
    {
        return getMessage((Locale)null, key);
    }

    public String getMessage(String key, Object args[])
    {
        return getMessage((Locale)null, key, args);
    }

    public String getMessage(String key, Object arg0)
    {
        return getMessage((Locale)null, key, arg0);
    }

    public String getMessage(String key, Object arg0, Object arg1)
    {
        return getMessage((Locale)null, key, arg0, arg1);
    }

    public String getMessage(String key, Object arg0, Object arg1, Object arg2)
    {
        return getMessage((Locale)null, key, arg0, arg1, arg2);
    }

    public String getMessage(String key, Object arg0, Object arg1, Object arg2, Object arg3)
    {
        return getMessage((Locale)null, key, arg0, arg1, arg2, arg3);
    }

    public abstract String getMessage(Locale locale, String s);

    public String getMessage(Locale locale, String key, Object args[])
    {
        MessageFormat format;
        if(locale == null)
            locale = defaultLocale;
        format = null;
        String formatKey = messageKey(locale, key);
        HashMap hashmap = formats;
        JVM INSTR monitorenter ;
        String formatString;
        format = (MessageFormat)formats.get(formatKey);
        if(format != null)
            break MISSING_BLOCK_LABEL_128;
        formatString = getMessage(locale, key);
        if(formatString != null) goto _L2; else goto _L1
_L1:
        if(!returnNull) goto _L4; else goto _L3
_L3:
        null;
        hashmap;
        JVM INSTR monitorexit ;
        return;
_L4:
        "???" + formatKey + "???";
        hashmap;
        JVM INSTR monitorexit ;
        return;
_L2:
        format = new MessageFormat(escape(formatString));
        formats.put(formatKey, format);
        hashmap;
        JVM INSTR monitorexit ;
        break MISSING_BLOCK_LABEL_142;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        return format.format(((Object) (args)));
    }

    public String getMessage(Locale locale, String key, Object arg0)
    {
        Object args[] = new Object[1];
        args[0] = arg0;
        return getMessage(locale, key, args);
    }

    public String getMessage(Locale locale, String key, Object arg0, Object arg1)
    {
        Object args[] = new Object[2];
        args[0] = arg0;
        args[1] = arg1;
        return getMessage(locale, key, args);
    }

    public String getMessage(Locale locale, String key, Object arg0, Object arg1, Object arg2)
    {
        Object args[] = new Object[3];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        return getMessage(locale, key, args);
    }

    public String getMessage(Locale locale, String key, Object arg0, Object arg1, Object arg2, Object arg3)
    {
        Object args[] = new Object[4];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        args[3] = arg3;
        return getMessage(locale, key, args);
    }

    public boolean isPresent(String key)
    {
        return isPresent(null, key);
    }

    public boolean isPresent(Locale locale, String key)
    {
        String message = getMessage(locale, key);
        if(message == null)
            return false;
        return !message.startsWith("???") || !message.endsWith("???");
    }

    protected String escape(String string)
    {
        if(string == null || string.indexOf(39) < 0)
            return string;
        int n = string.length();
        StringBuffer sb = new StringBuffer(n);
        for(int i = 0; i < n; i++)
        {
            char ch = string.charAt(i);
            if(ch == '\'')
                sb.append('\'');
            sb.append(ch);
        }

        return sb.toString();
    }

    protected String localeKey(Locale locale)
    {
        if(locale == null)
            return "";
        else
            return locale.toString();
    }

    protected String messageKey(Locale locale, String key)
    {
        return localeKey(locale) + "." + key;
    }

    protected String messageKey(String localeKey, String key)
    {
        return localeKey + "." + key;
    }

    public static synchronized MessageResources getMessageResources(String config)
    {
        if(defaultFactory == null)
            defaultFactory = MessageResourcesFactory.createFactory();
        return defaultFactory.createResources(config);
    }

    public void log(String message)
    {
        log.debug(message);
    }

    public void log(String message, Throwable throwable)
    {
        log.debug(message, throwable);
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
        log = LogFactory.getLog(class$org$apache$struts$util$MessageResources != null ? class$org$apache$struts$util$MessageResources : (class$org$apache$struts$util$MessageResources = class$("org.apache.struts.util.MessageResources")));
    }
}
