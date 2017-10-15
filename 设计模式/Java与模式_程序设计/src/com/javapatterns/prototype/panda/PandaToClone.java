
package com.javapatterns.prototype.panda;
public class PandaToClone implements Cloneable   
{
   private int height, weight, age;      

   public PandaToClone (int height, int weight)
   {
      this.age = 0;
      this.weight = weight;
      this.height = height;
   }

   public void setAge(int age)
   { 
       this.age = age; 
   }  
   public int getAge()       
   { 
       return age; 
   }
   public int getHeight()     
   { 
       return height; 
   }
   public int getWeight() 
   { 
       return weight; 
   }

   public Object clone()
   {
      PandaToClone temp = new PandaToClone(height, weight);
      temp.setAge(age);

      return (Object) temp;
   }
}

