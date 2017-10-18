<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'reg_success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <style type="text/css">
<!--
.STYLE1 {
	color: #FF0000;
	font-weight: bold;
	font-size: 24px;
}
-->
    </style>
</head>
  
  <body>
  <table width="778"  border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
   <td width="778"     background="<%=basePath%>/images/top.jpg"  height="150" colspan="2" ></td>
  </tr>
  <tr> <td>&nbsp;</td>
  </tr>
  
  </table>
     <table width="778" height="264"  border="0" align="center"  background="<%=basePath%>/images/000.jpg" >
  <tr>
   <td width="337" height="93" ></td>
   <td width="431" > <span class="STYLE1">${qq}</span></td>
  </tr>
  
  <tr>
    <td height="165" colspan="2" align="center"><a href="<%=basePath%>/index.jsp">返回登录首页      </a>    
  </tr>
  </table>
      
  </body>
</html>
