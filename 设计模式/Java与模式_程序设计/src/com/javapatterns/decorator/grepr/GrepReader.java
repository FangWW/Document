package com.javapatterns.decorator.grepr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;

public class GrepReader extends FilterReader
{
    protected String substring;     // substring to be matched
    protected BufferedReader in; // InputReader

    public GrepReader( FileReader in, String substring )
    {
        super( in );
        this.in = new BufferedReader( in );
        this.substring = substring;
        lineNumber = 0;
    }

    public final String readLine( ) throws IOException
    {
        String line;
        // step forward until line matching substring
        do
        {
            line = in.readLine( ); lineNumber++;
        } while ( (line != null) && line.indexOf( substring ) == -1 );
        return line;
    }

	private int lineNumber;
	
	public int lineNo( ) { return lineNumber; }
   
} // class GrepReader

