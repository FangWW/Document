package com.javapatterns.command.itsukyu;

public class UndoCommand extends Command {
    UndoableTextArea text;

    public UndoCommand(UndoableTextArea text)
    {
        
        super("Undo");
        this.text = text;
    }

    public void execute()
    {
        text.undo();
    }
}
