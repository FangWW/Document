package com.javapatterns.command.document;

public class Client
{
     private static Invoker pasteinvoker;
     private static Invoker openinvoker;
     private static Invoker macroinvoker;

     public static void main(String[] args)
     {

		//(1) PasteCommand
        PasteCommand PasteDoc = new PasteCommand();
        pasteinvoker = new Invoker(PasteDoc);
        System.out.println("Testing PasteCommand: \n");
        pasteinvoker.invoke();
        
		//(2) OpenCommand
        OpenCommand OpenDoc = new OpenCommand();
        openinvoker = new Invoker(OpenDoc);
        System.out.println("Testing OpenCommand: \n");
        openinvoker.invoke();

		//(3) MacroCommand
        MacroDocCommand MacCom = new MacroDocCommand();
        macroinvoker = new Invoker(MacCom);
        System.out.print("Adding 3 PasteCommand and 3 OpenCommand into ");
        System.out.println("MacroCommand!!! \n");
        MacCom.add(PasteDoc);       
        MacCom.add(PasteDoc);
        MacCom.add(PasteDoc);
        MacCom.add(OpenDoc);
        MacCom.add(OpenDoc);
        MacCom.add(OpenDoc);
        System.out.println("Testing MacroCommand: \n");
        macroinvoker.invoke();
        
        System.out.print("Removing 1 of the PasteCommand and 1 of the OpenCommand ");
        System.out.println("from MacroCommand!!! \n");
        MacCom.remove(PasteDoc);
        MacCom.remove(OpenDoc);
        System.out.println("Testing MacroCommand: \n");
        macroinvoker.invoke();
     }

}
