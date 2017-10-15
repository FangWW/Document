package com.javapatterns.singleton.mxrecord1;

public class Client
{
    /**
     * @directed 
     */
    private static MXList mxl;

    public static void main(String[] args)
        throws Exception
    {
		mxl = MXList.getInstance(
            "dns://dns01390.ny.ssmb.com",
            "citicorp.com");

		for(int i=0; i < mxl.size(); i++)
		{
			System.out.println(
                (1 + i)
                + ") priority = "
				+ ((MailServer)
				mxl.elementAt(i)).getPriority()
				+ ", Name = "
				+ ((MailServer)
				mxl.elementAt(i)).getServer()
                );
		}


		mxl = MXList.getInstance(
            "dns://dns01390.ny.ssmb.com",
            "citicorp.com");

        for (int i= 0 ; i < 10000; i ++)
        {}

		for(int i=0; i < mxl.size(); i++)
		{
			System.out.println(
                (1 + i)
                + ") priority = "
				+ ((MailServer)
				mxl.elementAt(i)).getPriority()
				+ ", Name = "
				+ ((MailServer)
				mxl.elementAt(i)).getServer()
                );
		}

    }

}
