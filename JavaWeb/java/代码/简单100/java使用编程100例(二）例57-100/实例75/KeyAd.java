 import java.applet.Applet; 
 import java.awt.*; 
 import java.awt.event.*; 
/**
 * <p>Title: 键盘检测</p>
 * <p>Description: 在applet使用键盘事件，检测键盘的动作。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: KeyAd.java</p>
 * @author 杜江
 * @version 1.0
 */
 public class KeyAd extends Applet{ 
   String text = "";
   int width,height;
/**
 *<br>方法说明：Applet初始化，添加了一个键盘监听对象。
 *<br>输入参数：
 *<br>返回类型：
 */
   public void init() {
      addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        this_keyPressed(e);
      }
    }); 
   } //end init 
/**
 *<br>方法说明：获取键盘事件
 *<br>输入参数：
 *<br>返回类型：
 */
   void this_keyPressed(KeyEvent e) {
    String s = "";
    //强制转换成字符型
    text = s+=e.getKeyChar();
    System.out.println("text="+text);
    width = size().width;
    height = size().height;
    repaint();
   }
/**
 *<br>方法说明：绘制Applet屏幕
 *<br>输入参数：
 *<br>返回类型：
 */
   public void paint(Graphics g) {
   	  setFont(new Font("Dialog", 1, 80)); 
      g.drawString(text,height/2,width/2);
   } //end paint 
 } // end KeyAd 
