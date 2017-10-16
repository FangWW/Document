import javax.swing.JInternalFrame;
import javax.swing.JTextArea;

import java.awt.event.*;
import java.awt.*;
/**
 * <p>Title: 内部窗体</p>
 * <p>Description: 生成一个内部窗体，提供InternalFrameDemo类使用</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: MyInternalFrame.java</p>
 * @author 杜江
 * @version 1.0
 */
public class MyInternalFrame extends JInternalFrame {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

    public MyInternalFrame() {
        super("文档 #" + (++openFrameCount), 
              true, //可变尺寸
              true, //有关闭按钮
              true, //有最大化按钮
              true);//最小化按钮

        //给内部窗体添加一个文本域
        JTextArea j = new JTextArea(5,20);
        getContentPane().add(j);
        //设置内部窗体的大小
        setSize(300,300);

        //设置内部窗体的显示位置
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    }
}
