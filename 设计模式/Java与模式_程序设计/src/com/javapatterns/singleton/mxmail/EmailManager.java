package com.javapatterns.singleton.mxmail;

import com.javapatterns.singleton.mxrecord.*;

import javax.mail.*;
import javax.mail.internet.*;

/**
 * This class provides an interface on the top of the Java Mail.
 * It provides the methods for email message and attachments.
 * It takes more than one addresses for the to field, cc field,
 * and bcc field.
 * <p>
 * Sending a email takes the following steps:
 * <BR>(1) Create an instance of EmailClient.
 * <BR>(2) Call init method to instantiate the variables for email message.
 * <BR>(3) Call write method one or more times to set the email body.
 * <BR>(4) Call setAttachment one or more times if you need to attach files with the email.
 * <BR>(5) Call setToAddress one or more times if you want to set the multiple recipients with type TO.
 * <BR>(6) Call setCcAddress one or more times if you want to set the multiple recipients with type CC.
 * <BR>(7) Call setBccAddress one or more times if you want to set the multiple recipients with type BCC.
 * <BR>(8) Call send to send the email.
 *
 */

public class EmailManager
{
    MimeMessage m_msg;
    Session m_session;
    Properties m_props;
    MimeBodyPart m_mimeBody;
    Multipart m_multiParts;

    String m_host = "";
    String m_user = "jeff.yan@jeffcorp.com";
    String m_subject;
    String m_from;
    Vector m_to;
    Vector m_cc;
    Vector m_bcc;
    String m_emailContent;

	private MXList mxl = null;

    /**
     * This constructor should be called if more than one recipients with
     * recipient type TO, or CC, or BCC will be entered.
     */
    public EmailManager(String subject, String from)throws Exception
    {
	    m_subject = subject;
	    m_from = from;
	    m_to = null;
	    m_cc = null;
	    m_bcc = null;
    }

    /**
     * This constructor should be called if more than one recipients with
     * recipient type TO, or CC, or BCC will be entered.
     */
    public EmailManager(String subject, String from, String host, String user)throws Exception
    {
        m_user = user;
        m_host = host;
	    m_subject = subject;
	    m_from = from;
	    m_to = null;
	    m_cc = null;
	    m_bcc = null;
    }
    /**
     * This constructor should be called if one or less than one recipient with
     * recipient type TO, or CC, or BCC will be entered.
     */
    public EmailManager(String subject, String from, String to,
		            String cc, String bcc)throws Exception
    {
	    m_subject = subject;
	    m_from = from;
	    addToAddress(to);
	    addCcAddress(cc);
	    addBccAddress(bcc);
    }
    /**
     * This constructor should be called if one or less than one recipient with
     * recipient type TO, or CC, or BCC will be entered.
     */
    public EmailManager(String subject, String from, String to,
		            String cc, String bcc, String user,
		            String host)throws Exception
    {
        m_user = user;
        m_host = host;
	    m_subject = subject;
	    m_from = from;
	    addToAddress(to);
	    addCcAddress(cc);
	    addBccAddress(bcc);
    }

    public void addBccAddress(String bccAddr)
    {
	    if ((bccAddr == null) || (bccAddr.length() <= 0))
        {
	       return;
	    }

	    if (m_bcc == null)
        {
	        m_bcc = new Vector();
	    }

	    m_bcc.addElement(bccAddr);
    }

    public void addBccAddress(Vector bccAddr)
    {
	    if (bccAddr == null)
        {
	        return;
	    }

	    if (m_bcc == null)
        {
	        m_bcc = bccAddr;
	    }
        else
        {
	        Enumeration bccList = bccAddr.elements();
	        while (bccList.hasMoreElements())
            {
	            m_bcc.addElement(bccList.nextElement());
	        }
	    }
    }

    public void addCcAddress(String ccAddr)
    {
	    if ((ccAddr == null) || (ccAddr.length() <= 0))
        {
	       return;
	    }

	    if (m_cc == null)
        {
	       m_cc = new Vector();
	    }

	    m_cc.addElement(ccAddr);
    }

    public void addCcAddress(Vector ccAddr)
    {
	    if (ccAddr == null)
        {
	        return;
	    }

	    if (m_cc == null)
        {
	        m_cc = ccAddr;
	    }
        else
        {
	        Enumeration ccList = ccAddr.elements();
	        while (ccList.hasMoreElements())
            {
	            m_cc.addElement(ccList.nextElement());
	        }
	    }
    }

    public void addToAddress(String toAddr)
    {
	    if ((toAddr == null) || (toAddr.length() <= 0))
        {
	        return;
	    }

	    if (m_to == null)
        {
	        m_to = new Vector();
	    }

	    m_to.addElement(toAddr);
    }

    public void addToAddress(Vector toAddr)
    {
	    if (toAddr == null)
        {
	        return;
	    }

	    if (m_to == null)
        {
	        m_to = toAddr;
	    }
        else
        {
	        Enumeration toList = toAddr.elements();
	        while (toList.hasMoreElements())
            {
	            m_to.addElement(toList.nextElement());
	        }
	    }
    }

    public void init() throws Exception
    {
		mxl = MXList.getInstance("dns://dns90.jeffcorp.com", "jeffcorp.com");
        mxl.dump();

	    /**
	    * Create some properties and get the default Session.
	    */

	    m_props = System.getProperties();
	    m_props.put("mail.smtp.host", m_host);

	    /**
	    * If the user is not specified, local user will be used.
	    */
	    if ((m_user != null) && (m_user.length() >0))
        {
	        m_props.put("mail.smtp.user", m_user);
	    }

	    m_session = Session.getDefaultInstance(m_props, null);
	    m_session.setDebug(false);
	    m_msg = new MimeMessage(m_session);

	    m_multiParts = new MimeMultipart();

	    m_emailContent = "";

	    setSubject();
	    setBody();
    }

    public void send() throws Exception
    {

	    try
	    {
		    setFromAddress();
		    setToAddress();
	 	   	setCcAddress();
	  	  	setBccAddress();

		    m_mimeBody.setText(m_emailContent);
		    m_multiParts.addBodyPart(m_mimeBody);

	    	// add the Multipart to the message
	    	m_msg.setContent(m_multiParts);

	    	// set the Date: header
	    	m_msg.setSentDate(new Date());

	    	boolean shouldContinue = true;

			// try to send emails
	    	for (int i = 0 ; i < mxl.size() && shouldContinue; i++)
	    	{
		    	try
		    	{
			    	m_host = ((MailServer) mxl.elementAt(i)).getServer();

			    	System.out.println("TRYING SMTP SERVER " + m_host + " FOR ROUND " + i);

			    	if (m_host == null) m_host="";

			    	if (m_host.equals(""))
			    	{
						shouldContinue = true;
			    	}
			    	else
			    	{
				    	m_props.put("mail.smtp.host", m_host);
					    // send the message

				 	    Transport.send(m_msg);
						shouldContinue = false;
			    	}
		    	}
		    	catch(SendFailedException sfe)
		    	{
			    	shouldContinue = true;
		    	}
	    	}

	 	   init();
	    }
	    catch(Exception e)
	    {
		    System.out.println("Error in send()" + e);
            e.printStackTrace();
	    }
    }

    public void setAttachment(String filename)  throws Exception
    {
	    MimeBodyPart mimeAttach = new MimeBodyPart();

	    // attach the file to the message
	    FileDataSource fds=new FileDataSource(filename);
	    mimeAttach.setDataHandler(new DataHandler(fds));
	    mimeAttach.setFileName(filename);
	    m_multiParts.addBodyPart(mimeAttach);
    }
    protected void setBccAddress() throws Exception
    {
	    if (m_bcc == null){
	        return;
	    }

	    int addressCount = m_bcc.size();
	    if (addressCount <= 0){
	        return;
	    }

	    InternetAddress[] addresses = new InternetAddress[addressCount];
	    int addressIndex = 0;

	    while (addressIndex < addressCount){
	        String address = (String)m_bcc.elementAt(addressIndex);
	        addresses[addressIndex] = new InternetAddress(address);
	        addressIndex++;
	    }

	    m_msg.addRecipients(Message.RecipientType.BCC, addresses);
    }
    protected void setBody()
    {
	    // create and fill the message part for main body.
	    m_mimeBody = new MimeBodyPart();
	    m_emailContent = "";

    }
    protected void setCcAddress() throws Exception
    {
	    if (m_cc == null){
	        return;
	    }

	    int addressCount = m_cc.size();
	    if (addressCount <= 0){
	        return;
	    }

	    InternetAddress[] addresses = new InternetAddress[addressCount];
	    int addressIndex = 0;

	    while (addressIndex < addressCount){
	        String address = (String)m_cc.elementAt(addressIndex);
	        addresses[addressIndex] = new InternetAddress(address);
	        addressIndex++;
	    }

	    m_msg.addRecipients(Message.RecipientType.CC, addresses);
    }
    protected void setFromAddress() throws Exception
    {
	    if (m_from.length() <= 0){
	        return;
	    }

	    m_msg.setFrom(new InternetAddress(m_from));
    }
    protected void setSubject() throws Exception
    {
	    if ((m_subject == null) || (m_subject.length() <= 0)){
	        return;
	    }

	    m_msg.setSubject(m_subject);
    }
    protected void setToAddress() throws Exception
    {
	    if (m_to == null){
	        throw new Exception("Recipient TO is not set.");
	    }

	    int addressCount = m_to.size();
	    if (addressCount <= 0){
	        throw new Exception("Recipient TO is not set.");
	    }

	    InternetAddress[] addresses = new InternetAddress[addressCount];
	    int addressIndex = 0;

	    while (addressIndex < addressCount){
	        String address = (String)m_to.elementAt(addressIndex);
	        addresses[addressIndex] = new InternetAddress(address);
	        addressIndex++;
	    }

	    m_msg.addRecipients(Message.RecipientType.TO, addresses);
    }
    public void writeMessage(String msg)
    {
	    m_emailContent += msg;
    }

	public static void main(String[] args)
	{
		try{
			String from = "jeff.yan@jeffcorp.com";
			String to = "jeff.yan@jeffcorp.com";
			String subject = "test SendEmail from TCC";
			String body = "Hello, this is a test";

			EmailManager emailSvc = new EmailManager(subject, from);
			emailSvc.init();
			emailSvc.writeMessage(body);

			//Add to address.
			emailSvc.addToAddress(to);

			emailSvc.send();
		}
        catch (Exception e)
        {
			System.out.println("error:" + e);
            e.printStackTrace();
		}
	}

}

