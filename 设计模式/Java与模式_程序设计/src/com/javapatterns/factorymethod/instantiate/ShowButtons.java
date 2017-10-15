package com.javapatterns.factorymethod.instantiate;

/**
 *
 * The purpose of this class is to display a set of buttons
 * which were previously serialized in order to demostrate the
 * use of the "instantiate" method provided by the Beans class.
 * In order to execute this program, use the following command:
 *
 *    java ShowButtons [file basename #1] [file basename #2]...
 *
 * For example, If you had a green, 16-point Start button; a
 * yellow, 20-point Yield button; and a red, 30-point Stop
 * button; then the following command would apply:
 *
 *    java ShowButton Start16G Yield20Y Stop30R
 *
 * The file names of the serialized buttons must end in 
 * ".ser".  However, the extension should not be specified
 * as part of the command arguments.  There is not limit on
 * the number of buttons to display.
 *
 */
class ShowButtons extends Frame {


   /**
    *
    * This method constructs a frame window, changes its layout to 
    * FlowLayout and adds all of the serialized buttons specified on  
    * the command line.
    *
    * @param      serButton      Array of file names for the
    *                            serialized buttons.
    *
    */
   ShowButtons(String serButton) {

      /*
       * Invoke the super class constructor, add an event listener
       * for the "close" event and change the layout to "Flow".
       */
      super("Show Button");
      addWindowListener(new Wadapt());
      setLayout(new FlowLayout());

      /*
       * Instantiate the serialized button.  If the process
       * does not work for a particular button, then display an error
       * and instantiate a regular button.
       */
      Button b;

         try {
            b = (Button)Beans.instantiate(null, serButton);
         }
         catch (Exception e) {
           System.out.println(e);
           b = new Button();
         }

         add(b);

   }


   /**
    *
    * This method creates a frame to display the list of
    * serialized buttons.  The button components are packed into
    * the frame.
    * 
    * @param      args     Array of base file names for the
    *                      serialized buttons.  The ".ser"
    *                      extension should be omitted.
    */
   public static void main(String args[]) {

      Frame frame = new ShowButtons(args[0]);
      frame.pack();
      frame.setVisible(true);

   }


   /**
    *
    * This "inner" class listens for the event which indicates that
    * the window is closing.  
    *
    */
   class Wadapt extends WindowAdapter {
    
      /**
       *
       * This method handles the event which indicates that a window
       * is closing. If encountered, then it hides the frame, frees
       * the resources and exits.
       *
       * @param   evt         Event object
       */
      public void windowClosing(WindowEvent evt) {

         Frame frame = (Frame)evt.getSource();
         frame.setVisible(false);
         frame.dispose();
         System.exit(0);

      }
   }

}
