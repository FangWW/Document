package com.javapatterns.chainofresp.flowerpassing;

abstract class Player
{
	abstract public void handle(int i);
	
    /**
     * @link aggregation 
     */
	private Player successor;
	
	public Player()
	{ 
		successor = null;
	}
	
	protected void setSuccessor(Player aSuccessor)
	{ 
		successor = aSuccessor;
	}
	
	public void next(int index)
	{ 
		if( successor != null )
		{ 
			successor.handle(index);
		}
        else
        {
			System.out.println("Program terminated.");
		}
	} 

}

