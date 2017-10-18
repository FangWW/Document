   
$(function(){ 

	
	//数据传送
	  $("#submit").click(function(){    
          var qq = $("input[name='qq']").val();    //获取账号    
          var password = $("input[name='password']").val();        //获取密码    
 
          var jsonUser = {qq:qq, password:password};    //JSON对象    
              
          //注意:jsonUser.toString()这种方法错误,javaScript中的toString是用于布尔型变量的,而应用以下方法    
          var strUser = JSON.stringify(jsonUser);    //将JSON对象转变成JSON格式的字符串,    
          //var strUser = jsonUser.toJSONString();  
              
          $.post("/webchatroom/login.do", {json: strUser}, callback,'json');    
      });    
 
      function callback(json){
      
          	//alert( json.msg + " \n密码："  + json.password + "\n姓名：" + json.name); 
          	jAlert(json.msg , ' ');
          	
          	
          	
          
      }    
		
})
