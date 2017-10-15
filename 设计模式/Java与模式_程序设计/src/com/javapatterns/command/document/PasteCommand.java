package com.javapatterns.command.document;

public class PasteCommand implements Command 
{

/*     public PasteCommand(Document d) 
     { 
          _document = d;
     } 
*/
     public void execute() 
     { 
          System.out.println("Selected document has been pasted. \n");
     } 
}

