 import java.applet.Applet; 
 import java.awt.*; 
 import java.awt.event.*; 
/**
 * <p>Title: 鼠标检测</p>
 * <p>Description: 在applet使用鼠标事件，检测鼠标的动作。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: MouseAd.java</p>
 * @author 杜江
 * @version 1.0
 */
 public class MouseAd extends Applet{ 
   int x=0,y=0;
   int width,height;
/**
 *<br>方法说明：Applet初始化，添加了一个鼠标监听对象。
 *<br>输入参数：
 *<br>返回类型：
 */
   public void init() {
      addMouseListener(new Mouse()); 
   } //end init 
/**
 *<br>类说明：继承MouseAdapter类，实现鼠标点击监听。
 *<br>功能描述：获取鼠标在Applet上点击的位置
 */
   class Mouse extends MouseAdapter {
      public void mousePressed(MouseEvent e) {
         x=e.getX();
         y=e.getY(); 
         width = size().width;
         height = size().height;
         repaint(); 
      } //end mousePressed 
   } //end Mouse 
/**
 *<br>方法说明：绘制Applet屏幕，根据鼠标位置绘制坐标
 *<br>输入参数：
 *<br>返回类型：
 */
   public void paint(Graphics g) { 
      g.drawString("x="+x+" y="+y,10,20);
      g.drawLine(x,0,x,height);
      g.drawLine(0,y,width,y);
   } //end paint 
 } // end MouseAd 
