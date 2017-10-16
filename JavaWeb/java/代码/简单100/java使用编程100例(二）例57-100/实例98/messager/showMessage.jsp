<%@ taglib uri="struts-bean" prefix="bean" %>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-logic" prefix="logic" %>

<html:html locale="true">
<head>
<title><bean:message key="showMessage.title"/></title>
<html:base/>
</head>
<body bgcolor="white">
<html:errors />
<h3><bean:message key="showMessage.heading"/></h3>
<bean:message key="sendMessage.form.name"/>:
<bean:write name="sendMessageForm" property="name" scope="request"/><br>
<bean:message key="sendMessage.form.email"/>:
<bean:write name="sendMessageForm" property="email" scope="request"/><br>
<bean:message key="sendMessage.form.message"/>:
<pre><bean:write name="sendMessageForm" property="message" scope="request"/></pre><br>
</body>
</html:html>
