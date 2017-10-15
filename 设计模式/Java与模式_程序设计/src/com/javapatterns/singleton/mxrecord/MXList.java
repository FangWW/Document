package com.javapatterns.singleton.mxrecord;

import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class MXList
{
    /**
     * @label creates 
     */
    private static MXList mxl = null;
    private Vector list = null;

    private static final String FACTORY_ENTRY =
        "java.naming.factory.initial";

	private static final String FACTORY_CLASS =
        "com.sun.jndi.dns.DnsContextFactory";

	private static final String PROVIDER_ENTRY =
        "java.naming.provider.url";

    private static final String MX_TYPE = "MX";

    private String dnsUrl = null;
    private String domainName = null;

    /**
     * @link aggregationByValue
     * @directed
     * @clientCardinality 1
     * @supplierCardinality * 
     */
    private MailServer lnkMailServer;

    private MXList() {}

    private MXList(String dnsUrl,
	    String domainName) throws Exception
    {
        this.dnsUrl = dnsUrl;
        this.domainName = domainName;
	
		this.list = getMXRecords(dnsUrl, domainName);
    }

    public static synchronized MXList getInstance(
        String dnsUrl,
	    String domainName) throws Exception
    {
        if (mxl == null)
        {
			mxl = new MXList(dnsUrl, domainName);
        }

        return mxl;
    }

    public MailServer elementAt(int index)
        throws Exception
    {
        return (MailServer) list.elementAt(index);
    }

    public int size()
    {
        return list.size();
    }

    private Vector getMXRecords(
        String providerUrl,
	    String domainName) throws Exception
    {
        Hashtable env = new Hashtable();
        
        env.put(FACTORY_ENTRY, FACTORY_CLASS);
        env.put(PROVIDER_ENTRY, providerUrl);
        
        DirContext dirContext = new InitialDirContext(env);
        
        Vector records = new Vector(10);
        
        Attributes attrs = dirContext.getAttributes(
	        domainName, 
	        new String[] {MX_TYPE});
        
        for(NamingEnumeration ne = attrs.getAll();
	        ne != null && ne.hasMoreElements();)
        {
            Attribute attr = (Attribute)ne.next();
            NamingEnumeration e = attr.getAll();
            while(e.hasMoreElements()) 
            {
                String element = e.nextElement().toString();

                StringTokenizer tokenizer = 
                	new StringTokenizer(element, " ");
                
                MailServer mailServer = new MailServer();
                
                String token1 = tokenizer.nextToken();
                String token2 = tokenizer.nextToken();
                
                if(token1 != null && token2 != null) 
                {
                    mailServer.setPriority(
	                    Integer.valueOf(token1).intValue());
                    mailServer.setServer(token2);
                    records.addElement(mailServer);
                }
            }
        }

        System.out.println("List created.");
        return records;
    }
}
