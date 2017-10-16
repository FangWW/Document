import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * <p>Title: 检查盒演示</p>
 * <p>Description: 选择不同的选择框显示不同的图片</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: CheckBoxDemo.java</p>
 * @author 杜江
 * @version 1.0
 */
public class CheckBoxDemo extends JPanel
                          implements ItemListener {
    JCheckBox chinButton;
    JCheckBox glassesButton;
    JCheckBox hairButton;
    JCheckBox teethButton;

    /*
     * 有四个检查盒，分别对应下巴、眼镜、头发和牙齿
     * 图片不是拼出来的，而是根据检查盒选择拼写图片文件名
     * 图片文件名的定义格式为"geek-XXXX.gif"
     * 其中 XXXX 根据检查盒的不同选择，而不同。它的格式如下：

       ----             //没有选择

       c---             //一个选择
       -g--
       --h-
       ---t

       cg--             //两个选择
       c-h-
       c--t
       -gh-
       -g-t
       --ht

       -ght             //三个选择
       c-ht
       cg-t
       cgh-

       cght             //所有都选
     */

    StringBuffer choices;
    JLabel pictureLabel;

    public CheckBoxDemo() {
        super(new BorderLayout());

        //创建检查盒
        chinButton = new JCheckBox("下巴(c)");
        chinButton.setMnemonic(KeyEvent.VK_C);
        chinButton.setSelected(true);

        glassesButton = new JCheckBox("眼镜(g)");
        glassesButton.setMnemonic(KeyEvent.VK_G);
        glassesButton.setSelected(true);

        hairButton = new JCheckBox("头发(h)");
        hairButton.setMnemonic(KeyEvent.VK_H);
        hairButton.setSelected(true);

        teethButton = new JCheckBox("牙齿(t)");
        teethButton.setMnemonic(KeyEvent.VK_T);
        teethButton.setSelected(true);

        //给检查盒添加监听
        chinButton.addItemListener(this);
        glassesButton.addItemListener(this);
        hairButton.addItemListener(this);
        teethButton.addItemListener(this);

        choices = new StringBuffer("cght");

        //放置一个带图片的标签
        pictureLabel = new JLabel();
        pictureLabel.setFont(pictureLabel.getFont().deriveFont(Font.ITALIC));
        updatePicture();

        //将检查盒放置到面版中
        JPanel checkPanel = new JPanel(new GridLayout(0, 1));
        checkPanel.add(chinButton);
        checkPanel.add(glassesButton);
        checkPanel.add(hairButton);
        checkPanel.add(teethButton);

        add(checkPanel, BorderLayout.LINE_START);
        add(pictureLabel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }
/**
 *<br>方法说明：监听检查盒事件，拼凑图片的文件名XXXX部分
 *<br>输入参数：
 *<br>返回类型：
 */
    public void itemStateChanged(ItemEvent e) {
        int index = 0;
        char c = '-';
        Object source = e.getItemSelectable();

        if (source == chinButton) {
            index = 0;
            c = 'c';
        } else if (source == glassesButton) {
            index = 1;
            c = 'g';
        } else if (source == hairButton) {
            index = 2;
            c = 'h';
        } else if (source == teethButton) {
            index = 3;
            c = 't';
        }
        
        //取消选择事件
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            c = '-';
        }

        //改变文件名字XXXX
        choices.setCharAt(index, c);

        updatePicture();
    }
/**
 *<br>方法说明：绘制图片
 *<br>输入参数：
 *<br>返回类型：
 */
    protected void updatePicture() {
        //将得到的图片制成图标
        ImageIcon icon = createImageIcon(
                                    "images/geek/geek-"
                                    + choices.toString()
                                    + ".gif");
        pictureLabel.setIcon(icon);
        pictureLabel.setToolTipText(choices.toString());
        if (icon == null) {
            pictureLabel.setText("没有发现图片");
        } else {
            pictureLabel.setText(null);
        }
    }
/**
 *<br>方法说明：获取图标
 *<br>输入参数：String path 图片路径
 *<br>返回类型：ImageIcon对象
 */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = CheckBoxDemo.class.getResource(path);
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
    public static void main(String s[]) {
         JFrame.setDefaultLookAndFeelDecorated(true);

        //创建一个窗体，
        JFrame frame = new JFrame("CheckBoxDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //创建一个面板
        JComponent newContentPane = new CheckBoxDemo();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        //显示窗体
        frame.pack();
        frame.setVisible(true);
    }
}
