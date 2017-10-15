package com.javapatterns.builder.rtfreader;

public class RTFReader
{
	public static final char EOF='0'; //Delimitor for End of File
	public final char CHAR='c';
	public final char PARA='p';
	private char t;
	private TextConverter builder;

    /** @link dependency */
    /*#Document lnkDocument;*/

	public RTFReader(TextConverter obj)
    {
		builder=obj;
	}
	
    public void parseRTF(Document doc)
    {
		while ((t=doc.getNextToken())!= EOF)
        {
			switch (t)
            {
				case CHAR: builder.convertCharacter(t);
				case PARA: builder.convertParagraph();
			}
		}
	}
}