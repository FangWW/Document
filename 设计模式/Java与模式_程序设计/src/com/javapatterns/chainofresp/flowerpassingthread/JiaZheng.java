package com.javapatterns.chainofresp.flowerpassingthread;


class JiaZheng extends Player
{ 

	public JiaZheng(Player aSuccessor)
	{ 
		this.setSuccessor(aSuccessor);
	}
	
	public void handle()
	{ 
		if( DrumBeater.stopped )
		{
			System.out.println("Jia Zheng gotta drink!");
		}
        else
        {
			System.out.println("Jia Zheng passed!");
			next();
		}
	}

}