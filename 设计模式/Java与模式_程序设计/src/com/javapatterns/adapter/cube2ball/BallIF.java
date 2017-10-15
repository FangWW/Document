package com.javapatterns.adapter.cube2ball;

public interface BallIF {
    /**
     * Class Adaptee contains operation sampleOperation1.
     */
    double calculateArea();

    /**
     * Class Adaptee doesn't contain operation sampleOperation2.
     */
    double calculateVolume();

    double getRadius();

    void setRadius(double radius);
}
