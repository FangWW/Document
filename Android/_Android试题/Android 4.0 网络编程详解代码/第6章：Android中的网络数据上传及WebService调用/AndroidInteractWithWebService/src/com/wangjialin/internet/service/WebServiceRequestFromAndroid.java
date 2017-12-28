package com.wangjialin.internet.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.wangjialin.internet.utils.StreamTool;

public class WebServiceRequestFromAndroid {
	/**
	 * 获取手机号归属地
	 * @param mobile 手机号
	 * @return
	 * @throws Exception
	 */
	public static String getAddress(String mobile) throws Exception{
		String soap = readSoap();
		soap = soap.replaceAll("\\$mobile", mobile);
		byte[] entity = soap.getBytes();
		
		String path = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx";
		HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
		conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
		conn.getOutputStream().write(entity);
		if(conn.getResponseCode() == 200){
			return parseSOAP(conn.getInputStream());
		}
		return null;
	}
	/*
	 <?xml version="1.0" encoding="utf-8"?>
	<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
	  <soap12:Body>
	    <getMobileCodeInfoResponse xmlns="http://WebXml.com.cn/">
	      <getMobileCodeInfoResult>string</getMobileCodeInfoResult>
	    </getMobileCodeInfoResponse>
	  </soap12:Body>
	</soap12:Envelope>
	 */
	private static String parseSOAP(InputStream xml)throws Exception{
		XmlPullParser pullParser = Xml.newPullParser();
		pullParser.setInput(xml, "UTF-8");
		int event = pullParser.getEventType();
		while(event != XmlPullParser.END_DOCUMENT){
			switch (event) {
			case XmlPullParser.START_TAG:
				if("getMobileCodeInfoResult".equals(pullParser.getName())){
					return pullParser.nextText();
				}
				break;
			}
			event = pullParser.next();
		}
		return null;
	}

	private static String readSoap() throws Exception{
		InputStream inStream = WebServiceRequestFromAndroid.class.getClassLoader().getResourceAsStream("AndroidInteractWithWebService.xml");
		byte[] data = StreamTool.read(inStream);
		return new String(data);
	}
}
