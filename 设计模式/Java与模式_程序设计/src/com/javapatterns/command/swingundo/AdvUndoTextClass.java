package com.javapatterns.command.swingundo;

/**
 * Class Name:  advUndoTextClass
 * Class Desc:  purpose of this class is to use the new Java text undo manager.
 *
 * Startdate:   07/19/1999
 *
 * @author:     Shah Mumin
 *
 * @version:    1.0         07/19/1999
 */

public class AdvUndoTextClass extends JFrame
                              implements ActionListener,
                                         UndoableEditListener {

  private JPanel          mainSwingpanel    = new JPanel(),
                          textPanel         = new JPanel();
  private JButton         btn               = new JButton("Start"),
                          btn1              = new JButton("Undo"),
                          exit              = new JButton("Exit");
  private Dimension       myDimension       = null;
  private JTextArea       txta              = new JTextArea();
  private JScrollPane     sp                = new JScrollPane();
  private BorderLayout    borderLayout1     = new BorderLayout();
  private UndoManager     undomanager;


  //public constructor
  public AdvUndoTextClass() {

    super("    Components testing   ");
    addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
    });

    try  {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }//end of constructor

   //private components initialization
   private void jbInit() throws Exception {

      this.setBackground(Color.pink);
      this.setSize(new Dimension(500, 450));

      myDimension = new Dimension(490, 440);
      mainSwingpanel.setMaximumSize(myDimension);
      mainSwingpanel.setPreferredSize(myDimension);
      mainSwingpanel.setMinimumSize(myDimension);
      mainSwingpanel.setBackground(Color.gray);

      this.getContentPane().add(mainSwingpanel, BorderLayout.CENTER);

      myDimension = new Dimension(450, 300);
      textPanel.setMaximumSize(myDimension);
      textPanel.setPreferredSize(myDimension);
      textPanel.setMinimumSize(myDimension);
      textPanel.setBackground(Color.gray);
      textPanel.setLayout(borderLayout1);

      //attach an undo manager with the textarea
      txta.getDocument().addUndoableEditListener(this);

      textPanel.add(sp, BorderLayout.CENTER);
      sp.getViewport().add(txta, null);
      sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

      mainSwingpanel.add(textPanel);

      btn.addActionListener(this);
      btn1.addActionListener(this);
      exit.addActionListener(this);
      mainSwingpanel.add(btn);
      mainSwingpanel.add(btn1);
      mainSwingpanel.add(exit);
   }

   //required public main method
   public static void main(String[] args) {

      JFrame frame = new AdvUndoTextClass();
      frame.pack();
      frame.setVisible(true);
      frame.setSize(500, 450);
      frame.setBackground(Color.red);
      frame.setTitle("Text Undo Testing");

   } //end of main

   //implementation of interface
   public void actionPerformed(ActionEvent evt) {

      String arg = evt.getActionCommand();
      if(arg.equals("Exit"))
        System.exit(0);
      else if(arg.equals("Start")) {
        //initialize the UndoManager
        System.out.println("Start Undo manager");
        undomanager = new UndoManager();
        undomanager.setLimit(1000);
        txta.requestFocus();
      }
      else if(arg.equals("Undo")) {
        System.out.println("Undo all changes");
        if (undomanager != null) {
          undomanager.end();
          undomanager.undo();
          undomanager = null;
        }
        else {
          Toolkit.getDefaultToolkit().beep();
        }
        txta.requestFocus();
      }

   }//end of actionPerformed()

   public void undoableEditHappened(UndoableEditEvent e) {
      if (undomanager != null) {
        undomanager.addEdit(e.getEdit());
        System.out.println(e.getEdit());
      }
   }


}//end of class  advUndoTextClass
