<%@ taglib uri="struts-bean" prefix="bean" %>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-logic" prefix="logic" %>

<html:html locale="true">
<head>
<title><bean:message key="failes.title"/></title>
<html:base/>
</head>
<body bgcolor="white">
<html:errors />
<h3><bean:message key="failes.heading"/></h3>
<p><bean:message key="failes.message"/></p>
</body>
</html:html>
