package com.wangjialin.junit;

import com.wangjialin.service.PersonService;

import android.app.Activity;
import android.os.Bundle;

public class HelloJunitActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
		Integer integer = 10 + 6;
		total(integer);
		//声明并实例化PersonService
		PersonService personService = new PersonService();
		//调用save方法
		personService.save();

    	
    }
    
    private String total(Integer integer) {
		Integer integer1 = integer;
		Integer integer2 = 2011;
		Integer total = integer1 + integer2;
		String preString = "goushiAndroid";
		String result = preString + total ;
		
		return result;
	}
}