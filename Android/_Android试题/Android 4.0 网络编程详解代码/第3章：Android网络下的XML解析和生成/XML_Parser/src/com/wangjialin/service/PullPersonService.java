package com.wangjialin.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import com.wangjialin.domain.Person;

public class PullPersonService {
	public static void save(List<Person> persons, OutputStream outStream) throws Exception{
		XmlSerializer serializer = Xml.newSerializer();
		serializer.setOutput(outStream, "UTF-8");
		serializer.startDocument("UTF-8", true);
		serializer.startTag(null, "persons");
		for(Person person : persons){
			serializer.startTag(null, "person");
			serializer.attribute(null, "id", person.getId().toString());
			serializer.startTag(null, "name");
			serializer.text(person.getName());
			serializer.endTag(null, "name");
			
			serializer.startTag(null, "age");
			serializer.text(person.getAge().toString());
			serializer.endTag(null, "age");
			
			serializer.endTag(null, "person");
		}		
		serializer.endTag(null, "persons");
		serializer.endDocument();
		outStream.flush();
		outStream.close();
	}

	public static List<Person> getPersons(InputStream inStream) throws Exception{
		Person person = null;
		List<Person> persons = null;
		XmlPullParser pullParser = Xml.newPullParser();
		pullParser.setInput(inStream, "UTF-8");
		int event = pullParser.getEventType();//触发第一个事件
		while(event!=XmlPullParser.END_DOCUMENT){
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				persons = new ArrayList<Person>();
				break;
			case XmlPullParser.START_TAG:
				if("person".equals(pullParser.getName())){
					int id = new Integer(pullParser.getAttributeValue(0));
					person = new Person();
					person.setId(id);
				}
				if(person!=null){
					if("name".equals(pullParser.getName())){
						person.setName(pullParser.nextText());
					}
					if("age".equals(pullParser.getName())){
						person.setAge(new Short(pullParser.nextText()));
					}
				}
				break;
				
			case XmlPullParser.END_TAG:
				if("person".equals(pullParser.getName())){
					persons.add(person);
					person = null;
				}
				break;
			}
			event = pullParser.next();
		}
		return persons;
	}
}
