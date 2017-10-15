package com.javapatterns.state.taoist;

import java.util.Date;

public class LockedState implements WallStateIF 
{
    public LockedState()
    {
        this.setTimeOfLock(new Date());
    }

    public void spell(WallEntry wall, String spell) throws WallEntryException
    {
        if (spell.equals(this.rightSpell))
        {
    		wall.setState(new UnlockedState());
        }
        else
        {
            throw new WallEntryException("Wrong spell!!!");
        }
    }

    public void pass(WallEntry wall)  throws WallEntryException
    {
    	throw new WallEntryException("Bang!!!");
    }

    public Date getTimeOfLock()
    {
        return timeOfLock;
    }

    public void setTimeOfLock(Date timeOfLock)
    {
        this.timeOfLock = timeOfLock;
    }

    private Date timeOfLock;
    protected static String rightSpell = "!@#$%^&*()";
}
