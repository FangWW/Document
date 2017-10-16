import java.awt.*;
import java.applet.*;
/**
 * <p>Title: 带阴影的文字</p>
 * <p>Description: 使用Applet和Graphics，实现一个文字的移动广告。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: ShadowText.java</p>
 * @author 杜江
 * @version 1.0
 */
public class ShadowText extends Applet implements Runnable
{
  private Image img;
  private Image offI;
  private Graphics offG;
  private Thread thread = null;
  
  
  private int height,width;
  private String text;
  private int FontSize;
  private Font font;
  private int textcolor, backcolor, shadowcolor;
/**
 *<br>方法说明：Applet初始化，浏览器加载Applet是调用。
 *<br>输入参数：
 *<br>返回类型：
 */  
  public void init()
  {    
    width = this.size().width;
    height = this.size().height;
    //获取显示信息
    String s = new String(getParameter("Text"));
    
    text = new String("Hello");
    if(s != null)
      text = s;
    //获取字体大小
    FontSize = 30;
    s = new String(getParameter("FontSize"));
    if(s != null)
      FontSize = Integer.parseInt(s);
    //获得字体颜色
    s = getParameter("Fore");
    textcolor = (s==null) ? 0x000000 : Integer.parseInt(s, 16);
    //获取背景颜色
    s = getParameter("Back");
    backcolor = (s==null) ? 0x000000 : Integer.parseInt(s, 16);
    //获取阴影颜色
    s = getParameter("shadow");
    shadowcolor = (s==null) ? 0x000000 : Integer.parseInt(s, 16);
    //设置背景颜色
    this.setBackground(new Color(backcolor));
    //使用Graphics创建一张图片
    img = createImage(width+300,height);
    Graphics temp = img.getGraphics();
    temp.setColor(new Color(backcolor));
    temp.fillRect(0,0,width,height);
    temp.setColor(new Color(shadowcolor));
    font = new Font("TimesRoman",Font.BOLD,FontSize);
    temp.setFont(font);
    temp.drawString(text,10,height*3/4);
    
    temp.setColor(new Color(textcolor));
    temp.drawString(text,10-3,height*3/4 - 3);
    
    //构造可控制的图片对象          
    offI = createImage(width,height);
    offG = offI.getGraphics();
    
  }
/**
 *<br>方法说明：重载start()方法，启动线程
 *<br>输入参数：
 *<br>返回类型：
 */  
  public void start()
  {
    if(thread == null)
    {
      thread = new Thread(this);
      thread.start();
    }
  }
/**
 *<br>方法说明：线程体。绘制屏幕
 *<br>输入参数：
 *<br>返回类型：
 */  
  public void run()
  {
    int x=width;
    while(thread != null)
    {
      try
      {
        offG.drawImage(img,x,0,this);
        repaint();
        thread.sleep(20);
      }
      catch(InterruptedException e){}

      x-=3;
      if(x < -img.getWidth(this))
      {
        x = width;
      }

    }
  }
/**
 *<br>方法说明：Appletg更新画面调用的方法
 *<br>输入参数：Graphics g 绘图对象
 *<br>返回类型：
 */  
  public void update(Graphics g)
  {
    paint(g);
  }
/**
 *<br>方法说明：Applet绘制屏幕的方法
 *<br>输入参数：
 *<br>返回类型：
 */  
  public void paint(Graphics g)
  {
    g.drawImage(offI,0,0,this);
  }


}