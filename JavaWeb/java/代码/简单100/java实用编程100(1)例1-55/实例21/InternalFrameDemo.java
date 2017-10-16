import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import java.awt.event.*;
import java.awt.*;
/**
 * <p>Title: 内部窗体演示</p>
 * <p>Description: 这是演示一个内部窗体。可以选择“新文档”菜单不停的生成内部窗体。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author 杜江
 * @version 1.0
 */
public class InternalFrameDemo extends JFrame
                               implements ActionListener {
    JDesktopPane desktop;
/**
 *<br>方法说明：构造器，添加窗体成员
 *<br>输入参数：
 *<br>返回类型：
 */
    public InternalFrameDemo() {
        super("InternalFrameDemo");

        //这里设置了一个比较大的窗体，给四周只留了50px的边界
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                  screenSize.width  - inset*2,
                  screenSize.height - inset*2);
        
        //定义界面
        desktop = new JDesktopPane(); //桌面面板
        createFrame(); //创建第一个内部窗体
        setContentPane(desktop);//将桌面添加到窗体
        setJMenuBar(createMenuBar());

        //设置托拽模式
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }
/**
 *<br>方法说明：组建菜单
 *<br>输入参数：
 *<br>返回类型：
 */
    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        //定义一个菜单
        JMenu menu = new JMenu("文件");
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        //定义“新文档”菜单
        JMenuItem menuItem = new JMenuItem("新文档");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("new");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //定义“退出”菜单
        menuItem = new JMenuItem("退出");
        menuItem.setMnemonic(KeyEvent.VK_Q);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        return menuBar;
    }
/**
 *<br>方法说明：事件监听，对选择的菜单做出反映
 *<br>输入参数：ActionEvent e 事件
 *<br>返回类型：
 */
    public void actionPerformed(ActionEvent e) {
        if ("new".equals(e.getActionCommand())) { //新文档
            createFrame();
        } else { //退出
            quit();
        }
    }
/**
 *<br>方法说明：调用MyInternalFrame类创建新的内部窗体，
 *<br>输入参数：
 *<br>返回类型：
 */
    protected void createFrame() {
        MyInternalFrame frame = new MyInternalFrame();
        
        frame.setVisible(true); 
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
/**
 *<br>方法说明：退出程序
 *<br>输入参数：
 *<br>返回类型：
 */
    protected void quit() {
        System.exit(0);
    }
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        //建立一个内部窗体
        InternalFrameDemo frame = new InternalFrameDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //显示窗体
        frame.setVisible(true);
    }
}
