<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" %>
<%@ page import="com.bean.Browser"%>
<%
Browser BR = new Browser(request, session);
%>
<html>
<head>
<title>Java实例100例</title>
</head>
<body>
<p>
浏览器名：<%=BR.getName()%><br>
浏览器版本：<%=BR.getVersion()%><br>
浏览器使用语言：<%=BR.getLanguage()%><br>
浏览器主版本号：<%=BR.getMainVersion()%><br>
浏览器小版本号：<%=BR.getMinorVersion()%><br>
浏览器出品公司：<%=BR.getCompany()%><br>
</p>
<p>
客户所在时区：<%=BR.getLocale()%><br>
使用的操作系统：<%=BR.getOs()%><br>
</p>
</body>
</html>
