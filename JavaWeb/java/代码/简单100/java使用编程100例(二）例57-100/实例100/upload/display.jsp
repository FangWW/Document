<%@ page language="java" %>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-logic" prefix="logic" %>
<%@ taglib uri="struts-bean" prefix="bean" %>
<html>
<head>
<title><bean:message key="display.title"/></title>
</head>

<body>
<b><bean:message key="display.text"/></b>: <%= request.getAttribute("text") %> <br />

<b><bean:message key="display.query"/></b>: <%= request.getAttribute("queryValue") %> <br />

<b><bean:message key="display.name"/></b>: <%= request.getAttribute("fileName") %> <br />

<b><bean:message key="display.content"/></b>: <%= request.getAttribute("contentType") %> <br />

<b><bean:message key="display.size"/></b>: <%= request.getAttribute("size") %> <br />

<hr />
<bean:message key="display.file"/>
<pre><%= request.getAttribute("data") %></pre>
<hr />
</body>
</html>