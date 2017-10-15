package com.javapatterns.observersax;

public class MyHandler implements ContentHandler
{

    private Writer out;
    
    public MyHandler(Writer out) 
    {
        this.out = out;     
    }
        
    public void characters(char[] text,
        int start, int length) throws SAXException
    {
         
        try 
        {
            out.write(text, start, length); 
        }
        catch (IOException e) 
        {
            throw new SAXException(e);     
        }
        
    }    
        
    // do-nothing methods
    public void setDocumentLocator(Locator locator) {}
    
    public void startDocument() {}
    
    public void endDocument() {}
    
    public void startPrefixMapping(String prefix,
        String uri) {}

    public void endPrefixMapping(String prefix) {}

    public void startElement(String namespaceURI,
        String localName,
        String qualifiedName, Attributes atts) {}
       
    public void endElement(String namespaceURI,
        String localName,
        String qualifiedName) {}
       
    public void ignorableWhitespace(char[] text,
        int start, 
        int length) throws SAXException {}
       
    public void processingInstruction(String target,
        String data){}
    
    public void skippedEntity(String name) {}

} 

