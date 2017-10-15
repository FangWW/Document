package com.javapatterns.state.taoist;

public interface WallStateIF
{
    void spell(WallEntry wall, String spell) throws WallEntryException;

    void pass(WallEntry wall) throws WallEntryException;
}
