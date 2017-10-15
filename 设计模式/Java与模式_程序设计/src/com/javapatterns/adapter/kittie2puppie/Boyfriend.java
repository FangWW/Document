package com.javapatterns.adapter.kittie2puppie;

public class Boyfriend extends Kittie implements Puppie
{

    public void wao()
    {
        this.miao();
    }

    public void fetchBall()
    {
        this.catchRat();
    }

    public void run()
    {
        super.run();
    }

    public void sleep()
    {
        super.sleep();
    }

    public String getName()
    {
        return super.getName();
    }

    public void setName(String name)
    {
    	super.setName(name);
    }

    /**
     * @link
     * @shapeType PatternLink
     * @pattern Adapter
     * @supplierRole Adaptee
     * @hidden 
     */
    /*# private Kittie _kittie; */

    /**
     * @link
     * @shapeType PatternLink
     * @pattern Adapter
     * @supplierRole Target
     * @hidden 
     */
    /*# private Puppie _doggie; */
}
