package com.javapatterns.command.document;

public class OpenCommand implements Command 
{
//this would be used as a receiver, if we had one
//     private Application _application;

//this would have been the constructor
/*     public OpenCommand(Application a) 
     { 
          _application = a; 
     } 
*/
     public void execute() 
     { 
/*          String docName = AskUser();
          			
          if (docname != null)
          {
                 Document docOpen = new Document (docName);
                 _application.add(docOpen);
                 _application.open();
          }
*/

	  System.out.print("System requested for document...");
	  System.out.println("user enters name of document");
          
          System.out.println("System has opened document. \n");
     } 
}

