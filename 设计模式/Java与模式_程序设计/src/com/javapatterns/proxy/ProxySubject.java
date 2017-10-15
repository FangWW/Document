package com.javapatterns.proxy;

public class ProxySubject extends Subject 
{
    private RealSubject realSubject;
	
	public ProxySubject()
	{
	}
	
	public void request()
	{
        preRequest();

		if( realSubject == null )
        {
			realSubject = new RealSubject();
		}

        realSubject.request();

        postRequest();
	}

    private void preRequest()
    {
        //something you want to do before requesting
    }

    private void postRequest()
    {
        //something you want to do after requesting
    }
}
