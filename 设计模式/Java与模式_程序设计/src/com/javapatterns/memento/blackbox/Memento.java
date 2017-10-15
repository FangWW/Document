package com.javapatterns.memento.blackbox;

class Memento implements MementoIF
{
	private String savedState;

    /**
     * @directed
     * @label Is-a-member-of*/
    private Originator lnkOriginator;
	
	private Memento(String someState)
	{
		savedState = someState;
	}
	
	private void setState(String someState)
	{
		savedState = someState;
	}

	private String getState()
	{
		return savedState;
    }
}


