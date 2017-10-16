<%@ page import="org.apache.struts.action.*,
                 java.util.Iterator,
                 com.webapp.upload.UploadForm"%>
<%@ page language="java" %>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-logic" prefix="logic" %>
<%@ taglib uri="struts-bean" prefix="bean" %>
<html:html locale="true">
<head>
<title><bean:message key="upload.title"/></title>
</head>
<html:base/>
<body>
<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
  <font color="red">
    ERROR:  Application resources not loaded -- check servlet container
    logs for error messages.
  </font>
</logic:notPresent>

<br /><br />

<html:form action="upload.do?queryParam=Successful" enctype="multipart/form-data">
<bean:message key="upload.form.text"/>
<br />
	<html:text property="theText" /><br /><br />
<bean:message key="upload.form.theFile"/><br />
<html:file property="theFile" /><br /><br />
<html:submit />

</html:form>
</body>
</html:html>