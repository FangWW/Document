package com.javapatterns.chainofresp.flowerpassingthread;

class JiaMu extends Player
{ 
	public JiaMu(Player aSuccessor)
	{ 
		this.setSuccessor(aSuccessor);
	}
	
	public void handle()
	{ 
		if( DrumBeater.stopped )
		{ 
			System.out.println("Jia Mu gotta drink!");
		}
        else
        {
			System.out.println("Jia Mu passed!");
			next();
		}
	}

} 


