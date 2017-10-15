package com.javapatterns.decorator.grepr;

/** This class implements a subset of the UNIX tool grep and
 ** demonstrates the use of the GrepInputStream class.
 ** It prints the filename, linenumbers and lines of the file that 
 ** contains a specified substring (no regexp)
 ** @see GrepInputStream
 ** @author Ulrik Schroeder
 ** @version Joop97-Demo
 **/
public class Grep {
	static GrepReader g;		// own FilterReader definition

    private static GrepView gv = new GrepView();
	/** reads substring and files to be looked through from commandline.
	 ** Unfortunately DOS shell does not expand wildcards when calling Grep.
	 ** @param args command line arguments: 1 substring, 2 .. n Filenames
	 ** @see <{DataInputStream}>
	 ** @see GrepInputStream 
	 ** @see GrepInputStream#GrepInputStream 
	 ** @see GrepInputStream#line 
	 ** @see GrepInputStream#lineNo 
	 **/
	public static void main( String[] args  )
    {
	
        if ( args.length <= 1 )
        {
            gv.println("Usage: java Grep <substring> <filenames>");
            gv.println("       <substring> no regexp");
            gv.println("       <filenames> files to be searched in");
            System.exit( 1 );
        }
        
		String line;
        try
        {
            gv.println( "\nGrep: ���� " + args[0] + " �ļ� " + args[1] );
            gv.println( "�ļ��к�\t\t ��������ﺬ�����������ַ���\n" );
//  		gv.println( "\n--> Grep: searching " + args[ 0 ] + " in " + args[ i ] + " <----------" );
//			gv.println( "File:Line:\t Line containing substring\n" );
			g = new GrepReader( new FileReader( args[1] ), args[0] );
			for( ; ; )
               {
				line = g.readLine( );
				if (line == null) break;
				gv.println( args[1] + g.lineNo( ) + ":\t" + line );
			} // loop through lines of file
			g.close( );
        } // try
		catch ( IOException e ) { 
			gv.println( e.getMessage() );
		} // catch IOException 
    }

}

