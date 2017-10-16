<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" %>
<%
/**
此文件演示使用Bean连接数据库，并查询TBL_USER表的所有数据记录<br>
版权所有： 杜江 ;<br>
**/
%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<jsp:useBean id="conn" scope="page" class="com.bean.JDBCConn" />

<html> 
<head> 
<title>Java实例100例</title> 
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language='javascript'>
   function toUrl(num){
      window.location.href="jspBeanList.jsp?pageNo="+num;
   }
</script>
<style type="text/css">
<!--
.style3 {font-size: large}
-->
</style>
</head> 

<%
String pageStr=(String)request.getParameter("pageNo");
int pageSize = 10;
if(pageStr==null || pageStr.equals(""))	pageStr="1";
int pageNo = java.lang.Integer.parseInt(pageStr);
if (pageNo==0) pageNo=1;

//设置参数
String sql="SELECT * FROM TEST.TBL_USER ORDER BY ID ASC";


//得到结果集
Vector vResult=new Vector();
vResult=conn.query(sql,pageNo,pageSize);
System.out.println("&&&&&"+vResult);

//计算翻页参数
int count=Integer.parseInt((String)vResult.elementAt(0));
int pageCount=(int)((count-1)/pageSize + 1);
%>
<body topmargin="40" leftmargin="0" bgcolor="#FFFFFF" text="#000000"> 

<table width="75%" border="0" align="center" bgcolor="#CCCCFF">
<tr ><td align="center"><span class="style3">用户登录管理</span>－－密码显示
</td>
</tr>
<tr ><td >
 <table width="100%" border="0" cellpadding="0">
   <tr bgcolor="#FFFFFF">
    <th scope="col"><kbd>ID</kbd></th>
    <th scope="col"><kbd>用户名</kbd></th>
    <th scope="col"><kbd>密码</kbd></th>
  </tr>
<%
for (int i=1;i<vResult.size();i++) {
  Vector vResultRow=new Vector();
  System.out.println("1111"+vResultRow);
  vResultRow=(Vector)vResult.elementAt(i);
   String str_ID=(String)vResultRow.elementAt(0);
   String str_Name=(String)vResultRow.elementAt(1);
   String str_PWD=(String)vResultRow.elementAt(2);

%>
 <tr bgcolor="#FFFFFF">
<td ><%=str_ID%></td>
<td ><%=str_Name%></td>
<td ><%=str_PWD%></td>
</tr>
<%} %>

</table>
<tr>
  <td colspan="2" align="right">
  <%if (pageNo==1){
    out.print("首页 上一页"); 
    }else{
%>
<a href="javascript:toUrl('1');">首页</a>  <a href="javascript:toUrl('<%=pageNo-1%>');">上一页</a> 
<% } %>
<% if (pageNo+1>pageCount){
     out.print ("下一页 尾页");
   }else{
%>
<a href="javascript:toUrl('<%=pageNo+1%>');">下一页</a> <a href="javascript:toUrl('<%=pageCount%>');">尾页</a>
<% } %>
  </td>
  </tr>
  </td></tr>
  
</table>
</body> 
</html> 



