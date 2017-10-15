
package com.javapatterns.command.drawapplet;

public class Drawing extends Canvas
   implements MouseListener, MouseMotionListener
{
   private SimpleDraw    applet;
   private Vector        lines;
   private Point         startPosition;
   private Point         mousePosition;
   private boolean       mousePressed;

   /**
    * @link aggregation
    * @directed 
    */
   private Line lnkLine;

   public Drawing( SimpleDraw owner )
   {
      applet = owner;
      lines = new Vector();
      mousePressed = false;
      addMouseListener( this );
      addMouseMotionListener( this );
   }

   public void add( Line line )
   {
      lines.addElement( line );
      repaint();
   }

   public void remove( Line line )
   {
      lines.removeElement( line );
      repaint();
   }

   public void paint( Graphics graphics )
   {
      Enumeration enumeration = lines.elements();
      Line currentLine;

      while( enumeration.hasMoreElements() )
      {
           currentLine = (Line)(enumeration.nextElement());
           currentLine.paint( graphics );
      }
      if( mousePressed )
      {
           graphics.drawLine( startPosition.x, startPosition.y,
           mousePosition.x, mousePosition.y );
      }
   }

   public void mouseClicked( MouseEvent event ) {}
   public void mouseEntered( MouseEvent event ) {}
   public void mouseExited( MouseEvent event ) {}

   public void mousePressed( MouseEvent event )
   {
      mousePressed = true;
      startPosition = event.getPoint();
   }

   public void mouseReleased( MouseEvent event )
   {
      if( !event.getPoint().equals( startPosition ))
      {
          Line line = new Line( startPosition, event.getPoint() );
          AddLineCommand command = new AddLineCommand( this, line );
          applet.execute( command );
      }
      mousePressed = false;
   }

   public void mouseDragged( MouseEvent event )
   {
          mousePosition = event.getPoint();
          repaint();
   }
   public void mouseMoved( MouseEvent event ) {}
}


