package com.javapatterns.chainofresp.flowerpassing;

class JiaBaoYu extends Player
{ 

	public JiaBaoYu(Player aSuccessor)
	{ 
		this.setSuccessor(aSuccessor);
	}
	
	public void handle(int i)
	{ 
		if( i == 4 )
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
