package com.lsy.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;





public class EncodingFilter implements Filter {
	//服务器已启动就创建一个全局的对象（usermap），用于保存各用户的session
	private static Map<String , HttpSession> usermap = new HashMap<String, HttpSession>();

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
		} catch (Exception e) {
		}
		
		chain.doFilter(request, response);

	} 
	
	
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	//将用户和与之对应的session保存到usermap中
	public static void put(String key,HttpSession value)
	{
		usermap.put(key, value);
	}
	//通过用户的账号获得该用户的session
	public static HttpSession getValue(String key)
	{
		return usermap.get(key);
	}
	
	public void destroy() {

	}

}
