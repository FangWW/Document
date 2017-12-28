package com.wangjialin.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.wangjialin.domain.Person;

public class DOMPersonService {
	public static List<Person> getPersons(InputStream inStream) throws Exception{
		List<Person> persons = new ArrayList<Person>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(inStream);
		Element root = document.getDocumentElement();
		NodeList personNodes = root.getElementsByTagName("person");
		for(int i=0; i < personNodes.getLength() ; i++){
			Element personElement = (Element)personNodes.item(i);
			int id = new Integer(personElement.getAttribute("id"));
			Person person = new Person();
			person.setId(id);
			NodeList childNodes = personElement.getChildNodes();
			for(int y=0; y < childNodes.getLength() ; y++){
				if(childNodes.item(y).getNodeType()==Node.ELEMENT_NODE){
					if("name".equals(childNodes.item(y).getNodeName())){
						String name = childNodes.item(y).getFirstChild().getNodeValue();
						person.setName(name);
					}else if("age".equals(childNodes.item(y).getNodeName())){
						String age = childNodes.item(y).getFirstChild().getNodeValue();
						person.setAge(new Short(age));
					}
				}
			}
			persons.add(person);
		}
		inStream.close();
		return persons;
	}
}
