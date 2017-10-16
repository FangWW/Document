import javax.swing.*;
import java.awt.*;

/**
 * <p>Title: 创建自己的窗体</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: mainFrame.java</p>
 * @author 杜江
 * @version 1.0
 */
public class mainFrame extends JFrame
{
/**
 *<br>方法说明：构造器，通过传递参数来完成窗体的绘制。
 *<br>输入参数：String sTitle 窗体标题
 *<br>输入参数：int iWidth 窗体的宽度
 *<br>输入参数：int iHeight 窗体的高度 
 *<br>返回类型：
 */
  public mainFrame(String sTitle,int iWidth,int iHeight)
  {
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸
    ImageIcon ii = new ImageIcon("images/middle.gif");
    setTitle(sTitle);//设置窗体标题
    setIconImage(ii.getImage());//设置窗体的图标
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置但关闭窗体时退出程序
    setSize(iWidth,iHeight);//设置窗体大小
    int w = getSize().width;//获取窗体宽度
    int h = getSize().height;//获取窗体高度
    System.out.println("窗体宽："+w+" 窗体高："+h);
    int x = (dim.width-w)/2;
    int y = (dim.height-h)/2;
    setLocation(x,y);//将窗体移到屏幕中间
    setVisible(true);//显示窗体
  }
  public static void main(String[] args)
  {
    JFrame.setDefaultLookAndFeelDecorated(true);//使用最新的SWING外观
    mainFrame mF = new mainFrame("main Frame Demo",400,300);
  }
}