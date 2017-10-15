package com.javapatterns.state.taoist;

public class WallEntryException extends Exception
{
    public WallEntryException(String message)
    {
        super(message);
        System.out.println(message);
    }
}
