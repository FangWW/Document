package com.javapatterns.builder.message;

import java.awt.Image;

/**
 * This is a class to parse MIME e-mail messages.
 */
class MIMEParser {
    private MIMEMessage msg;        // The message being parsed
    private MessageBuilder builder; // The builder object
    //...

    /**
     * parse a MIME message, calling the builder methods that correspond
     * to the message's header fields and body parts.
     */
    OutboundMessageIF parse(MIMEMessage msg) {
        this.msg = msg;
        builder = MessageBuilder.getInstance(getDestination());
        
        MessagePart hdr = nextHeader();
        while (hdr != null) {
            if (hdr.getName().equalsIgnoreCase("to"))
              builder.to((String)hdr.getValue());
            else if (hdr.getName().equalsIgnoreCase("from"))
              builder.from((String)hdr.getValue());
            //...
            hdr = nextHeader();
        } // while hdr
        
        MessagePart bdy = nextBodyPart();
        while (bdy != null) {
            if (bdy.getName().equals("text/plain"))
              builder.plainText((String)bdy.getValue());
            //...
            else if (bdy.getName().equals("image/jpeg"))
              builder.jpegImage((Image)bdy.getValue());
            //...
            bdy = nextHeader();
        } // while bdy

        return builder.getOutboundMsg();
    } // parse(MIMEMessage)

    private MessagePart nextHeader() {
        MessagePart mp = null;
        //...
        return mp;
    } // nextHeader()

    private MessagePart nextBodyPart() {
        MessagePart mp = null;
        //...
        return mp;
    } // nextBodyPart()

    // return the destination e-mail address for the message
    private String getDestination() {
        String dest = null;
        //...
        return dest;
    } // getDestination()

    private class MessagePart {
        private String name;
        private Object value;

        /**
         * Consructor
         */
        MessagePart(String name, Object value) {
            this.name = name;
            this.value = value;
        } // Consructor(String, String)

        String getName() { return name; }

        Object getValue() { return value; }
    } // class MessagePart
} // class MIMEParser

