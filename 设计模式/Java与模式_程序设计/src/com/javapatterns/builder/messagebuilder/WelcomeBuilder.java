package com.javapatterns.builder.messagebuilder;

public class WelcomeBuilder extends Builder
{

    private static final String subject = "Welcome to philharmony news group!";

    /** @link dependency */
    /*#WelcomeMessage lnkWelcomeMessage;*/

    public WelcomeBuilder()
    {
		msg = new WelcomeMessage();
    }

    public void buildSubject()
    {
		msg.setSubject( subject );
    }

    public void buildBody()
    {
        String body = "Congratulations for making the right choice!";

		msg.setBody(body);
    }

    public void sendMessage()
    {
		msg.send();
    }
}
