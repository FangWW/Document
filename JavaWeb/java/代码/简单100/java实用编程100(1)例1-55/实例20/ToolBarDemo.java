import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import java.net.URL;

import java.awt.*;
import java.awt.event.*;
/**
 * <p>Title: 工具栏演示</p>
 * <p>Description: 提供一个工具栏，包括“打开”、“保存”、“搜索”工具按钮</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: ToolBarDemo.java</p>
 * @author 杜江
 * @version 1.0
 */
public class ToolBarDemo extends JPanel
                         implements ActionListener {
    protected JTextArea textArea;
    protected String newline = "\n";
    static final private String OPEN = "OPEN";
    static final private String SAVE = "SAVE";
    static final private String SEARCH = "SEARCH";
/**
 *<br>方法说明：构造器
 *<br>输入参数：
 *<br>返回类型：
 */
    public ToolBarDemo() {
        super(new BorderLayout());

        //创建工具栏
        JToolBar toolBar = new JToolBar();
        addButtons(toolBar);

        //创建一个文本域，用来输出一些信息
        textArea = new JTextArea(15, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        //安放成员
        setPreferredSize(new Dimension(450, 110));
        add(toolBar, BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);
    }
/**
 *<br>方法说明：构建工具栏
 *<br>输入参数：JToolBar toolBar 工具条
 *<br>返回类型：
 */
    protected void addButtons(JToolBar toolBar) {
        JButton button = null;

        //第一个按钮，“打开”
        button = makeNavigationButton("Open16", OPEN,
                                      "打开一个文件！",
                                      "打开");
        toolBar.add(button);

        //第二个按钮，“保存”
        button = makeNavigationButton("Save16", SAVE,
                                      "保存当前文件！",
                                      "保存");
        toolBar.add(button);

        //第三个按钮，“搜索”
        button = makeNavigationButton("Search16", SEARCH,
                                      "搜索文件中的字符！",
                                      "搜索");
        toolBar.add(button);
    }
/**
 *<br>方法说明：构造工具栏上的按钮
 *<br>输入参数：
 *<br>返回类型：
 */
    protected JButton makeNavigationButton(String imageName,
                                           String actionCommand,
                                           String toolTipText,
                                           String altText) {
        //搜索图片
        String imgLocation = "images/"
                             + imageName
                             + ".gif";
        URL imageURL = ToolBarDemo.class.getResource(imgLocation);

        //初始化工具按钮
        JButton button = new JButton();
        //设置按钮的命令
        button.setActionCommand(actionCommand);
        //设置提示信息
        button.setToolTipText(toolTipText);
        button.addActionListener(this);
        
        if (imageURL != null) {                      //找到图像
            button.setIcon(new ImageIcon(imageURL));
        } else {                                     //没有图像
            button.setText(altText);
            System.err.println("Resource not found: "
                               + imgLocation);
        }

        return button;
    }
/**
 *<br>方法说明：事件监听
 *<br>输入参数：
 *<br>返回类型：
 */
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String description = null;

        if (OPEN.equals(cmd)) { //点击第一个按钮
            description = "打开一个文件操作！";
        } else if (SAVE.equals(cmd)) { //点击第二个按钮
            description = "保存文件操作";
        } else if (SEARCH.equals(cmd)) { //点击第三个按钮
            description = "搜索字符操作";
        }

        displayResult("如果这里是真正的程序，你将进入： "
                        + description);
    }

    protected void displayResult(String actionDescription) {
        textArea.append(actionDescription + newline);
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        //定义窗体
        JFrame frame = new JFrame("ToolBarDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //定义面板
        ToolBarDemo newContentPane = new ToolBarDemo();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        //显示窗体
        frame.pack();
        frame.setVisible(true);
    }
}
