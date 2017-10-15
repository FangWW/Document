package com.javapatterns.state.taoist;

import java.util.Date;

public class UnlockedState implements WallStateIF
{
    public UnlockedState()
    {
        this.setTimeOfUnlock(new Date());
    }

    public void spell(WallEntry wall, String spell) throws WallEntryException
    {
    }

    public void pass(WallEntry wall) throws WallEntryException
    {
    	wall.setState(new LockedState());
    }

    public Date getTimeOfUnlock()
    {
        return timeOfUnlock;
    }

    public void setTimeOfUnlock(Date timeOfUnlock)
    {
        this.timeOfUnlock = timeOfUnlock;
    }

    private Date timeOfUnlock;
}
