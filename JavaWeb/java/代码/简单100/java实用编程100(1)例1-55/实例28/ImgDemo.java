import javax.swing.*;
import java.awt.*;
/**
 * <p>Title: 图片的处理，</p>
 * <p>Description: 将图片放大和翻转显示</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: ImgDemo.java</p>
 * @author 杜江
 * @version 1.0
 */
class ImgDemo extends JFrame {
Image image;
/**
 *<br>方法说明：构造器，显示窗体
 *<br>输入参数：
 *<br>返回类型：
 */
ImgDemo(String filename) {
setTitle("drawImage Example");
try {
  image = getToolkit().getImage(filename);
  setIconImage(image);
} catch (Exception e) {
  e.printStackTrace();
}

setSize(600, 250);
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
show();
}
/**
 *<br>方法说明：绘制图像
 *<br>输入参数：
 *<br>返回类型：
 */
public void paint(Graphics g) {
  Insets insets = getInsets();
  int x = insets.left, y = insets.top;
  //获取图片尺寸
  int w = image.getWidth(this);
  int h = image.getHeight(this);
  //正常显示图片
  g.drawImage(image, x, y, this); 
  //缩小图形
  g.drawRect(x, y, w/4+1, h/4+1);//画一个框
  g.drawImage(image, x+1, y+1, w/4, h/4, this);
  //水平翻转
  g.drawImage(image, x+w, y, x+2*w, y+h, w, 0, 0, h, this);
}
/**
 *<br>方法说明：主方法，接受参数
 *<br>输入参数：
 *<br>返回类型：
 */
public static void main(String[] args) {
 if (args.length == 1) {
  new ImgDemo(args[0]);
 } else {
  System.err.println("usage: java ImgDemo images-name ");
 }
}
}
