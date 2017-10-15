package com.javapatterns.decorator.greps;

import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.IOException;

public class GrepInputStream extends FilterInputStream
{
	String substring;
    DataInputStream in;
    
    public GrepInputStream(DataInputStream in, String substring)
    {
        super(in);
        this.in = in;
        this.substring = substring;
    }
    
    // This is the filter:  read lines from the DataInputStream,
    // but only return the lines that contain the substring.
    // When the DataInputStream returns null, we return null.
    public final String readLine() throws IOException
    {
        String line;
        do
        {
            line = in.readLine();
        }
        while ((line != null) && line.indexOf(substring) == -1);
        return line;
    }

}
