///////////////////////////////////////////////////////////
// DeJaved by mDeJava v1.0. Copyright 1999 MoleSoftware. //
//       To download last version of this software:      //
//            http://molesoftware.hypermatr.net          //
//               e-mail:molesoftware@mail.ru             //
///////////////////////////////////////////////////////////

package org.apache.struts.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletContext;

public class ServletContextWriter extends PrintWriter
{

    protected StringBuffer buffer = null;
    protected ServletContext context = null;
    protected boolean error = false;

    public ServletContextWriter(ServletContext context)
    {
        super(new StringWriter());
        buffer = new StringBuffer();
        this.context = null;
        error = false;
        this.context = context;
    }

    public boolean checkError()
    {
        flush();
        return error;
    }

    public void close()
    {
        flush();
    }

    public void flush()
    {
        if(buffer.length() > 0)
        {
            context.log(buffer.toString());
            buffer.setLength(0);
        }
    }

    public void print(boolean b)
    {
        write(String.valueOf(b));
    }

    public void print(char c)
    {
        write(c);
    }

    public void print(char c[])
    {
        for(int i = 0; i < c.length; i++)
            write(c[i]);

    }

    public void print(double d)
    {
        write(String.valueOf(d));
    }

    public void print(float f)
    {
        write(String.valueOf(f));
    }

    public void print(int i)
    {
        write(String.valueOf(i));
    }

    public void print(long l)
    {
        write(String.valueOf(l));
    }

    public void print(Object o)
    {
        write(o.toString());
    }

    public void print(String s)
    {
        int len = s.length();
        for(int i = 0; i < len; i++)
            write(s.charAt(i));

    }

    public void println()
    {
        flush();
    }

    public void println(boolean b)
    {
        println(String.valueOf(b));
    }

    public void println(char c)
    {
        write(c);
        println();
    }

    public void println(char c[])
    {
        for(int i = 0; i < c.length; i++)
            print(c[i]);

        println();
    }

    public void println(double d)
    {
        println(String.valueOf(d));
    }

    public void println(float f)
    {
        println(String.valueOf(f));
    }

    public void println(int i)
    {
        println(String.valueOf(i));
    }

    public void println(long l)
    {
        println(String.valueOf(l));
    }

    public void println(Object o)
    {
        println(o.toString());
    }

    public void println(String s)
    {
        int len = s.length();
        for(int i = 0; i < len; i++)
            print(s.charAt(i));

        println();
    }

    public void setError()
    {
        error = true;
    }

    public void write(char c)
    {
        if(c == '\n')
            flush();
        else
        if(c != '\r')
            buffer.append(c);
    }

    public void write(int c)
    {
        write((char)c);
    }

    public void write(char buf[])
    {
        for(int i = 0; i < buf.length; i++)
            write(buf[i]);

    }

    public void write(char buf[], int off, int len)
    {
        for(int i = off; i < len; i++)
            write(buf[i]);

    }

    public void write(String s)
    {
        int len = s.length();
        for(int i = 0; i < len; i++)
            write(s.charAt(i));

    }

    public void write(String s, int off, int len)
    {
        for(int i = off; i < len; i++)
            write(s.charAt(i));

    }
}
