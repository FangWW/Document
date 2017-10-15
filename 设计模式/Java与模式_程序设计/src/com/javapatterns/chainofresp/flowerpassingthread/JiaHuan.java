package com.javapatterns.chainofresp.flowerpassingthread;

class JiaHuan extends Player
{ 

	public JiaHuan(Player aSuccessor)
	{ 
		this.setSuccessor(aSuccessor);
	}
	
	public void handle()
	{ 
		if( DrumBeater.stopped )
		{ 
			System.out.println("Jia Huan gotta drink!");
		}
        else
        {
			System.out.println("Jia Huan passed!");
			next();
		}
	} 

}
