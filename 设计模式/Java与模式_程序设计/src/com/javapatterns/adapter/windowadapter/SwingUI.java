package com.javapatterns.adapter.windowadapter;

import java.awt.BorderLayout;
import java.awt.Color;

class SwingUI extends JFrame implements ActionListener
{

   JLabel text, clicked;
   JButton button, clickButton;
   JPanel panel;
   private boolean m_clickMeMode = true;

   /**
    * @label Uses
    * @directed 
    */
   private WindowAdapter lnkWindowAdapter;

   SwingUI()
   {
     text = new JLabel("�Һܸ��ˣ�");
     button = new JButton("����");
     button.addActionListener(this);

     panel = new JPanel();
     panel.setLayout(new BorderLayout());
     panel.setBackground(Color.white);
     getContentPane().add(panel);
     panel.add(BorderLayout.CENTER, text);
     panel.add(BorderLayout.SOUTH, button);
   }

   public void actionPerformed(ActionEvent event)
   {
        Object source = event.getSource();
        if (m_clickMeMode)
        {
          text.setText("�Һܷ���");
          button.setText("������");
          m_clickMeMode = false;
        }
        else
        {
          text.setText("�Һܸ��ˣ�");
          button.setText("����");
          m_clickMeMode = true;
        }
   }

   public static void main(String[] args)
   {
     SwingUI frame = new SwingUI();
     frame.setTitle("��");
     WindowListener listener = new WindowAdapter()
     {
       public void windowClosing(WindowEvent e)
       {
         System.exit(0);
       }
     };

     frame.addWindowListener(listener);
     frame.pack();
     frame.setVisible(true);
  }
}
