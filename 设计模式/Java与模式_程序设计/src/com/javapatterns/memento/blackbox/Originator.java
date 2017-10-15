package com.javapatterns.memento.blackbox;

public class Originator
{
    public Originator()
    {
    }

    private String state;

    /** @link dependency 
     * @label wide*/
    /*#MementoIF lnkMementoIF;*/

	public MementoIF createMemento()
	{
		return new Memento( this.state );
	}
	
	public void restoreMemento( MementoIF memento)
	{
        Memento aMemento = (Memento) memento;

		this.setState( aMemento.getState() );
	}

    public String getState()
    {
        return this.state;
    }

    public void setState(String state)
    {
        this.state = state;
        System.out.println("state = " + state);
    }
	
	class Memento implements MementoIF
	{
		private String savedState;
		
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
}
