package com.javapatterns.decorator.simplified2;

public class ConcreteDecorator implements Component {
public ConcreteDecorator(Component component){
        super();
        this.component = component;
    }

    public void sampleOperation(){
        component.sampleOperation();
    }

    /**
     * @link aggregation 
     */
    private Component component;
}
