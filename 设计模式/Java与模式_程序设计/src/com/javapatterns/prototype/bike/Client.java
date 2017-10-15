package com.javapatterns.prototype.bike;

public class Client
{
    CloneableBike first;
    CloneableBike second;

   public static void main(String[] args)
   {
      // Create two CloneableBike class object references

      CloneableBike first, second;

      // Create the first bike object from scratch

      first = new CloneableBike(4,26);
      first.setAge(3);

      // Create the second bike object by cloning the first

      second = (CloneableBike) first.clone();

      // Now describe these bikes on the system console :

      System.out.println(" First bike age : "+first.getAge());
      System.out.println("          gears : "+first.getGears());
      System.out.println("     wheel size : "+first.getWheelSize());
      System.out.println("Second bike age : "+second.getAge());
      System.out.println("          gears : "+second.getGears());
      System.out.println("     wheel size : "+second.getWheelSize());
   }
}
