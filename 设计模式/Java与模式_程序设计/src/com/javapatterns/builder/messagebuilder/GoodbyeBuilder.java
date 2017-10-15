package com.javapatterns.builder.messagebuilder;

public class GoodbyeBuilder extends Builder
{

    private static final String subject = "Thank you for being with us!";

    /** @link dependency */
    /*#GoodbyeMessage lnkGoodbyeMessage;*/

    public GoodbyeBuilder()
    {
        msg = new GoodbyeMessage();
    }

    public void buildSubject()
    {
		msg.setSubject( subject );
    }

    public void buildBody()
    {
        String body = "Oops! You have chosen to leave.";
		msg.setBody( body );
    }

    public void sendMessage()
    {
		msg.send();
    }
}
