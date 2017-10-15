package com.javapatterns.command.javaawt;

public class TheWorld extends Frame implements ActionListener
{
   LetThereBeLightCommand btnLight;
   LetThereBeLandCommand btnLand;
   ResetCommand btnReset;
   GodRestsCommand btnExit;
   Panel p;

   public TheWorld()
   {
      super("This is the world, and God says...");

      p = new Panel();
      p.setBackground(Color.black);
      
      add(p);
      btnLight = new LetThereBeLightCommand("Let there be light", p);
      btnLand = new LetThereBeLandCommand("Let there be land", p);
      btnReset = new ResetCommand("Reset", p);
      btnExit = new GodRestsCommand("God rests");

      p.add(btnLight);
      p.add(btnLand);
      p.add(btnReset);
      p.add(btnExit);

      btnLight.addActionListener(this);
      btnLand.addActionListener(this);
      btnReset.addActionListener(this);
      btnExit.addActionListener(this);
      setBounds(100,100,400,200);
      setVisible(true);
   }
   //-----------------------------------------
   public void actionPerformed(ActionEvent e)
   {
      CommandFromGod obj = (CommandFromGod)e.getSource();
      obj.Execute();
   }
   //-----------------------------------------
   static public void main(String[] argv)
   {
      new TheWorld();
   }
}
