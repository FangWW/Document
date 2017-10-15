
package com.javapatterns.state;

public interface State {
    void sampleOperation();

    /**
     * @link
     * @shapeType PatternLink
     * @pattern State
     * @supplierRole <{Context}>
     */
    /*# private Context _context; */

    /**
     * @link
     * @shapeType PatternLink
     * @pattern State
     * @supplierRole Concrete states 
     */
    /*# private ConcreteState _concreteState; */
}
