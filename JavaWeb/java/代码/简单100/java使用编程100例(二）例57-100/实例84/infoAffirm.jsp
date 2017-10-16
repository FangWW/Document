<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
//获取客户提交信息
String name = (String)request.getParameter("username");
String nick = (String)request.getParameter("nickname");
String sex = (String)request.getParameter("sex");
String educate = (String)request.getParameter("educate");
String birthday = (String)request.getParameter("birthday");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>用户信息确认</title>
</head>

<body>
<table width="70%"  border="0" align="center" bgcolor="#00CC33">
<tr><td >
<table width="100%"  border="0" align="center">
  <tr>
    <th colspan="2" scope="col">用户信息填写</th>
  </tr>
  <tr bgcolor="#FFFFFF">
    <th width="27%" scope="row"><div align="right">用户名：</div></th>
    <td width="73%"><%=name%></td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <th scope="row"><div align="right">昵称：</div></th>
    <td><%=nick%></td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <th scope="row"><div align="right">性别：</div></th>
    <td><% if(sex.equals("1")) out.print("男"); else out.print("女");%></td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <th scope="row"><div align="right">学历：</div></th>
    <td><%
	int ii = Integer.parseInt(educate);
	switch (ii){
	   case 1 :
	     out.print("小学");
	     break;
	  case 2 :
	     out.print("初中");
	     break;
	  case 3 :
	     out.print("高中");
	     break;
	case 4 :
	     out.print("大学");
	     break;
	}
	%></td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <th scope="row"><div align="right">出生：</div></th>
    <td><%=birthday%></td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <th scope="row"><div align="right"></div></th>
    <td>&nbsp;</td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <th scope="row"><div align="right"></div></th>
    <td><input type="submit" name="Submit" value="确定">
            </td>
  </tr>
</table>
</td></tr>
</table>
</body>
</html>
