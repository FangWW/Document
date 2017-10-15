package com.javapatterns.command.drawlines;

import java.util.Stack;

public class CommandList {
    private	Stack	executedCommands = new Stack();
    private	Stack	unexecutedCommands = new Stack();

private void _execute( Command command ) {
        command.execute();
        executedCommands.push( command );
    }

  public void execute( Command command ) {
        unexecutedCommands.removeAllElements();
        _execute( command );
    }

  public void unexecute() {
        Command command = (Command)executedCommands.pop();
        command.unexecute();
        unexecutedCommands.push( command );
    }

  public void reexecute() {
        Command command = (Command)unexecutedCommands.pop();
        _execute( command );
    }

  public void reset() {
        executedCommands.removeAllElements();
        unexecutedCommands.removeAllElements();
    }

    public boolean canUnexecuteCommand() {
        return !executedCommands.empty();
    }

    public boolean canReexecuteCommand() {
        return !unexecutedCommands.empty();
    }
}

