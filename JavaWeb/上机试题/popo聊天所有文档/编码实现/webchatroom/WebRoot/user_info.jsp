<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

<head>
	<title></title>
<script type="text/javascript" src="js/jquery-1.4.1.js"></script>
<style type="text/css">

.STYLE1 {
	font-size: small;
	font-weight: bold;
	
}
td{color:blue}
</style>

<script language="javaScript">
		function closeWin(){
		    opener.window.location.reload() ;	// 重新读取，刷新
			window.close() ;
		}
</script>
</head>
<body>
<center>
	<form >
	  <table border="0" width="539" background="images/register.jpg" height="435">
	  
        <tr align="center" valign="middle">
          <td height="20" colspan="3" align="left"><h4 > ${user_info.nickname}的资料</h4></td>
        </tr>
        <tr>
          <td width="22%" rowspan="6" align="center" valign="middle"  ><img src="<%=basePath %>photo/${user_info.photo}"  width="100" height="100" align="middle" /></td>
          <td height="25" colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="2">${user_info.nickname} &nbsp; ${user_info.qq} </td>
        </tr>
        <tr>
          <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="3">个性签名：${user_info.sign}</td>
        </tr>
        <tr>
          <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="2">性别：${user_info.sex}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  年龄：${user_info.age} <br></td>
        </tr>
        <tr>
          <td width="22%" align="center" valign="middle" ><br></td>
          <td colspan="2"><br></td>
        </tr>
        <tr>
          <td width="22%" align="center" valign="middle" ><br></td>
          <td colspan="2"><br> 
                                            生日：${user_info.birthday}&nbsp;&nbsp; 血型：--<br></td>
        </tr>
        <tr>
          <td align="right" valign="middle" >&nbsp;</td>
          <td width="53%" align="right" valign="middle" >&nbsp;</td>
        </tr>
        <tr align="center" valign="middle">
          <td align="right">&nbsp;</td>
          <td ></td>
        </tr><tr><td valign="top"><br></td><td valign="top"><br></td><td valign="top"><br></td></tr>
      </table>
	</form>

</center>
</body>
</html>