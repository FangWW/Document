package com.javapatterns.chainofresp.flowerpassing;

class JiaMu extends Player
{ 
	public JiaMu(Player aSuccessor)
	{ 
		this.setSuccessor(aSuccessor);
	}
	
	public void handle(int i)
	{ 
		if( i == 1 )
		{ 
			System.out.println("Jia Mu gotta drink!");
		}
        else
        {
			System.out.println("Jia Mu passed!");
			next(i);
		}
	}

} 


