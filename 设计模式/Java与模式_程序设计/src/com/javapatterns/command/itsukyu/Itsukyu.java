package com.javapatterns.command.itsukyu;

public class Itsukyu extends Frame
{
    /**
     * @link aggregation 
     */
    private static UndoableTextArea text ;

    /**
     * @link aggregation 
     */
    static ItsukyuQuotation panel;

	public Itsukyu(String title)
	{
        super(title);
    }

    public static void main(String[] s)
    {
        // create an undoable text area
        text = new UndoableTextArea("Your text here.");

        // Create app panel
        panel = new ItsukyuQuotation(text);

        // Create a frame for app
        Itsukyu frame = new Itsukyu("Itskyu talks on Zen");

        // Add a window listener for window close events
        frame.addWindowListener(new WindowAdapter()
        {
             public void windowClosing(WindowEvent e) { System.exit(0);} 
        } );

        // Add app panel to content pane
        frame.add(panel);

        // Set initial frame size and make visible
        frame.setSize (300, 300);
        frame.setVisible(true);
    } 
} 



