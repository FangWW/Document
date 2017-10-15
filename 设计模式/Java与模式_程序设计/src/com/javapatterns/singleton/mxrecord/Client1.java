package com.javapatterns.singleton.mxrecord;

public class Client1
{
    /**
     * @directed 
     */
    private static MXList1 mxl;

    public static void main(String[] args)
        throws Exception
    {
		mxl = MXList1.getInstance(
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


		mxl = MXList1.getInstance(
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
