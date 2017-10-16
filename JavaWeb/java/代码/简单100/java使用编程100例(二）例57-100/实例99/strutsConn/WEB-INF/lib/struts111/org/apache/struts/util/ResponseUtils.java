///////////////////////////////////////////////////////////
// DeJaved by mDeJava v1.0. Copyright 1999 MoleSoftware. //
//       To download last version of this software:      //
//            http://molesoftware.hypermatr.net          //
//               e-mail:molesoftware@mail.ru             //
///////////////////////////////////////////////////////////

package org.apache.struts.util;

import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.BodyContent;

// Referenced classes of package org.apache.struts.util:
//            RequestUtils, MessageResources

public class ResponseUtils
{

    protected static MessageResources messages = MessageResources.getMessageResources("org.apache.struts.util.LocalStrings");

    public ResponseUtils()
    {
    }

    public static String filter(String value)
    {
        if(value == null)
            return null;
        char content[] = new char[value.length()];
        value.getChars(0, value.length(), content, 0);
        StringBuffer result = new StringBuffer(content.length + 50);
        for(int i = 0; i < content.length; i++)
            switch(content[i])
            {
            case 60: // '<'
                result.append("&lt;");
                break;

            case 62: // '>'
                result.append("&gt;");
                break;

            case 38: // '&'
                result.append("&amp;");
                break;

            case 34: // '"'
                result.append("&quot;");
                break;

            case 39: // '\''
                result.append("&#39;");
                break;

            default:
                result.append(content[i]);
                break;

            }

        return result.toString();
    }

    public static void write(PageContext pageContext, String text)
        throws JspException
    {
        JspWriter writer = pageContext.getOut();
        try
        {
            writer.print(text);
        }
        catch(IOException e)
        {
            RequestUtils.saveException(pageContext, e);
            throw new JspException(messages.getMessage("write.io", e.toString()));
        }
    }

    public static void writePrevious(PageContext pageContext, String text)
        throws JspException
    {
        JspWriter writer = pageContext.getOut();
        if(writer instanceof BodyContent)
            writer = ((BodyContent)writer).getEnclosingWriter();
        try
        {
            writer.print(text);
        }
        catch(IOException e)
        {
            RequestUtils.saveException(pageContext, e);
            throw new JspException(messages.getMessage("write.io", e.toString()));
        }
    }

}
