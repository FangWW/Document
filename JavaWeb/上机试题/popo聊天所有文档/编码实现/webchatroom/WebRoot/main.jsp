<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<style type="text/css">

.table {
	font-size: small;
	font-weight: bold;
	color:white
	
}
.online{
color:white
}

</style>
<title>POPO聊天平台</title>
<link href="CSS/style.css" rel="stylesheet">
<script language="javascript" src="js/jquery-1.5.1.js"></script>
<script language="javascript" src="js/jquery-1.5.1.min.js"></script>
 <script src="alerts/jquery.alerts.js" type="text/javascript"></script> 
 <script language="javascript" src="js/ajax.js"></script>
 <link href="alerts/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" /> 
<script language="javascript">
window.setInterval("showOwnContent();",1000);
window.setInterval("showContent();",1000);
window.setInterval("showOnline();",10000);
var sysBBS="<span style='font-size:14px; line-height:30px;'>欢迎光临POPO聊天平台，请遵守聊天室规则，不要使用不文明用语。</span><br><span style='line-height:22px;'>";
var BBS="<span style='font-size:14px; line-height:30px;'>私聊区</span><br><span style='line-height:22px;'>";
//此处需要加?nocache="+new Date().getTime()，否则将出现在线人员列表不更新的情况
function showOnline(){
	var loader=new net.AjaxRequest("online.jsp?nocache="+new Date().getTime(),deal_online,onerror,"GET");
}
//公聊信息
function showContent(){
	var loader1=new net.AjaxRequest("Messages?action=getMessages&nocache="+new Date().getTime(),deal_content,onerror,"GET");
}
//私聊信息
function showOwnContent(){
	var loader2=new net.AjaxRequest("Messages?action=getOwnMessages&nocache="+new Date().getTime(),deal_owncontent,onerror,"GET");
}

function onerror(){
	alert("很抱歉，服务器出现错误，当前窗口将关闭！");
	window.opener=null;
	window.close();
}

function deal_online(){
	online.innerHTML=this.req.responseText;
}
function deal_content(){
 
	var returnValue=this.req.responseText;		//获取Ajax处理页的返回值
	var h=returnValue.replace(/\s/g,"");	//去除字符串中的Unicode空白符
	if(h=="error"){
		//alert("您的账户已经过期，请重新登录！");
		Exit();
	}else{
	
	    content.innerHTML=sysBBS+returnValue+"</span>";
		document.getElementById('content').scrollTop = document.getElementById('content').scrollHeight*2;	//当聊天信息超过一屏时，设置最先发送的聊天信息不显示
	   
	}
	
}

function deal_owncontent(){
    
	var returnValue=this.req.responseText;		//获取Ajax处理页的返回值
	var h=returnValue.replace(/\s/g,"");	//去除字符串中的Unicode空白符
	if(h=="error"){
		
		Exit();
	}else{
	
	    owncontent.innerHTML=BBS+returnValue+"</span>";
		document.getElementById('owncontent').scrollTop = document.getElementById('owncontent').scrollHeight*2;	//当聊天
		
	    
	   
	}
}


window.onload=function(){
	showContent();	
	showOwnContent();					//当页面载入后显示聊天内容
	showOnline();						//当页面载入后显示在线人员列表
}
</script>
<script language="javascript">
     
	function send(){	//验证聊天信息并发送
	
		if(form1.to.value==""){
			alert("请选择聊天对象！");return false;
		}
		if(form1.content1.value==""){
			alert("发送信息不可以为空！");form1.content1.focus();return false;
		}
		var param="from="+form1.from.value+"&face="+form1.face.value+"&color="+form1.color.value+"&to="+form1.to.value+"&content="+	form1.content1.value;
        var loader=new net.AjaxRequest("Messages?action=sendMessage",deal_send,onerror,"POST",param);
        
       if(form1.content1.value!=""){
			$("#content1").val("");
		}
	}
function deal_send(){
content.innerHTML=sysBBS+this.req.responseText+"</span>";
}
	function Exit(){
		window.location.href="leave.jsp";
		alert("欢迎您下次光临！");
	}

</script>
<script language="javascript">
function set(selectPerson){	//自动添加聊天对象
    var to=$("#flag").val();
    
	if(selectPerson!=to){
			form1.to.value=selectPerson;
	}else{
		alert("请重新选择聊天对象！");
	}
}
</script>

<script language="javascript">
		function changeColor(obj,color){
			obj.bgColor = color ;
		}
		function goView(thisurl){
			window.open(thisurl,"好友资料","width=535,height=340,scrollbars=yes,resizable=yes");
		}
	
	
	</script>
</head>
<body>
<table width="778" height="150" border="0" align="center" cellpadding="0" cellspacing="0" background="images/top.jpg">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<table width="778" height="276" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td   width="170" valign="top" bgcolor="#006599" id="online" style="padding:5px">在线人员列表</td>
    <td width="398"  height="200px" valign="top" background="images/main_bj.jpg" bgcolor="#FFFFFF" style="padding:5px; ">
	<div style="height:290px; overflow:hidden" id="content">公聊内容</div>
	</td>
	<td width="310"  height="200px" valign="top" background="images/main_bj.jpg" bgcolor="#FFFFFF" style="padding:5px; ">
	<div style="height:290px; overflow:hidden" id="owncontent">私聊内容</div>
	</td>
  </tr>
</table>
<table id=table width="778" height="95" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#D6D3CE" background="images/bottom.jpg">
<form action="" name="form1" method="post" >
  <tr>
    <td height="30" align="left">&nbsp;</td>
    <td height="37" align="left"><input name="flag" id="flag" type="hidden" value="${nickname}(${user_qq})"><input name="from" type="hidden" value="${nickname}">[${nickname}]对
      <input id="to" name="to" type="text" value="" size="35" readonly="readonly">
      
表情
<select name="face" class="wenbenkuang">
  <option  value="无表情的">无表情的</option>
  <option value="微笑着" selected>微笑着</option>
  <option value="笑呵呵地">笑呵呵地</option>
  <option value="热情的">热情的</option>
  <option value="温柔的">温柔的</option>
  <option value="红着脸">红着脸</option>
  <option value="幸福的">幸福的</option>
  <option value="嘟着嘴">嘟着嘴</option>
  <option value="热泪盈眶的">热泪盈眶的</option>
  <option value="依依不舍的">依依不舍的</option>
  <option value="得意的">得意的</option>
  <option value="神秘兮兮的">神秘兮兮的</option>
  <option value="恶狠狠的">恶狠狠的</option>
  <option value="大声的">大声的</option>
  <option value="生气的">生气的</option>
  <option value="幸灾乐祸的">幸灾乐祸的</option>
  <option value="同情的">同情的</option>
  <option value="遗憾的">遗憾的</option>
  <option value="正义凛然的">正义凛然的</option>
  <option value="严肃的">严肃的</option>
  <option value="慢条斯理的">慢条斯理的</option>
  <option value="无精打采的">无精打采的</option>
</select>
说：</td>
    <td width="189" align="left"> &nbsp;&nbsp;字体颜色：
      <select name="color" size="1" class="wenbenkuang" id="select">
        <option selected>默认颜色</option>
        <option style="color:#FF0000" value="FF0000">红色热情</option>
        <option style="color:#0000FF" value="0000ff">蓝色开朗</option>
        <option style="color:#ff00ff" value="ff00ff">桃色浪漫</option>
        <option style="color:#009900" value="009900">绿色青春</option>
        <option style="color:#009999" value="009999">青色清爽</option>
        <option style="color:#990099" value="990099">紫色拘谨</option>
        <option style="color:#990000" value="990000">暗夜兴奋</option>
        <option style="color:#000099" value="000099">深蓝忧郁</option>
        <option style="color:#999900" value="999900">卡其制服</option>
        <option style="color:#ff9900" value="ff9900">镏金岁月</option>
        <option style="color:#0099ff" value="0099ff">湖波荡漾</option>
        <option style="color:#9900ff" value="9900ff">发亮蓝紫</option>
        <option style="color:#ff0099" value="ff0099">爱的暗示</option>
        <option style="color:#006600" value="006600">墨绿深沉</option>
        <option style="color:#999999" value="999999">烟雨蒙蒙</option>
      </select></td>
    <td width="19" align="left">&nbsp;</td>
  </tr>
  <tr>
    <td width="21" height="30" align="left">&nbsp;</td>
    <td width="549" align="left"><input id="content1" name="content1" type="text" size="70" onKeyDown="if(event.keyCode==13 && event.ctrlKey){send();}">
      <input name="Submit2" type="button" class="btn_grey" value="发送" onClick="send()"></td>
    <td align="right"><input name="button_exit" type="button" class="btn_grey" value="退出POPO" onClick="Exit()"></td>
    <td align="center">&nbsp;</td>
  </tr>
  <tr>
   
    <td colspan="2" align="center" class="word_dark"> &nbsp; <font color='ff9900'><strong>${nickname},</strong></font> <strong>欢迎来到POPO聊天平台</strong> </td>
    <td align="center"> </td>
  </tr>
</form>
</table>
</body>
</html>
