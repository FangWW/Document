package com.javapatterns.builder.rtfreader;

public class ASCIIConverter extends TextConverter
{
    private ASCIIText asciiTextObj;
    //resulting product
    //converts a character to target representation and
    //appends to the resulting

    public void convertCharacter(char c)
    {
        char asciiChar = new Character(c).charValue();
        //gets the ascii charcter
        asciiTextObj.append(asciiChar);
    }

	public void convertParagraph(){}
	
	public ASCIIText getResult()
    {
		return asciiTextObj;
	}
}


