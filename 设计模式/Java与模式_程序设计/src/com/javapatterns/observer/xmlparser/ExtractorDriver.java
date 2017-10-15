package com.javapatterns.observer.xmlparser;

import org.xml.sax.helpers.XMLReaderFactory;

public class ExtractorDriver
{

	public static void main(String[] args)
	{
      
		if (args.length <= 0)
		{
			System.out.println("Usage: java ExtractorDriver url");
			return;
		}
	        
		try
		{
			XMLReader parser = XMLReaderFactory.createXMLReader();
			
// Since this just writes onto the console, it's best
// to use the system default encoding, which is what
// we get by not specifying an explicit encoding here.
			Writer out = new OutputStreamWriter(System.out);
			ContentHandler handler = new TextExtractor(out);
			parser.setContentHandler(handler);
			
//      parser.parse("file:////c://myxml.xml");
			parser.parse(args[0]);
			
			out.flush();
		}
		catch (Exception e)
		{
	  		System.err.println(e);
	  	}
	
	} 
}