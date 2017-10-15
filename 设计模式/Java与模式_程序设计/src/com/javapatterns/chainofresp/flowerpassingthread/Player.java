package com.javapatterns.chainofresp.flowerpassingthread;

abstract class Player
{
	abstract public void handle();
	
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
	
	public void next()
	{ 
		if( successor != null )
		{ 
			successor.handle();
		}
        else
        {
			System.out.println("Program terminated.");
		}
	} 

}

