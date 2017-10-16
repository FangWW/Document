import java.awt.*;
import java.awt.event.*;
import javax.swing.JPopupMenu;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
/**
 * <p>Title: 菜单演示</p>
 * <p>Description: 演示菜单的建立和快捷键的使用。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: MenuDemo.java</p>
 * @author 杜江
 * @version 1.0
 */

public class MenuDemo implements ActionListener, ItemListener {
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
/**
 *<br>方法说明：组建菜单栏
 *<br>输入参数：
 *<br>返回类型：
 */
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        //定义菜单条
        menuBar = new JMenuBar();

        //定义第一个菜单
        menu = new JMenu("(A)菜单");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

        //下面开始定义菜单项
        
        //只有文字
        menuItem = new JMenuItem("(O)只有文本的菜单",
                                 KeyEvent.VK_O);
        //设置快捷键
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        //添加监听
        menuItem.addActionListener(this);
        menu.add(menuItem);
        //有图标还有文字
        ImageIcon icon = createImageIcon("images/middle.gif");
        menuItem = new JMenuItem("(B)有图标和文字的菜单", icon);
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        //只有图标
        menuItem = new JMenuItem(icon);
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //定义一组radio button（单选按钮）菜单
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();

        rbMenuItem = new JRadioButtonMenuItem("(R)使用radio的菜单");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        rbMenuItem.addActionListener(this);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("(d)另外一个radio菜单");
        rbMenuItem.setMnemonic(KeyEvent.VK_D);
        group.add(rbMenuItem);
        rbMenuItem.addActionListener(this);
        menu.add(rbMenuItem);

        //定义一组check box（检查盒）菜单
        menu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("(C)使用检查盒的菜单");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        cbMenuItem.addItemListener(this);
        menu.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("(H)另外一个检查盒");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        cbMenuItem.addItemListener(this);
        menu.add(cbMenuItem);

        //定义一个带子菜单
        menu.addSeparator();
        submenu = new JMenu("(S)带有子菜单");
        submenu.setMnemonic(KeyEvent.VK_S);
        //定义子菜单
        menuItem = new JMenuItem("这是子菜单");
        //定义快捷键
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        submenu.add(menuItem);

        menuItem = new JMenuItem("子菜单项");
        menuItem.addActionListener(this);
        submenu.add(menuItem);
        menu.add(submenu);

        //定义第二个菜单
        menu = new JMenu("(N)第二个菜单");
        menu.setMnemonic(KeyEvent.VK_N);
        menuBar.add(menu);

        return menuBar;
    }
/**
 *<br>方法说明：构建面板
 *<br>输入参数：
 *<br>返回类型：
 */
    public Container createContentPane() {
        //构造一个面板
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //定义一个文本域
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);

        //将文本域添加到面板中
        contentPane.add(scrollPane, BorderLayout.CENTER);

        return contentPane;
    }
/**
 *<br>方法说明：构建弹出菜单
 *<br>输入参数：
 *<br>返回类型：
 */
    public void createPopupMenu() {
        JMenuItem menuItem;

        //构件弹出菜单
        JPopupMenu popup = new JPopupMenu();
        ImageIcon openicon = createImageIcon("images/Open16.gif");
        menuItem = new JMenuItem("打开文件",openicon);
        menuItem.addActionListener(this);
        popup.add(menuItem);
        ImageIcon saveicon = createImageIcon("images/Save16.gif");
        menuItem = new JMenuItem("保存文件",saveicon);
        menuItem.addActionListener(this);
        popup.add(menuItem);

        //添加一个监听给文本域，以便点击右键时响应
        MouseListener popupListener = new PopupListener(popup);
        output.addMouseListener(popupListener);
    }
/**
 *<br>方法说明：监听普通的菜单选择
 *<br>输入参数：ActionEvent e 事件
 *<br>返回类型：
 */
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "监测事件。"
                   + newline
                   + "    事件源: " + source.getText()
                   + " (选择对象" + getClassName(source) + ")";
        output.append(s + newline);
    }
/**
 *<br>方法说明：监听检查盒菜单选择项
 *<br>输入参数：ItemEvent e 检查盒触发的事件
 *<br>返回类型：
 */
    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "菜单项监听"
                   + newline
                   + "    事件源: " + source.getText()
                   + " (选择对象 " + getClassName(source) + ")"
                   + newline
                   + "    新的状态: "
                   + ((e.getStateChange() == ItemEvent.SELECTED) ?
                     "选择":"不选择");
        output.append(s + newline);
    }
/**
 *<br>方法说明：获得类的名字
 *<br>输入参数：
 *<br>返回类型：
 */
    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }
/**
 *<br>方法说明：根据路径查找图片
 *<br>输入参数：String path 图片的路径
 *<br>返回类型：ImageIcon 图片对象
 */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MenuDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        //创建一个窗体
        JFrame frame = new JFrame("MenuDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //创建菜单，并添加到面板中
        MenuDemo demo = new MenuDemo();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());

        //生成弹出菜单
        demo.createPopupMenu();

        //显示窗体
        frame.setSize(450, 260);
        frame.setVisible(true);
    }
//弹出菜单监听类
    class PopupListener extends MouseAdapter {
        JPopupMenu popup;

        PopupListener(JPopupMenu popupMenu) {
            popup = popupMenu;
        }
        
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(),
                           e.getX(), e.getY());
            }
        }
    }
}
