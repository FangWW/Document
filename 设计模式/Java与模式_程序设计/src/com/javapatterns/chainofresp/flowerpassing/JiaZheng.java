package com.javapatterns.chainofresp.flowerpassing;

class JiaZheng extends Player
{ 

	public JiaZheng(Player aSuccessor)
	{ 
		this.setSuccessor(aSuccessor);
	}
	
	public void handle(int i)
	{ 
		if( i == 3 )
		{ 
			System.out.println("Jia Zheng gotta drink!");
		}
        else
        {
			System.out.println("Jia Zheng passed!");
			next(i);
		}
	}

}