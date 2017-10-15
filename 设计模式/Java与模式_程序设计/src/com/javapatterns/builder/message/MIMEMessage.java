package com.javapatterns.builder.message;

/**
 * This is a class used to represent raw MIME e-mail messages.  If this
 * program is expanded to receive messages in formats other than MIME,
 * then this will probably become an abstract class with a subclass for
 * each type of e-mail format that can be received.
 */
public class MIMEMessage {
    private byte[] rawMessage;

    /**
     * Constructor
     * @param msg A byte array containing a raw unprocessed e-mail
     * message. 
     */
    public MIMEMessage(byte[] msg) {
        rawMessage = msg;
    } // constructor(byte[])

    /**
     * Return the raw bytes of an unprocessed e-mail message.
     */
    byte[] getMessage() {
        return rawMessage;
    } // getMessage()
} // class MIMEMessage
