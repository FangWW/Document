package com.javapatterns.command.document;

import java.util.Vector;

public class MacroDocCommand implements MacroCommand 
{
     private Vector commandList = new Vector();

     public void add(Command toAdd)
     {          
          commandList.addElement(toAdd);
     }

     public void remove(int index)
     {
		commandList.remove(index);
     }

     public void remove(Command toRemove)
     {
          commandList.removeElement(toRemove);
     }

     public void execute() 
     { 
          Command nextCommand;
          for (int i=0; i < commandList.size(); i++)
          {
          	nextCommand = (Command) commandList.elementAt(i);
          	nextCommand.execute();
          }
     } 
}
