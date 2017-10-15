package com.javapatterns.state.drawingtool;

public class DrawingController {
    public void mousePressed(){
        tool.handleMousePress();
    }

    public void setTool(Tool tool){
            this.tool = tool;
        }

    public void processKeyboard() {
    }

    public void initialize() {
    }

    /**
     * @link aggregation 
     * @clientRole Current Tool
     */
    private Tool tool;
}
