package com.javapatterns.builder.messagebuilder;

import java.util.Date;

abstract public class Builder
{
    protected AutoMessage msg ;

    public Builder()
    {
    }

    public abstract void buildSubject();

    public abstract void buildBody();

    public void sendMessage()
    {
		msg.send();
    }

    public void buildFrom(String from)
    {
		msg.setFrom( from );
    }

    public void buildTo(String to)
    {
        System.out.println(to);
		msg.setTo( to );
    }

    public void buildSendDate()
    {
		msg.setSendDate(new Date());
    }
}
