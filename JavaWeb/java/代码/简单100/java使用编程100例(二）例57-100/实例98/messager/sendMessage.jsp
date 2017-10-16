<%@ taglib uri="struts-bean" prefix="bean" %>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-logic" prefix="logic" %>

<html:html locale="true">
<head>
<title><bean:message key="sendMessage.title"/></title>
<html:base/>
</head>
<body bgcolor="white">

<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
  <font color="red">
    ERROR:  Application resources not loaded -- check servlet container
    logs for error messages.
  </font>
</logic:notPresent>
<h3><bean:message key="sendMessage.heading"/></h3>
<p><bean:message key="sendMessage.message"/></p>
<html:errors/>
<html:form action="/sendMessage" focus="name" >
  <bean:message key="sendMessage.form.name"/>
  <html:text property="name" size="20" maxlength="50"/>
  <br>
  <bean:message key="sendMessage.form.email"/>
  <html:text property="email" size="20" maxlength="50"/>
  <br>
  <bean:message key="sendMessage.form.message"/>
  <br>
  <html:textarea property="message" cols="50" rows="5"/>
  <br>
  <html:submit>
    <bean:message key="sendMessage.form.submit"/>
  </html:submit>
</html:form>

</body>
</html:html>
