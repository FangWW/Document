<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" %>
<%
/**
此程序实现数据库查询，本实现分页显示；<br>
版权所有： 杜江 ;<br>
**/
%>
<%
//定义数据库连接信息
String MM_conn_DRIVER = "oracle.jdbc.driver.OracleDriver";
String MM_conn_USERNAME = "test";
String MM_conn_PASSWORD = "test";
String MM_conn_STRING = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
//获取数据库连接和结果集
Driver DriverRecordset1 = (Driver)Class.forName(MM_conn_DRIVER).newInstance();
Connection ConnRecordset1 = DriverManager.getConnection(MM_conn_STRING,MM_conn_USERNAME,MM_conn_PASSWORD);
Statement stmt = ConnRecordset1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
ResultSet rs = stmt.executeQuery("SELECT * FROM TEST.TBL_USER ORDER BY ID ASC");

//页面初始化
String pageStr=(String)request.getParameter("pageNo");
int pageSize = 10;
if(pageStr==null || pageStr.equals(""))	pageStr="1";
int pageNo = java.lang.Integer.parseInt(pageStr);
if (pageNo==0) pageNo=1;

//获取记录总数
rs.last();  
int intRowCount = rs.getRow(); 
//记算总页数  
int intPageCount = (intRowCount+pageSize-1) / pageSize;
//调整待显示的页码  
if(pageNo>intPageCount) pageNo = intPageCount; 

//将记录指针定位到待显示页的第一条记录上  
rs.absolute((pageNo-1) * pageSize + 1); 
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>用户登录管理</title>
<style type="text/css">
<!--
.style3 {font-size: large}
-->
</style>
<script language='javascript'>
   function toUrl(num){
      window.location.href="jspJDBC.jsp?pageNo="+num;
   }
</script>
</head>

<body>
<table width="70%"  border="0" align="center" bgcolor="#00CC99">
   <tr>
     <th colspan="3" bgcolor="#00CCCC" scope="col"><span class="style3">用户登录管理</span>－－密码显示</th>
   </tr>
   <tr bgcolor="#FFFFFF">
    <th scope="col"><kbd>ID</kbd></th>
    <th scope="col"><kbd>用户名</kbd></th>
    <th scope="col"><kbd>密码</kbd></th>
  </tr>
 <%
 int i = 0;
 while(i<pageSize && !rs.isAfterLast())
 {
  %>
  <tr bgcolor="#FFFFFF">
    <th width="28%" scope="col"><%=rs.getObject("ID")%></th>
    <th width="27%" scope="col"><%=rs.getObject("NAME")%></th>
    <th width="45%" scope="col"><%=rs.getObject("PASSWORD")%></th>
  </tr>
<%
 rs.next();
 i++;
 }
%>
<tr>
    <th colspan="3" scope="row">
<%if (pageNo==1){
    out.print("首页 上一页"); 
    }else{
%>
<a href="javascript:toUrl('1');">首页</a>  <a href="javascript:toUrl('<%=pageNo-1%>');">上一页</a> 
<% } %>
<% if (pageNo+1>intPageCount){
     out.print ("下一页 尾页");
   }else{
%>
<a href="javascript:toUrl('<%=pageNo+1%>');">下一页</a> <a href="javascript:toUrl('<%=intPageCount%>');">尾页</a>
<% } %>
    </th>
  </tr>
</table>
</body>
</html>
<%
//关闭数据库连接，释放资源。
rs.close();
stmt.close();
ConnRecordset1.close();
%>

