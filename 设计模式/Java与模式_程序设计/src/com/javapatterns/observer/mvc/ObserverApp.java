package com.javapatterns.observer.mvc;

/*
* This example is from javareference.com
* for more information visit,
* http://www.javareference.com
*/

  //import statements
  /**
  * ObserverApp.java
  */
  public class ObserverApp extends Applet implements ActionListener
  {
     //private variables
     private Button m_changeData;
     private Data m_data;
     private TextDisplay m_textDisplay;
     private LabelDisplay m_labelDisplay;
     private Random m_ran;
          
     //public variables

     /**
     * init()
     * This is the first method called by the browsers JVM, when the
     * applet is instantiated.
     * This method can be used to create the applets GUI elements
     *
     * @return void
     * @exception
     */
     public void init()
     {
        //setting the applet Layout
         setLayout(new FlowLayout(FlowLayout.CENTER));
            
         m_ran = new Random(1000);
             
         //creating data objects
         m_data = new Data(m_ran.nextInt());
         
         m_changeData = new Button("Click here to change Data Object");
         m_changeData.addActionListener(this);
         
         //creating view objects
         m_textDisplay = new TextDisplay("Observer 2, TextField View : ");
         m_labelDisplay = new LabelDisplay("Observer 1, LabelField View : ");
         
         //adding view objects as observers to the data object
         m_data.addObserver(m_textDisplay);
         m_data.addObserver(m_labelDisplay);
         
         settingData();
             
         add(m_labelDisplay);
         add(m_textDisplay);
         add(m_changeData);
     }

     private void settingData()
     {
         //create random number and
         //set the data
         m_data.setData(m_ran.nextInt());
           
           //Notify all the observers of the
         //change on Data object
         m_data.notifyObservers();
     }
     
     /**
     * actionPerformed
     * Handling Action Events
     *
     * @param ActionEvent
     * @return void
     * @exception
     */
     public void actionPerformed(ActionEvent ae)
     {
       if(ae.getSource().equals(m_changeData))
       {   
           settingData();
       }
     }
  }


