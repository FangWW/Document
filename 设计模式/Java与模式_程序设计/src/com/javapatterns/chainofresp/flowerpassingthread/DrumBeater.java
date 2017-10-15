package com.javapatterns.chainofresp.flowerpassingthread;

import java.util.Timer;
import java.util.TimerTask;

public class DrumBeater
{
    private static Player firstPlayer;
    public static boolean stopped = false;
    Timer timer;

	static public void main(String[] args)
	{
        DrumBeater drumBeater = new DrumBeater();

        JiaMu jiaMu = new JiaMu(null);

		jiaMu.setSuccessor( new JiaShe (
            new JiaZheng(
            new JiaBaoYu(
            new JiaHuan( jiaMu ) ) ) ) );

        drumBeater.startBeating(1);

        firstPlayer = jiaMu;
        firstPlayer.handle();

	}

	public void startBeating(int stopInSeconds)
	{
		System.out.println("Drum beating started...");

        timer = new Timer();
        timer.schedule(new StopBeatingReminder(), stopInSeconds * 1000);

	}

    class StopBeatingReminder extends TimerTask
    {
        public void run()
        {
            System.out.println("Drum beating stopped!");
            stopped = true;
            timer.cancel(); //Terminate the timer thread
        }
    }

}
