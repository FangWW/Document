package com.wangjialin.junit;

import com.wangjialin.service.PersonService;

import android.test.AndroidTestCase;

public class PersonServiceTest extends AndroidTestCase {
	
	/**
	 * 1，单元测试方法需要申明为public类型；
	 * 2，单元测试方法的返回值类型为void；
	 * 3，按照JUnit3的规范要求单元测试方法的方法命名需要以test开头；
	 * 4，单元测试方法需要申明向单元测试框架抛出异常；
	 */
	public void testSave() throws Throwable
	{
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
