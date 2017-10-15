package com.javapatterns.command.lightandfan;

public class TestCommand
{
	public static void main(String[] args)
	{
	        Light  testLight = new Light( );
	        LightOnCommand testLOC = new LightOnCommand(testLight);
	        LightOffCommand testLFC = new LightOffCommand(testLight);
	        Switch testSwitch = new Switch( testLOC,testLFC);       
	        testSwitch.flipUp( );
	        testSwitch.flipDown( );
	        Fan testFan = new Fan( );
	        FanOnCommand foc = new FanOnCommand(testFan);
	        FanOffCommand ffc = new FanOffCommand(testFan);
	        Switch ts = new Switch( foc,ffc);
	        ts.flipUp( );
	        ts.flipDown( ); 
	}
}               

