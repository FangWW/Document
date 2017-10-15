package com.javapatterns.facade.security;

public class Client
{
    /**
     * @link aggregation 
     */
    static private Camera camera1, camera2;

    /**
     * @link aggregation 
     */
    static private Light light1, light2, light3;

    /**
     * @link aggregation 
     */
    static private Sensor sensor;

    /**
     * @link aggregation 
     */
    static private Alarm alarm;

    public static void main(String[] args)
    {
		camera1.turnOn();
		camera2.turnOn();

        light1.turnOn();
        light2.turnOn();
        light3.turnOn();

        sensor.activate();
        alarm.activate();
    }

}

