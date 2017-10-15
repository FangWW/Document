package com.javapatterns.facade.security;

public class Camera
{
    public void turnOn()
    {
		System.out.println("Turning on the camera.");
    }

    public void turnOff()
    {
		System.out.println("Turning off the camera.");
    }

    public void rotate(int degrees)
    {
		System.out.println("rotating the camera by " + degrees + " degrees.");
    }
}

