<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
 <base href="<%=basePath%>">
<title>
	POPO聊天平台
</title>
   <script type="text/javascript" src="js/jquery-1.5.1.js"></script>
   <script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
   <script type="text/javascript" src="js/json2.js"></script>
   <script type="text/javascript" src="js/Stip/Stip.js"></script>
   <script type="text/javascript" src="js/Stip/jquery.js"></script> 
   <script src="alerts/jquery.alerts.js" type="text/javascript"></script> 
   <link href="alerts/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" /> 
   <link href="CSS/style.css" rel="stylesheet">
   <link href="js/Stip/Stip.css" type="text/css" rel="stylesheet" >


<style type="text/css">
	.box{border:1px solid #CCC; background:#f8f8f8; text-align:left;}
	
.lj-tipsWrap{min-width:10px;min-height:15px;font-family:'Segoe UI',Calibri, 'Myriad Pro', Myriad, 'Trebuchet MS', Helvetica, Arial, sans-serif;background:#F4FBFF;line-height:1.5em;border:1px solid #2192D3;text-align:left;-webkit-border-radius:3px;-moz-border-radius:3px;-webkit-box-shadow:2px 2px 3px #eee;-moz-box-shadow:2px 2px 3px #eee;position:absolute;z-index:1;padding:5px 15px;}
.lj-tipsWrap,.lj-in,.lj-close{display:inline-block;}
.lj-in{position:absolute;zoom:1;border:10px dashed transparent;width:0;height:0;}
.lj-in .lj-span{zoom:1;width:0;height:0;overflow:hidden;}
.lj-close{position:absolute;text-decoration:none;color:#000;font-size:14px;zoom:1;text-indent:1px;height:9px;width:9px;overflow:hidden;line-height:.5em;right:0;top:0;}.lj-close:hover{color:#39F;}.lj-top{border-top:10px solid #2192D3;bottom:-20px;left:3px;}.lj-top .lj-span{border-top:10px solid #F4FBFF;margin:-11px 0 0 -10px;}.lj-right{border-right:10px solid #2192D3;left:-20px;top:3px;}
.lj-right .lj-span{border-right:10px solid #F4FBFF;margin:-10px 0 0 -9px;}
.lj-bottom{border-bottom:10px solid #2192D3;top:-20px;left:3px;}
.lj-bottom .lj-span{border-bottom:10px solid #F4FBFF;margin:-9px 0 0 -10px;}
.lj-left{border-left:10px solid #2192D3;right:-21px;top:3px;}
.lj-left .lj-span{border-left:10px solid #F4FBFF;margin:-10px 0 0 -11px;}
.lj-left ~ .lj-close{left:0;}.lj-error{color:#f30;border:1px solid #FB8888;background:#FEF2F2;}.lj-error .lj-top{border-top-color:#FB8888;}.lj-error .lj-right{border-right-color:#FB8888;}.lj-error .lj-left{border-left-color:#FB8888;}.lj-error .lj-bottom{border-bottom-color:#FB8888;}.lj-error .lj-top .lj-span{border-top-color:#FEF2F2;}.lj-error .lj-right .lj-span{border-right-color:#FEF2F2;}.lj-error .lj-left .lj-span{border-left-color:#FEF2F2;}.lj-error .lj-bottom .lj-span{border-bottom-color:#FEF2F2;}.lj-correct{color:#000;border:1px solid #2192D3;background:#F4FBFF;}.lj-correct .lj-top{border-top-color:#2192D3;}.lj-correct .lj-right{border-right-color:#2192D3;}.lj-correct .lj-left{border-left-color:#2192D3;}.lj-correct .lj-bottom{border-bottom-color:#2192D3;}.lj-correct .lj-top .lj-span{border-top-color:#F4FBFF;}.lj-correct .lj-right .lj-span{border-right-color:#F4FBFF;}.lj-correct .lj-left .lj-span{border-left-color:#F4FBFF;}.lj-correct .lj-bottom .lj-span{border-bottom-color:#F4FBFF;}
</style>

<script language="javascript">
   
$(function(){ 
	
	
	//数据传送
	//  $("#submit").click(function(){    
       //   var qq = $("input[name='qq']").val();    //获取账号    
        //  var password = $("input[name='password']").val();        //获取密码    
 			
       //   var jsonUser = {qq:qq, password:password};    //JSON对象    
              
          //注意:jsonUser.toString()这种方法错误,javaScript中的toString是用于布尔型变量的,而应用以下方法    
       //   var strUser = JSON.stringify(jsonUser);    //将JSON对象转变成JSON格式的字符串,    
          //var strUser = jsonUser.toJSONString();  
              
        //  $.post("login.do?op=login", {json: strUser}, callback ,'json');   
             
           
    //  });    
 
   //   function callback(json){
      
     //     	alert( "json.msg"+ json.msg ); 
      //    	jAlert(json.msg , ' ');
     	
   //   }  
	
	Stip.config.p= 'bottom';// String [left|top|right|bottom] 默认弹出框的方向 
	Stip.config.closeBtn=true;
	Stip.config.time=10000;
	Stip.config.kind="error";

    var str="" ;
    
    //表单验证
	$("#password").focusout(function() {
	
	var password =$(this).val().length;
	
		if(password==0){
		  Stip('password').show(function(){
		
                str="密码不能为空";
            	return  str;
		  });
     	}

      if(password<6 && password >0 || password >12){
      
    	  Stip('password').show(function(){
    		  str="密码长度为6--12位";
              return  str ;	 
		  });
                
		} 
		}) ;
	
	
	
	$("#qq").focusout(function() {
	
		var qq =$(this).val().length;
		
		if(qq==0){
		 
		 Stip('qq').show(function(){	   
             str="帐号不能为空";
         	 return  str;
		 });
    	} 

   if(qq<6 && qq >0 || qq >12 || isNaN( $(this).val() ) ){
	    
	   Stip('qq').show(function(){
	          
			  
		   str="请输入正确的帐号";
       	   return  str;
       	   
		 });

	  }
			 
  });
}); 

</script>
<body>
<br>
<form name="form1" method="post" action="login.do?op=login" >
    <table width="752" height="479"  border="0" align="center" cellpadding="0" cellspacing="0" background="images/22.jpg">
      <tr>
        <td height="210" colspan="3" class="word_dark">&nbsp;</td>
      </tr>
      <tr>
        <td width="51" height="123" align="center" valign="top" class="word_dark">&nbsp;</td>
        <td width="247" align="center" valign="top" class="word_dark"><br>账号：<input type="text" name="qq" id ="qq" class="login">  
          &nbsp;&nbsp;  <a href="<%=basePath%>chatjsp/registerl.html">账号注册</a><br>
        <br>密码：<input type="password" name="password" id="password" class="login">   
       &nbsp;&nbsp; <a href=""> 密码找回</a>
        
       <br><br><input name="login" type="submit" class="btn_bg" id="submit" value="登陆"/> &nbsp; &nbsp;&nbsp; &nbsp; <input name="canle" type="reset" class="btn_bg" value="取消"/>
        </td>
        <td width="138" valign="top" class="word_dark"><br><br><br><br>
          <br></td>
      </tr>
      
       
</table>		
</form>
</body>
</html>
