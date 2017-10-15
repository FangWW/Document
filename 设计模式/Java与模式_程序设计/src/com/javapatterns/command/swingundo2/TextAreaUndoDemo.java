package com.javapatterns.command.swingundo2;

/* TextAreaUndoDemo.java
 *
 * Illustrates the basic concepts of supporting undo and redo
 * by adding this support to an extension of the TextArea class.
 */

/* App class
 */
public class TextAreaUndoDemo { 
    // Main entry point
    public static void main(String s[]) { 
        // Create app panel
        TextAreaUndoDemoPanel panel = new TextAreaUndoDemoPanel();

        // Create a frame for app
        JFrame frame = new JFrame("TextAreaUndoDemo");

        // Add a window listener for window close events
        frame.addWindowListener(new WindowAdapter() { 
             public void windowClosing(WindowEvent e) { System.exit(0);} 
        } );

        // Add app panel to content pane
        frame.getContentPane().add(panel);

        // Set initial frame size and make visible
        frame.setSize (300, 200);
        frame.setVisible(true);
    } 
} 



