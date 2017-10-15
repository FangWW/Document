package com.javapatterns.builder.sendemail;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

class SimpleMail
{
    public static void main(String[] args) throws Exception
    {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "umbriel.citicorp.com");
        props.setProperty("mail.user", "emailuser");
        props.setProperty("mail.password", "");

        Session mailSession = Session.getDefaultInstance(props, null);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
        message.setContent("This is a test", "text/plain");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("hong.yan@citi.com"));

        transport.connect();
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
        }
   }

