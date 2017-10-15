package com.javapatterns.chainofresp.scheduler;

import java.io.IOException;
  
public class Scheduler
{
   private static Timer timer = new Timer();

   public static void main(String []args)
   {
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.HOUR_OF_DAY, 10);
      calendar.set(Calendar.MINUTE, 30);
      calendar.set(Calendar.SECOND, 30);
      Date time = calendar.getTime();
 
      timer.schedule(new ScheduledTask(), time);
      System.out.println("A job has been scheduled at " + time);
   }
 
   static class ScheduledTask extends TimerTask
   {
      public void run()
      {
		try
        {
	        String command = "notepad c:/boot.ini";
	        Process child = Runtime.getRuntime().exec(command);
            System.out.println("The scheduled job has been executed.");
		}
        catch (IOException e)
        {
            System.out.println("Exception " + e);
		}
      }
   }
} 


