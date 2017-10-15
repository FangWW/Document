package com.javapatterns.prototype.monkeyking;

import java.util.Date;

public class Monkey implements Cloneable
{
    public Monkey()
    {
    	this.birthDate = new Date();
        this.staff = new GoldRingedStaff();
    }

    public Object clone()
    {
        Monkey temp = null;
        try
        {
            temp = (Monkey) super.clone();
        }
        catch(CloneNotSupportedException e)
        {
            System.out.println("Clone failed");
        }
        finally
        {
            return temp;
        }
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getWeight()
    {
        return weight;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    public GoldRingedStaff getStaff()
    {
        return staff;
    }

    private int height;
    private int weight;

    /**
     * @directed
     * @clientCardinality 1
     * @supplierCardinality 1 
     */
    private GoldRingedStaff staff;
    private Date birthDate;
}
