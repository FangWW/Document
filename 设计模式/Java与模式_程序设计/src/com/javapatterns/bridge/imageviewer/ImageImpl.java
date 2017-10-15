package com.javapatterns.bridge.imageviewer;

abstract public class ImageImpl
{
    public abstract void init();

    public abstract void paint();

    public long width;
    public long height;
    public byte[] data;
}
