package com.javapatterns.bridge.peer;

public class Button extends Component {
    public String getLabel(){ return label; }

    public void setLabel(String label){ this.label = label; }

    private String label;

    /**
     * @link aggregation
     * @directed
     */
    private ButtonPeer lnkButtonPeer;
}
