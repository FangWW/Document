package com.javapatterns.command.undoconcept;

public class ConcreteCommand implements Command
{
    public ConcreteCommand(Receiver receiver)
    {
        this.receiver = receiver;
    }

    public void execute()
    {
        receiver.action();
    }

    public void reexecute(){ }

    public void unexecute(){ }

    /**
     * @directed
     * @clientRole receiver
     */
    private Receiver receiver;
}
