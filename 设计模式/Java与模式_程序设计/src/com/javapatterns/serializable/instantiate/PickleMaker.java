package com.javapatterns.serializable.instantiate;

import java.awt.TextField;

/**
 *
 * The purpose of this class is to create a button, set its label,
 * font and color and then serialize it to a file.  To execute
 * this program, use the command:
 *
 *   java ButtonMaker [label] [font size] [Color]
 *
 * The parameters specify the attributes of the button and will
 * also be used to construct the name of the file as follows:
 *
 *    [label][font size][Color].ser
 *
 * The "label" may be any suitable label and will be used as is.
 * The font size will also be used as is; however, a font size of
 * zero will mean the default size.  The color should be a letter
 * code: r)ed, g)reen, b)lue, y)ellow.  Anything else will result
 * in the default color.
 *
 */
public class PickleMaker extends Frame {

   /**
    *
    * This constructs a frame window which will show the
    * button and the button itself utilizing the attributes
    * specified.  It also serializes the button to a file
    * for reuse in other programs.  The name of the file
    * is constructed from the button attributes as previously
    * described.
    *
    * @param      label       Button label
    * @param      size        Point size for the font or 0
    *                         for the default.
    * @param      color       Color code (R, G, B, Y or anything
    *                         else for the default.
    *
    */
   PickleMaker(String text, int size)
   {

      /*
       * Invoke the super class constructor, add an event listener
       * for the "close" event and change the layout to "Flow".
       */
      super("Pickle Maker");
      addWindowListener(new win());
      setLayout(new FlowLayout());

      /*
       * Make the button and serialize it.
       */
      TextField textField = makeTextField(text, size);
      serializeTextField(textField, "mytextfield.ser");
      add(textField);
   }
   

   /**
    *
    * This method constructs a button and changes its attributes
    * based on the parameters specified.  
    *
    * @param      label    Label to assign to button
    * @param      size     Size of font or 0 for default
    * @param      color    Color (red, green, blue or yellow
    *                      or anything else for the default.
    *
    * @return              Button component
    *
    */
   private TextField makeTextField(String text, int size)
   {
      TextField textField = new TextField(text, size);
      return textField;
   }

   /**
    *
    * This method writes a serialized version of the button to 
    * the file name specified.
    *
    * @param      button      Button to serialize
    * @param      filename    Name of the file to use
    *
    */
   private void serializeTextField(TextField textField, String filename)
   {
      try
      {
         FileOutputStream outStream = new FileOutputStream(filename);
         ObjectOutputStream out = new ObjectOutputStream(outStream);
         out.writeObject(textField);
         out.flush();
         out.close();
      }
      catch (Exception e)
      {
   		  System.out.println(e);
      }
   }


   /**
    *
    * This method creates the frame which will construct and show
    * the button. The frame is displayed with a size of (0, 0, 300, 200).
    *
    * @params     args        arg[0] = label text; arg[1] = font size;
    *                         arg[2] = background color
    *
    */
   public static void main(String[] args)
   {
         Frame frame = new PickleMaker("No matter where you go, &this.", 25);
         frame.setBounds(0,0,300,200);
         frame.setVisible(true);
   }

   /**
    *
    * This "inner" class listens for the event which indicates that
    * the window is closing.  
    *
    */
   class win extends WindowAdapter {
    
      /**
       *
       * This method handles the event which indicates that a window
       * is closing. If encountered, then it hides the frame, fees
       * the resources and exits.
       *
       * @param   evt         Event object
       */
      public void windowClosing(WindowEvent evt)
      {

         Frame frame = (Frame)evt.getSource();
         frame.setVisible(false);
         frame.dispose();
         System.exit(0);

      }
   }

}
