<%@ taglib uri="struts-bean" prefix="bean" %>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-logic" prefix="logic" %>

<html:html locale="true">
<head>
<title><bean:message key="showMenu.title"/></title>
<html:base/>
</head>
<body bgcolor="white">
<html:errors />
<h3><bean:message key="showMenu.heading"/></h3>
<bean:message key="login.weclome"/>
<bean:message key="login.form.name"/>:
<bean:write name="loginForm" property="name" scope="request"/><br>
<br>
<!-- 这里显示一个菜单，为了演示用-->
<pre>
<bean:message key="menu.heading"/>
<ul>
 <li><bean:message key="menu.manager"/></li>
 <li><bean:message key="menu.logout"/></li>
</ul>
</pre>
</body>
</html:html>
