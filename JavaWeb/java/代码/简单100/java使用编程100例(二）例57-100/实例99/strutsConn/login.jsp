<%@ taglib uri="struts-bean" prefix="bean" %>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-logic" prefix="logic" %>

<html:html locale="true">
<head>
<title><bean:message key="login.title"/></title>
<html:base/>
</head>
<body bgcolor="white">

<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
  <font color="red">
    ERROR:  Application resources not loaded -- check servlet container
    logs for error messages.
  </font>
</logic:notPresent>
<h3><bean:message key="login.heading"/></h3>
<p><bean:message key="login.message"/></p>
<html:errors/>
<html:form action="/login" focus="name" >
  <bean:message key="login.form.name"/>
  <html:text property="name" size="20" maxlength="50"/>
  <br>
  <bean:message key="login.form.pass"/>
  <html:password property="password" size="20" maxlength="50"/>
  <br>
  <br>
  <html:submit>
    <bean:message key="form.submit"/>
  </html:submit>
</html:form>

</body>
</html:html>
