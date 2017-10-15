package com.javapatterns.prototype.panda;

public class Client
{
    private static PandaToClone thisPanda , thatPanda;

    public static void main(String[] args)
    {
       thisPanda = new PandaToClone(15, 25);
       thisPanda.setAge(3);

       // Create the second object by cloning the first
       thatPanda = (PandaToClone) thisPanda.clone();

      // Now describe these objects on the system console :

      System.out.println(" Age of this panda : " + thisPanda.getAge());
      System.out.println("            height : " + thisPanda.getHeight());
      System.out.println("            weight : " + thisPanda.getWeight());
      System.out.println(" Age of that panda : " + thatPanda.getAge());
      System.out.println("            height : " + thatPanda.getHeight());
      System.out.println("            weight : " + thatPanda.getWeight());
   }

}
