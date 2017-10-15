package com.javapatterns.state;

public class Context {
    public void sampleOperation(){
        state.sampleOperation();
    }

    public void setState(State state){
            this.state = state;
        }

    /**
     * @link aggregation 
     */
    private State state;
}
