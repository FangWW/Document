import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
/**
 * <p>Title: Applet使用jar包</p>
 * <p>Description: 将类文件和图片等资源文件打包，一次下载到本地。
                   本实例演示Applet使用这些资源</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: ImageButtonApplet.java</p>
 * @author 杜江
 * @version 1.0
 */
public class ImageButtonApplet extends JApplet
{
private String path = "/img/ittoolbox.gif";
private ImageIcon logoButtonIcon = new ImageIcon(getClass().getResource(path));
/**
 *<br>方法说明：初始化Applet，添加带图片的按钮
 *<br>输入参数：
 *<br>返回类型：
 */
public void init()
{
try
{
if (logoButtonIcon == null)
throw new Exception("cannot get the image!");

JButton iButton = new JButton(logoButtonIcon);

Container cp = this.getContentPane();
cp.add(iButton);
}
catch (Exception e)
{
e.printStackTrace();
}
}
}
