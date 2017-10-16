<html>
<%@ page import="java.io.InputStream,
                 java.io.IOException,
                 javax.xml.parsers.SAXParser,
                 javax.xml.parsers.SAXParserFactory"
   session="false" %>
 <%
    /*
     * The Apache Software License, Version 1.1
     *
     * Copyright (c) 2002 The Apache Software Foundation.  All rights
     * reserved.
     *
     * Redistribution and use in source and binary forms, with or without
     * modification, are permitted provided that the following conditions
     * are met:
     *
     * 1. Redistributions of source code must retain the above copyright
     *    notice, this list of conditions and the following disclaimer.
     *
     * 2. Redistributions in binary form must reproduce the above copyright
     *    notice, this list of conditions and the following disclaimer in
     *    the documentation and/or other materials provided with the
     *    distribution.
     *
     * 3. The end-user documentation included with the redistribution, if
     *    any, must include the following acknowlegement:
     *       "This product includes software developed by the
     *        Apache Software Foundation (http://www.apache.org/)."
     *    Alternately, this acknowlegement may appear in the software itself,
     *    if and wherever such third-party acknowlegements normally appear.
     *
     * 4. The names "The Jakarta Project", "Ant", and "Apache Software
     *    Foundation" must not be used to endorse or promote products derived
     *    from this software without prior written permission. For written
     *    permission, please contact apache@apache.org.
     *
     * 5. Products derived from this software may not be called "Apache"
     *    nor may "Apache" appear in their names without prior written
     *    permission of the Apache Group.
     *
     * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
     * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
     * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
     * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
     * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
     * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
     * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
     * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
     * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
     * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
     * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
     * SUCH DAMAGE.
     * ====================================================================
     *
     * This software consists of voluntary contributions made by many
     * individuals on behalf of the Apache Software Foundation.  For more
     * information on the Apache Software Foundation, please see
     * <http://www.apache.org/>.
     */
%>
<head>
<title>Axis Happiness Page</title>
</head>
<body bgcolor='#ffffff'>
<%!

    /*
     * Happiness tests for axis. These look at the classpath and warn if things
     * are missing. Normally addng this much code in a JSP page is mad
     * but here we want to validate JSP compilation too, and have a drop-in
     * page for easy re-use
     * @author Steve 'configuration problems' Loughran
     * @author dims
     * @author Brian Ewins
     */


    /**
     * Get a string providing install information.
     * TODO: make this platform aware and give specific hints
     */
    public String getInstallHints(HttpServletRequest request) {

        String hint=
            "<B><I>Note:</I></B> On Tomcat 4.x and Java1.4, you may need to put libraries that contain "
            +"java.* or javax.* packages into CATALINA_HOME/common/lib"
            +"<br>jaxrpc.jar and saaj.jar are two such libraries.";
        return hint;
    }

    /**
     * test for a class existing
     * @param classname
     * @return class iff present
     */
    Class classExists(String classname) {
        try {
            return Class.forName(classname);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * test for resource on the classpath
     * @param resource
     * @return true iff present
     */
    boolean resourceExists(String resource) {
        boolean found;
        InputStream instream=this.getClass().getResourceAsStream(resource);
        found=instream!=null;
        if(instream!=null) {
            try {
                instream.close();
            } catch (IOException e) {
            }
        }
        return found;
    }

    /**
     * probe for a class, print an error message is missing
     * @param out stream to print stuff
     * @param category text like "warning" or "error"
     * @param classname class to look for
     * @param jarFile where this class comes from
     * @param errorText extra error text
     * @param homePage where to d/l the library
     * @return the number of missing classes
     * @throws IOException
     */
    int probeClass(JspWriter out,
                   String category,
                   String classname,
                   String jarFile,
                   String description,
                   String errorText,
                   String homePage) throws IOException {
        try {
            Class clazz = classExists(classname);
            if(clazz == null)  {
               String url="";
               if(homePage!=null) {
                  url="<br>  See <a href="+homePage+">"+homePage+"</a>";
               }
               out.write("<p>"+category+": could not find class "+classname
                   +" from file <b>"+jarFile
                   +"</b><br>  "+errorText
                   +url
                   +"<p>");
               return 1;
            } else {
               String location = getLocation(out, clazz);
               if(location == null) {
                  out.write("Found "+ description + " (" + classname + ")<br>");
               }
               else {
                  out.write("Found "+ description + " (" + classname + ") at " + location + "<br>");
               }
               return 0;
            }
        } catch(NoClassDefFoundError ncdfe) { 
            String url="";
            if(homePage!=null) {
                url="<br>  See <a href="+homePage+">"+homePage+"</a>";
            }
            out.write("<p>"+category+": could not find a dependency"
                    +" of class "+classname
                    +" from file <b>"+jarFile
                    +"</b><br> "+errorText
                    +url
                    +"<br>The root cause was: "+ncdfe.getMessage()
                    +"<br>This can happen e.g. if "+classname+" is in" 
                    +" the 'common' classpath, but a dependency like "
                    +" activation.jar is only in the webapp classpath."
                    +"<p>");
            return 1;
        }
    }

    /**
     * get the location of a class
     * @param out
     * @param clazz
     * @return the jar file or path where a class was found
     */

    String getLocation(JspWriter out,
                       Class clazz) {
        try {
            java.net.URL url = clazz.getProtectionDomain().getCodeSource().getLocation();
            String location = url.toString();
            if(location.startsWith("jar")) {
                url = ((java.net.JarURLConnection)url.openConnection()).getJarFileURL();
                location = url.toString();
            } 
            
            if(location.startsWith("file")) {
                java.io.File file = new java.io.File(url.getFile());
                return file.getAbsolutePath();
            } else {
                return url.toString();
            }
        } catch (Throwable t){
        }
        return "an unknown location";
    }

    /**
     * a class we need if a class is missing
     * @param out stream to print stuff
     * @param classname class to look for
     * @param jarFile where this class comes from
     * @param errorText extra error text
     * @param homePage where to d/l the library
     * @throws IOException when needed
     * @return the number of missing libraries (0 or 1)
     */
    int needClass(JspWriter out,
                   String classname,
                   String jarFile,
                   String description,
                   String errorText,
                   String homePage) throws IOException {
        return probeClass(out,
                "<b>Error</b>",
                classname,
                jarFile,
                description,
                errorText,
                homePage);
    }

    /**
     * print warning message if a class is missing
     * @param out stream to print stuff
     * @param classname class to look for
     * @param jarFile where this class comes from
     * @param errorText extra error text
     * @param homePage where to d/l the library
     * @throws IOException when needed
     * @return the number of missing libraries (0 or 1)
     */
    int wantClass(JspWriter out,
                   String classname,
                   String jarFile,
                   String description,
                   String errorText,
                   String homePage) throws IOException {
        return probeClass(out,
                "<b>Warning</b>",
                classname,
                jarFile,
                description,
                errorText,
                homePage);
    }

    /**
     * probe for a resource existing,
     * @param out
     * @param resource
     * @param errorText
     * @throws Exception
     */
    int wantResource(JspWriter out,
                      String resource,
                      String errorText) throws Exception {
        if(!resourceExists(resource)) {
            out.write("<p><b>Warning</b>: could not find resource "+resource
                        +"<br>"
                        +errorText);
            return 0;
        } else {
            out.write("found "+resource+"<br>");
            return 1;
        }
    }


    /**
     *  get servlet version string
     *
     */

    public String getServletVersion() {
        ServletContext context=getServletConfig().getServletContext();
        int major = context.getMajorVersion();
        int minor = context.getMinorVersion();
        return Integer.toString(major) + '.' + Integer.toString(minor);
    }



    /**
     * what parser are we using.
     * @return the classname of the parser
     */
    private String getParserName() {
        SAXParser saxParser = getSAXParser();
        if (saxParser == null) {
            return "Could not create an XML Parser";
        }

        // check to what is in the classname
        String saxParserName = saxParser.getClass().getName();
        return saxParserName;
    }

    /**
     * Create a JAXP SAXParser
     * @return parser or null for trouble
     */
    private SAXParser getSAXParser() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        if (saxParserFactory == null) {
            return null;
        }
        SAXParser saxParser = null;
        try {
            saxParser = saxParserFactory.newSAXParser();
        } catch (Exception e) {
        }
        return saxParser;
    }

    /**
     * get the location of the parser
     * @return path or null for trouble in tracking it down
     */

    private String getParserLocation(JspWriter out) {
        SAXParser saxParser = getSAXParser();
        if (saxParser == null) {
            return null;
        }
        String location = getLocation(out,saxParser.getClass());
        return location;
    }
    %>
<html><head><title>Axis Happiness Page</title></head>
<body>
<h1>Axis Happiness Page</h1>
<h2>Examining webapp configuration</h2>

<p>
<h3>Needed Components</h3>
<%
    int needed=0,wanted=0;

    /**
     * the essentials, without these Axis is not going to work
     */
    needed=needClass(out, "javax.xml.soap.SOAPMessage",
            "saaj.jar",
            "SAAJ API",
            "Axis will not work",
            "http://xml.apache.org/axis/");

    needed+=needClass(out, "javax.xml.rpc.Service",
            "jaxrpc.jar",
            "JAX-RPC API",
            "Axis will not work",
            "http://xml.apache.org/axis/");

    needed+=needClass(out, "org.apache.axis.transport.http.AxisServlet",
            "axis.jar",
            "Apache-Axis",
            "Axis will not work",
            "http://xml.apache.org/axis/");

    needed+=needClass(out, "org.apache.commons.discovery.Resource",
            "commons-discovery.jar",
            "Jakarta-Commons Discovery",
            "Axis will not work",
            "http://jakarta.apache.org/commons/discovery.html");

    needed+=needClass(out, "org.apache.commons.logging.Log",
            "commons-logging.jar",
            "Jakarta-Commons Logging",
            "Axis will not work",
            "http://jakarta.apache.org/commons/logging.html");

    needed+=needClass(out, "org.apache.log4j.Layout",
            "log4j-1.2.8.jar",
            "Log4j",
            "Axis may not work",
            "http://jakarta.apache.org/log4j");

    //should we search for a javax.wsdl file here, to hint that it needs
    //to go into an approved directory? because we dont seem to need to do that.
    needed+=needClass(out, "com.ibm.wsdl.factory.WSDLFactoryImpl",
            "wsdl4j.jar",
            "IBM's WSDL4Java",
            "Axis will not work",
            null);

    needed+=needClass(out, "javax.xml.parsers.SAXParserFactory",
            "xerces.jar",
            "JAXP implementation",
            "Axis will not work",
            "http://xml.apache.org/xerces-j/");

    needed+=needClass(out,"javax.activation.DataHandler",
            "activation.jar",
            "Activation API",
            "Axis will not work",
            "http://java.sun.com/products/javabeans/glasgow/jaf.html");
%>
<h3>Optional Components</h3>
<%
    /*
     * now the stuff we can live without
     */
    wanted+=wantClass(out,"javax.mail.internet.MimeMessage",
            "mail.jar",
            "Mail API",
            "Attachments will not work",
            "http://java.sun.com/products/javamail/");

    wanted+=wantClass(out,"org.apache.xml.security.Init",
            "xmlsec.jar",
            "XML Security API",
            "XML Security is not supported",
            "http://xml.apache.org/security/");

    wanted += wantClass(out, "javax.net.ssl.SSLSocketFactory",
            "jsse.jar or java1.4+ runtime",
            "Java Secure Socket Extension",
            "https is not supported",
            "http://java.sun.com/products/jsse/");
    /*
     * resources on the classpath path
     */
    /* broken; this is a file, not a resource
    wantResource(out,"/server-config.wsdd",
            "There is no server configuration file;"
            +"run AdminClient to create one");
    */
    /* add more libraries here */

    out.write("<h3>");
    //is everythng we need here
    if(needed==0) {
       //yes, be happy
        out.write("<i>The core axis libraries are present. </i>");
    } else {
        //no, be very unhappy
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        out.write("<i>"
                +needed
                +" core axis librar"
                +(needed==1?"y is":"ies are")
                +" missing</i>");
    }
    //now look at wanted stuff
    if(wanted>0) {
        out.write("<i>"
                +wanted
                +" optional axis librar"
                +(wanted==1?"y is":"ies are")
                +" missing</i>");
    } else {
        out.write("The optional components are present.");
    }
    out.write("</h3>");
    //hint if anything is missing
    if(needed>0 || wanted>0 ) {
        out.write(getInstallHints(request));
    }

    %>
    <p>
    <B><I>Note:</I></B> Even if everything this page probes for is present, there is no guarantee your
    web service will work, because there are many configuration options that we do
    not check for. These tests are <i>necessary</i> but not <i>sufficient</i>
    <hr>

    <h2>Examining Application Server</h2>
    <%
        String servletVersion=getServletVersion();
        String xmlParser=getParserName();
        String xmlParserLocation = getParserLocation(out);

    %>
    <table>
        <tr><td>Servlet version</td><td><%= servletVersion %></td></tr>
        <tr><td>XML Parser</td><td><%= xmlParser %></td></tr>
        <tr><td>XML ParserLocation</td><td><%= xmlParserLocation %></td></tr>
    </table>
<% if(xmlParser.indexOf("crimson")>=0) { %>
    <p>
    <b>We recommend <a href="http://xml.apache.org/xerces2-j/">Xerces 2</a>
        over Crimson as the XML parser for Axis</b>
    </p>
<%    } %>

    <h2>Examining System Properties</h2>
<%
    /** 
     * Dump the system properties
     */
    java.util.Enumeration e=null;
    try {
        e= System.getProperties().propertyNames();
    } catch (SecurityException se) {
    }
    if(e!=null) {
        out.write("<pre>");
        for (;e.hasMoreElements();) {
            String key = (String) e.nextElement();
            out.write(key + "=" + System.getProperty(key)+"\n");
        }
        out.write("</pre><p>");
    } else {
        out.write("System properties are not accessible<p>");
    }
%>
    <hr>
    Platform: <%= getServletConfig().getServletContext().getServerInfo()  %>
</body>
</html>


