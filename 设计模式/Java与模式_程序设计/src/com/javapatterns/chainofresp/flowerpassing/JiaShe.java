package com.javapatterns.chainofresp.flowerpassing;

class JiaShe extends Player
{ 
	
	public JiaShe(Player aSuccessor)
	{ 
		this.setSuccessor(aSuccessor);
	}
	
	public void handle(int i)
	{ 
		if( i == 2 )
		{ 
			System.out.println("Jia She gotta drink!");
		}
        else
        {
			System.out.println("Jia She passed!");
			next(i);
		}
	} 

}