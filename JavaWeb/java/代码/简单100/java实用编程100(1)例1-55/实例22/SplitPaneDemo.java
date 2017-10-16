import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * <p>Title: 分割面板</p>
 * <p>Description: 演示将面板分割成左右两部分</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename:SplitPaneDemo.java </p>
 * @author 杜江
 * @version 1.0
 */
public class SplitPaneDemo implements ListSelectionListener {
    private String[] imageNames={"Bird.gif","Cat.gif","Dog.gif","Pig.gif"};
    private JLabel picture;
    private JList list;
    private JSplitPane splitPane;
/**
 *<br>方法说明：构造器，定义了所有要使用的构件
 *<br>输入参数：
 *<br>返回类型：
 */
    public SplitPaneDemo() {
        
        //创建一个图像名称的列表，设置为单选方式
        list = new JList(imageNames);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        JScrollPane listScrollPane = new JScrollPane(list);

        //获取默认的图片
        ImageIcon firstImage = createImageIcon("images/" +
                                     (String)imageNames[0]);
        if (firstImage != null) {
            picture = new JLabel(firstImage);
            picture.setPreferredSize(new Dimension(firstImage.getIconWidth(),
                                                   firstImage.getIconHeight()));
        } else {
            picture = new JLabel((String)imageNames[0]);
        }
        JScrollPane pictureScrollPane = new JScrollPane(picture);

        //创建一个水平分割的面板，定义了两个面板的名字。
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,
                                   listScrollPane, pictureScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        //定义面板的最小尺寸
        Dimension minimumSize = new Dimension(100, 50);
        listScrollPane.setMinimumSize(minimumSize);
        pictureScrollPane.setMinimumSize(minimumSize);

        //定义初始尺寸
        splitPane.setPreferredSize(new Dimension(400, 200));
    }
/**
 *<br>方法说明：获得这个分割的面板
 *<br>输入参数：
 *<br>返回类型：JSplitPane 对象
 */
    public JSplitPane getSplitPane() {
        return splitPane;
    }
/**
 *<br>方法说明：列表监听事件处理
 *<br>输入参数：ListSelectionEvent e 列表选择事件
 *<br>返回类型：
 */
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            return;

        JList theList = (JList)e.getSource();
        if (theList.isSelectionEmpty()) {
            picture.setIcon(null);
            picture.setText(null);
        } else {
            int index = theList.getSelectedIndex();
            ImageIcon newImage = createImageIcon("images/" +
                                     (String)imageNames[index]);
            picture.setIcon(newImage);
            if (newImage != null) {
                picture.setText(null);
                picture.setPreferredSize(new Dimension(newImage.getIconWidth(),
                                                       newImage.getIconHeight() ));
            } else {
                picture.setText("Image not found: "
                                + (String)imageNames[index]);
            }
            picture.revalidate();
        }
    }
/**
 *<br>方法说明：根据路径获取图形对象
 *<br>输入参数：String path 图片路径
 *<br>返回类型：ImageIcon 图片对象
 */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = SplitPaneDemo.class.getResource(path);
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
        JFrame.setDefaultLookAndFeelDecorated(true);

        //定义窗体
        JFrame frame = new JFrame("SplitPaneDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SplitPaneDemo splitPaneDemo = new SplitPaneDemo();
        frame.getContentPane().add(splitPaneDemo.getSplitPane());

        //显示窗体
        frame.pack();
        frame.setVisible(true);
    }
}
