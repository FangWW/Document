package com.javapatterns.chainofresp.flowerpassingthread;

class JiaBaoYu extends Player
{ 

	public JiaBaoYu(Player aSuccessor)
	{ 
		this.setSuccessor(aSuccessor);
	}
	
	public void handle()
	{ 
		if( DrumBeater.stopped )
		{
			System.out.println("Jia Bao Yu gotta drink!");
		}
        else
        {
			System.out.println("Jia Bao Yu passed!");
			next();
		}
	} 

}
