<%-- 指定文件编码 --%>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%-- 导入日期类 --%>
<%@ page import="java.util.Date" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>使用JSP的简单实例</title>
<style type="text/css">
<!--
.style1 {
	font-size: 16px;
	font-weight: bold;
}
-->
</style>
</head>
<%
String message = "你好，这时JSP动态输出的页面提示：";
%>
<body>
<p align="center" class="style1">欢迎阅读《Java实用一百例》</p>
<p align="center">你好，这时HTML语言输出的静态提示！</p>
<p align="center"><%= message %> </p>
<p align="center">现在时间：<%= new Date() %> </p>
</body>
</html>
