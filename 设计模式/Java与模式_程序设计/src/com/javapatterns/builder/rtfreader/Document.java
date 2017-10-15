package com.javapatterns.builder.rtfreader;

public class Document
{
	private static int value;
	private char token;
    private static int counter = 0 ;

    public Document()
    {
    	counter = 0 ;
    }

	public char getNextToken()
    {
		//Get the next token

        if ( counter++  != 10)
			return token;
        else
            return RTFReader.EOF;
	}
}
