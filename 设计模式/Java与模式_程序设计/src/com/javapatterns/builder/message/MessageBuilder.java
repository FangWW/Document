package com.javapatterns.builder.message;

import java.awt.Image;

/**
 * This is an abstract builder class for building e-mail messages
 */
abstract class MessageBuilder {
    /**
     * Return an object of the subclass appropriate for the e-mail
     * message format implied by the given destination address.
     * @param dest The e-mail address the message is to be sent to
     */
    static MessageBuilder getInstance(String dest) {
        MessageBuilder builder = null;
        //...
        return builder;
    } // getInstance(String)

    /**
     * pass the value of the "to" header field to this method.
     */
    abstract void to(String value);

    /**
     * pass the value of the "from" header field to this method.
     */
    abstract void from(String value);
    //...
    /**
     * pass the value of the "organization" header field to this method.
     */
    void organization(String value) { }

    /**
     * pass the content of a plain text body part to this method.
     */
    abstract void plainText(String content);

    /**
     * pass the content of a jpeg image body part to this method.
     */
    abstract void jpegImage(Image content) ;

    /**
     * complete and return the outbound e-mail message.
     */
    abstract OutboundMessageIF getOutboundMsg() ;
} // class MessageBuilder

