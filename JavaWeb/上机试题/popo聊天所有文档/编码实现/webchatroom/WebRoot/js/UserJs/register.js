$(function(){
	 $("input").hover(function(){$(this).addClass("color")},
		  function(){$(this).removeClass("color")}			 
	  )
			 
			  
	 $("textarea").hover(function(){$(this).addClass("color")},
		  function(){$(this).removeClass("color")}			 
	  )
	  

	
	Stip.config.p= 'right';// String [left|top|right|bottom] 默认弹出框的方向 
	Stip.config.closeBtn=true;
	Stip.config.time=5000;
	Stip.config.kind="error";
    var str="" ;
    
    //表单验证
	$("#nickname").focusout(function() {
		var nickname=$(this).val().length;
		alert($(this).val());
		if(nickname==0){
		  Stip('nickname').show(function(){

                str="请填写昵称";
            	return  str;
     	  });
     	}

      if( nickname >6 ){
               
                 Stip('nickname').show(function(){
                 
                 str="昵称不能超过6个字符";
            	 return  str;
     	  });
               
	  } 
			 
  });
	
	$("#sex").focusout(function() {
		var sex =$("#sex").val().length;
		if(sex==0){
		
		     Stip('sex').show(function(){  
                 str="请填写性别";
         	     return  str;
    	     }) ;
		 }
    });
	
	$("#password").focusout(function() {
		 var password=$("#password").val().length;
		 if(password==0){
		     Stip('password').show(function(){
                 str="请输入密码";
          	     return  str;
          	    });
        	}
        	

        if( password>0 && password <6 || password >12 ){
             Stip('password').show(function(){
                 str="请输入正确长度的密码（6--12位）";
                 return  str ;	 
			 }) ;
		}
	});
	
	$("#ampwd").focusout(function() {
		var ampwd =$("#ampwd").val().length;
		if(ampwd==0){
		  Stip('ampwd').show(function(){
              str="请再次输入你的密码";
        	  return  str;
          });
 	    }

      if(  $("#password").val() != ( $("#ampwd").val()) ){
         Stip('ampwd').show(function(){
            str="密码和确认密码输入不一致";
            return  str ;	 
			}); 
		}
	});
	
	$("#age").focusout(function() {
		var age=$("#age").val().length;
		if(age==0){
		   Stip('age').show(function(){
              str="请输入年龄";
      	      return  str;
      	      });
	     }
		
	});
	

	$("#birthday").focusout(function() {
		var birthday=$("#birthday").val().length;
		if(birthday==0){
		        Stip('birthday').show(function(){
                   str="请输入生日";
      	           return  str;
      	            }) ;
     	}	
	});
	
	$("#sign").focusout(function() {
		var sign=$("#sign").val().length;
		if(sign > 25){
		     Stip('sign').show(function(){
                 str="请输入正确的字符数（25个）";
    	         return  str;
    	      }) ;
	   }	
	});
	
	
	  
	 })
	