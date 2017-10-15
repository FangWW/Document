package com.javapatterns.builder.rtfreader;

public class Client
{
    private RTFReader rtfReader ;

    /** @link dependency */
    /*#ASCIIConverter lnkASCIIConverter;*/

	public void createASCIIText(Document doc)
    {
		TextConverter asciiBuilder = new ASCIIConverter();
		rtfReader = new RTFReader(asciiBuilder);
		rtfReader.parseRTF(doc);
		ASCIIText asciiText = asciiBuilder.getResult();
	}
	
	public static void main(String[] args)
    {
		Client client=new Client();
		Document doc=new Document();
		client.createASCIIText(doc);
		System.out.println("This is an example of Builder Pattern");
	}
}
