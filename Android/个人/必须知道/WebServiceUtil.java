/**
 * 
 */
package org.crazyit.net;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class WebServiceUtil
{
	// ����Web Service�������ռ�
	static final String SERVICE_NS = "http://WebXml.com.cn/";
	// ����Web Service�ṩ�����URL
	static final String SERVICE_URL = 
		"http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx";

	// ����Զ��Web Service��ȡʡ���б�
	public static List<String> getProvinceList()
	{
		// ���õķ���
		String methodName = "getRegionProvince";
		// ����HttpTransportSE�������
		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		ht.debug = true;
		// ʹ��SOAP1.1Э�鴴��Envelop����
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
			SoapEnvelope.VER11);
		// ʵ����SoapObject����
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
		envelope.bodyOut = soapObject;
		// ������.Net�ṩ��Web Service���ֽϺõļ�����
		envelope.dotNet = true;
		try
		{
			// ����Web Service
			ht.call(SERVICE_NS + methodName, envelope);
			if (envelope.getResponse() != null)
			{
				// ��ȡ��������Ӧ���ص�SOAP��Ϣ
				SoapObject result = (SoapObject) envelope.bodyIn;
				SoapObject detail = (SoapObject) result.getProperty(methodName
					+ "Result");
				// ������������Ӧ��SOAP��Ϣ��
				return parseProvinceOrCity(detail);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (XmlPullParserException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	// ����ʡ�ݻ�ȡ�����б�
	public static List<String> getCityListByProvince(String province)
	{
		// ���õķ���
		String methodName = "getSupportCityString";
		// ����HttpTransportSE�������
		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		ht.debug = true;
		// ʵ����SoapObject����
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
		// ���һ���������
		soapObject.addProperty("theRegionCode", province);
		// ʹ��SOAP1.1Э�鴴��Envelop����
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
			SoapEnvelope.VER11);
		envelope.bodyOut = soapObject;
		// ������.Net�ṩ��Web Service���ֽϺõļ�����
		envelope.dotNet = true;
		try
		{
			// ����Web Service
			ht.call(SERVICE_NS + methodName, envelope);
			if (envelope.getResponse() != null)
			{
				// ��ȡ��������Ӧ���ص�SOAP��Ϣ
				SoapObject result = (SoapObject) envelope.bodyIn;
				SoapObject detail = (SoapObject) result.getProperty(methodName
					+ "Result");
				// ������������Ӧ��SOAP��Ϣ��
				return parseProvinceOrCity(detail);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (XmlPullParserException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private static List<String> parseProvinceOrCity(SoapObject detail)
	{
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < detail.getPropertyCount(); i++)
		{
			// ������ÿ��ʡ��
			result.add(detail.getProperty(i).toString().split(",")[0]);
		}
		return result;
	}

	public static SoapObject getWeatherByCity(String cityName)
	{
		String methodName = "getWeather";
		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		ht.debug = true;
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
			SoapEnvelope.VER11);
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
		soapObject.addProperty("theCityCode", cityName);
		envelope.bodyOut = soapObject;
		// ������.Net�ṩ��Web Service���ֽϺõļ�����
		envelope.dotNet = true;
		try
		{
			ht.call(SERVICE_NS + methodName, envelope);
			SoapObject result = (SoapObject) envelope.bodyIn;
			SoapObject detail = (SoapObject) result.getProperty(methodName
				+ "Result");
			return detail;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}



// ����������ȡ������
		// sqlite��ҳ select * from tablename limit pagesize offset
		// currentpage*pagesize ;
		// SQLServer ��ҳ select top pagesize * from tablename where id not in
		// (select top current*pagesize id from table);