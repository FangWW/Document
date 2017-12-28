package com.wangjialin.service;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import com.wangjialin.domain.Person;

public class SAXForHandler extends DefaultHandler {
	private static final String TAG = "SAXForHandler"; 
	private List<Person> persons;
	private String perTag ;//通过此变量，记录前一个标签的名称。
	Person person;//记录当前Person
	
	public List<Person> getPersons() {
		return persons;
	}

	//适合在此事件中触发初始化行为。
	public void startDocument() throws SAXException {
		persons = new ArrayList<Person>();
		Log.i(TAG , "***startDocument()***");
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if("person".equals(localName)){
			for ( int i = 0; i < attributes.getLength(); i++ ) {
				Log.i(TAG ,"attributeName:" + attributes.getLocalName(i)
						+ "_attribute_Value:" + attributes.getValue(i));
				person = new Person();
				person.setId(Integer.valueOf(attributes.getValue(i)));
			}
		}
		perTag = localName;
		Log.i(TAG , qName+"***startElement()***");
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch, start, length).trim();
	    if(!"".equals(data.trim())){
	           Log.i(TAG ,"content: " + data.trim());
	    }
	    if("name".equals(perTag)){
				person.setName(data);
		}else if("age".equals(perTag)){
				person.setAge(new Short(data));
		}
	}
	
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		Log.i(TAG , qName+"***endElement()***");
		if("person".equals(localName)){
			persons.add(person);
			person = null;
		}
		perTag = null;
	}

	public void endDocument() throws SAXException {
		Log.i(TAG , "***endDocument()***");
	}
}
