package com.wangjialin.internet.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HttpServletRequestEncodingWrapper extends
		HttpServletRequestWrapper {

	private HttpServletRequest request;
	public HttpServletRequestEncodingWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	public String getParameter(String name) {
		String value = request.getParameter(name);
		try {
			if(value!=null){
				return new String(value.getBytes("ISO8859-1"), "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
		}
		return super.getParameter(name);
	}

}
