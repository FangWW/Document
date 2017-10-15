package com.javapatterns.builder.messagebuilder;

import javax.mail.*;
import javax.mail.internet.*;

abstract public class AutoMessage
{
    protected String subject = "";
    protected String body = "";
    protected String from = "";
    protected String to = "";
    protected Date sendDate = null;

    public AutoMessage()
    {
    }

    public void send()
    {
        //Your SMTP server address here.
		String smtpHost="umbriel.email.jeffcorp.com";
        //The sender's email address
		String from="jeff.yan@jeffcorp.com";
        //The recepients email address
		String to ="hong.yan@jeffcorp.com";

		Properties props = new Properties();
        //The protocol to use is SMTP
		props.put("mail.smtp.host", smtpHost);

		Session session = Session.getDefaultInstance(props, null);

		try
        {
			InternetAddress[] address = {new InternetAddress(to)};
			MimeMessage message;

			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));

			message.setRecipients(Message.RecipientType.TO, to);
			message.setSubject("Hello from Jeff");
			message.setSentDate(sendDate);
			message.setText("Hello Jeff, \nHow are things going?");

			Transport.send(message);

            System.out.println("email has been sent.");
		}
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public String getTo()
    {
        return to;
    }

    public void setTo(String to)
    {
        this.to = to;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public Date getSendDate()
    {
	    return sendDate;
	}

    public void setSendDate(Date sendDate)
    {
        this.sendDate = sendDate;
    }
}
