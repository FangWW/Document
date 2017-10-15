package com.javapatterns.proxy.imageloader;

import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JFrame;

public class Client extends JFrame
{
   private static int IMAGE_WIDTH = 270;
   private static int IMAGE_HEIGHT = 380;

   private Icon imageIconProxy = null;

   static public void main(String[] args)
   {
      Client app = new Client();
      app.show();
   }

   public Client()
   {
      super("Virtual Proxy Client");
      imageIconProxy = new ImageIconProxy("c:/hongyan.jpg", IMAGE_WIDTH, IMAGE_HEIGHT);

      setBounds(100, 100, IMAGE_WIDTH + 10, IMAGE_HEIGHT + 30);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public void paint(Graphics g)
   {
      super.paint(g);
      Insets insets = getInsets();
      imageIconProxy.paintIcon(this, g, insets.left , insets.top);
   }
}
