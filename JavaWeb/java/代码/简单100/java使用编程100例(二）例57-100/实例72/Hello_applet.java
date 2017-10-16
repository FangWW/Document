import java.awt.*;
import java.applet.Applet;
/**
 * <p>Title: 简单的Applet</p>
 * <p>Description: 继续Applet类，实现Applet方法。在屏幕说输出一句问候语。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: Hello_applet.java</p>
 * @author 杜江
 * @version 1.0
 */
public class Hello_applet extends Applet{
 String s;
/**
 *<br>方法说明：初始化
 *<br>输入参数：
 *<br>返回类型：
 */
 public void init()
 {
  s = "Hello! welcome into Applet world!";
 }
/**
 *<br>方法说明：输出文字
 *<br>输入参数：
 *<br>返回类型：
 */
 public void paint(Graphics g)
 {
  setFont(new Font(s,Font.BOLD,16)); 
  g.drawString(s,(getSize().width/2)/2,getSize().height/2);
 }
}
