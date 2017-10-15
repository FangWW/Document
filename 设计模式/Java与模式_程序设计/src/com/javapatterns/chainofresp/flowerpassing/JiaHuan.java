package com.javapatterns.chainofresp.flowerpassing;

class JiaHuan extends Player
{ 

	public JiaHuan(Player aSuccessor)
	{ 
		this.setSuccessor(aSuccessor);
	}
	
	public void handle(int i)
	{ 
		if( i == 5 )
		{ 
			System.out.println("Jia Bao Yu gotta drink!");
		}
        else
        {
			System.out.println("Jia Bao Yu passed!");
			next(i);
		}
	} 

}
