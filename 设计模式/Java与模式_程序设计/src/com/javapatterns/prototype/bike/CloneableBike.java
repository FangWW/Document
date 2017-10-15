package com.javapatterns.prototype.bike;

public class CloneableBike implements Cloneable   // Class we will be cloning
{
   private int gears, wheelSize, age;      // Local variables

   public CloneableBike (int gears, int wheelSize)  // Class constructor
   {
      this.gears = gears;
      this.wheelSize = wheelSize;
      age = 0;
   }

   public void setAge(int age) { this.age = age; }  // Set the bike age
   public int getAge()       { return age; }
   public int getGears()     { return gears; }
   public int getWheelSize() { return wheelSize; }

   // Method to allow cloning of this class

   public Object clone()
   {
      // Create a new CloneableBike object ready for returning to the caller.

      CloneableBike temp = new CloneableBike(gears, wheelSize);
      temp.setAge(age);

      // Return this new CloneableBike object to the caller, as an Object
      // class object

      return (Object) temp;
   }
}

