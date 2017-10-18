<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<script language="javascript" src="js/data.js"></script>
<script language="javaScript">
		function closeWin(){
		    opener.window.location.reload() ;	// 重新读取，刷新
			window.close() ;
		}

		</script>
<head>
<style type="text/css">


td{color:blue}
</style>
	

</head>
<body>
<center>
	<form action="updata.do" method="post" enctype="multipart/form-data"  onsubmit="">
	  <table border="0" width="63%" background="images/register.jpg">
        <tr align="center" valign="middle">
          <td height="36" colspan="3"><strong>我的资料</strong></td>
        </tr>
        <tr>
          <td width="22%" rowspan="7" align="center" valign="middle" ><img src="<%=basePath %>photo/${user_info.photo}"  width="100" height="100" align="top"  /></td>
          <td width="40%" height="25">昵称：</td>
          <td width="38%">账号：</td>
        </tr>
        <tr>
          <td><input type="text" name="nickname" id="nickname" value="${user_info.nickname}"></td>
          <td><input type="text" name="qq" id="qq" value="${user_info.qq}" readonly="readonly" ></td>
        </tr>
        <tr>
          <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="2">个性签名：</td>
        </tr>
        <tr>
          <td colspan="2"><input type="text" name="sign"  size="50" id="sign" value="${user_info.sign}"></td>
        </tr>
        <tr>
          <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td>性别：</td>
          <td >年龄：</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><select name="sex"  id="sex">
            <option value="${user_info.sex }"> ${user_info.sex}</option>
            <option value="男">男</option>
            <option value="女">女</option>
          </select></td>
          <td ><select name="age" id="age" >
              <option value="${user_info.age}">${user_info.age}</option>
               <option value="15">15</option>
            <option value="16">16</option>
            <option value="17">17</option>
            <option value="18">18</option>
            <option value="19">19</option>
            <option value="20">20</option>
            <option value="21">21</option>
            <option value="22">22</option>
            <option value="23">23</option>
            <option value="24">24</option>
            <option value="25">25</option>
            <option value="26">26</option>
            <option value="27">27</option>
            <option value="28">28</option>
            <option value="29">29</option>
            <option value="30">30</option>
            <option value="31">31</option>
            <option value="32">32</option>
            <option value="33">33</option>
            <option value="34">34</option>
            <option value="35">35</option>
          </select></td>
        </tr>
        <tr>
          <td colspan="3">&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>血型：</td>
          <td width="40%">生日：</td>
        </tr>
        <tr>
          <td valign="middle" >&nbsp;</td>
          <td><select name="select"  >
            <option value="">其他</option>
          </select></td>
          <td><input type="text" name="birthday" id="birthday" value="${user_info.birthday }" maxlength="15" size="12"
					onClick='popUpCalendar(this, this, "yyyy-mm-dd")' readonly="readonly">
            <font color="red" size="2" > *单击鼠标左键修改</font></td>
        </tr>
        <tr>
          <td colspan="3">&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="2">个人头像：</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="2"><input type="file" name="photo" >
          <font color="red" size="2" >*可以不更改</font></td>
        </tr>
		  <tr>
          <td colspan="3">&nbsp;</td>
        </tr>
        <tr align="center" valign="middle">
          <td colspan="4" align="right"><div align="center">
              <input type="hidden" name="ph_oldName" value="${user_info.photo }" >
              <input name="submit" type="submit" value="确定" >
            &nbsp;&nbsp;&nbsp;
            <input name="a" type="button" value="取消" onClick="closeWin();">
          </div></td>
        </tr>
      </table>
	</form>

</center>
</body>
</html>