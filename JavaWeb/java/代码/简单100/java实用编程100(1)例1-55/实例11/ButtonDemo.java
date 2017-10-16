import java.awt.*;
import java.awt.event.*;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

/**
 * <p>Title: 按钮演示</p>
 * <p>Description: 提供一个按钮的演示。如何实现按钮和是一个按钮失效</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author 杜江
 * @version 1.0
 */
public class ButtonDemo extends JPanel
                        implements ActionListener {
    protected JButton b1, b2, b3;
/**
 *<br>方法说明：构造器，初始图形界面构建
 *<br>输入参数：
 *<br>返回类型：
 */
    public ButtonDemo() {
        ImageIcon leftButtonIcon = createImageIcon("images/right.gif");
        ImageIcon middleButtonIcon = createImageIcon("images/middle.gif");
        ImageIcon rightButtonIcon = createImageIcon("images/left.gif");

        b1 = new JButton("失效中间按钮(D)", leftButtonIcon);
        b1.setVerticalTextPosition(AbstractButton.CENTER);//水平中间对齐
        b1.setHorizontalTextPosition(AbstractButton.LEADING);//相当于LEFT
        b1.setMnemonic(KeyEvent.VK_D);//将b1邦定alt+D键
        b1.setActionCommand("disable");

        b2 = new JButton("M中间按钮", middleButtonIcon);
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setMnemonic(KeyEvent.VK_M);//将b2邦定alt+M键

        b3 = new JButton("E激活中间按钮", rightButtonIcon);
        b3.setMnemonic(KeyEvent.VK_E);//将b3邦定alt+E键
        b3.setActionCommand("enable");
        b3.setEnabled(false);

        //给1和3添加事件监听
        b1.addActionListener(this);
        b3.addActionListener(this);
        //设置按钮提示文本
        b1.setToolTipText("点击这个按钮，将使中间的按钮失效！");
        b2.setToolTipText("点击这个按钮，没有任何的事件发生！");
        b3.setToolTipText("点击这个按钮，将使中间的按钮有效");

        //将按钮添加到JPanel中
        add(b1);
        add(b2);
        add(b3);
    }
/**
 *<br>方法说明：事件处理
 *<br>输入参数：
 *<br>返回类型：
 */
    public void actionPerformed(ActionEvent e) {
        if ("disable".equals(e.getActionCommand())) {
            b2.setEnabled(false);
            b1.setEnabled(false);
            b3.setEnabled(true);
        } else {
            b2.setEnabled(true);
            b1.setEnabled(true);
            b3.setEnabled(false);
        }
    }
/**
 *<br>方法说明：创建图标，
 *<br>输入参数：String path 图标所在的路径
 *<br>返回类型：ImageIcon 图标对象
 */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ButtonDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
    public static void main(String[] args) {
        //设置使用新的swing界面
        JFrame.setDefaultLookAndFeelDecorated(true);

        //创建一个窗体
        JFrame frame = new JFrame("ButtonDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //创建一个面板
        ButtonDemo newContentPane = new ButtonDemo();
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);

        //显示窗体
        frame.pack();
        frame.setVisible(true);
    }
}
