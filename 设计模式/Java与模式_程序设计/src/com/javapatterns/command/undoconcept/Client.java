package com.javapatterns.command.undoconcept;

public class Client
{
    public static void main(String[] args)
    {
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
    	Invoker invoker = new Invoker( command );

        invoker.action();
    }

    /** @link dependency */
    /*#com.javapatterns.command.undoconcept.Receiver lnkReceiver;*/

    /** @link dependency */
    /*#com.javapatterns.command.undoconcept.Invoker lnkInvoker;*/
}
