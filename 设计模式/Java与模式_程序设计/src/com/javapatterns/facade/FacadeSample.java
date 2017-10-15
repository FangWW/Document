package com.javapatterns.facade;

public class FacadeSample
{ 
	static public void main(String args[])
	{ 
	
		Facade facade = new Facade();
		
		facade.ProcessTitle();
		facade.ProcessSubTitle();
		facade.ProcessItem();
		facade.ProcessItem();
		facade.ProcessItem(); 
	
	} 

}

