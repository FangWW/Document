<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="com.lsy.model.UserInfo"%>
<%@ page import="com.lsy.vo.User"%>
<%@ page import="java.util.*"%>
<script type="text/javascript" src="js/jquery-1.4.1.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <style>
  .color{
	  background-color:#D8CAFF;
   }

      </style>
<script type="text/javascript">


 $(function(){
	 $(".tab tr").hover(function(){$(this).addClass("color")},
		  function(){$(this).removeClass("color")}			 
	  )
	  });
</script>

<%
UserInfo list=UserInfo.getInstance();
Vector vector=list.getList();
int amount=0;
%>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
  <tr><td width="19%" height="38" align="right" class="word_orange ">欢迎&nbsp;&nbsp; <br></td> <td width="81%"><span class="word_orange ">来到POPO聊天平台！</span></td></tr>
  <tr>
  <td height="23" align="right"><a  href="#" onclick="set('所有人')"></a><td><a  href="#" onclick="set('所有人')">所有人</a></td></td>
  </tr>  
 <%if(vector!=null&&vector.size()>0){
    User user=null;
	String user_qq="";	
	String nickname="";
	String photo=""; 
	
	amount=vector.size();
		for(int i=0;i<amount;i++){
		     user=(User)vector.elementAt(i);
		   
		%>
  <tr>
    <td height="" align="left"><a href="#" onclick="goView('user.do?command=FindByQq&qq=<%=user.getQq()%>')"> <img    src="images/girl.jpg" ondblclick="" width="40" height="40" /></a> </td>
    <td height="" align="left"><font color=white> <a href="#" onclick="set('<%=user.getNickname() %>(<%=user.getQq()%>)')"><%=user.getNickname() %>(<%=user.getQq()%>)</a> </font></td>
 
  </tr>
<%}}%>
<tr>
<td height="30" align="right">当前</td>
<td>在线[<font color=white><%=amount%></font>]人</tr>
</table>
