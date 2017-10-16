import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;
/**
 * <p>Title: 颜色选择器</p>
 * <p>Description: 演示一个颜色选择器，可以从样本中选择，可以使用HSB模式和RGB模式</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: ColorChooserDemo.java</p>
 * @author 杜江
 * @version 1.0
 */

public class ColorChooserDemo extends JPanel
                              implements ChangeListener {

    protected JColorChooser tcc;
    protected JLabel banner;

    public ColorChooserDemo() {
        super(new BorderLayout());

        //设置一个标签，做广告的。也用来显示用户选择的颜色。
        banner = new JLabel("欢迎使用颜色选择器！",
                            JLabel.CENTER);
        banner.setForeground(Color.yellow);
        banner.setBackground(Color.blue);
        banner.setOpaque(true);
        banner.setFont(new Font("SansSerif", Font.BOLD, 24));
        banner.setPreferredSize(new Dimension(100, 65));

        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.add(banner, BorderLayout.CENTER);
        bannerPanel.setBorder(BorderFactory.createTitledBorder("广告"));

        //设置选择颜色选择器
        tcc = new JColorChooser(banner.getForeground());//设置初始颜色
        tcc.getSelectionModel().addChangeListener(this);//给所有模式添加监听
        tcc.setBorder(BorderFactory.createTitledBorder("选择颜色"));

        add(bannerPanel, BorderLayout.CENTER);
        add(tcc, BorderLayout.PAGE_END);
    }
/**
 *<br>方法说明：事件监听。用户选择颜色触发
 *<br>输入参数：ChangeEvent e 用户选择事件
 *<br>返回类型：
 */
    public void stateChanged(ChangeEvent e) {
        Color newColor = tcc.getColor();//获取用户选择的颜色
        banner.setForeground(newColor);
    }
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        //创建窗体
        JFrame frame = new JFrame("ColorChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //创建面板容器
        JComponent newContentPane = new ColorChooserDemo();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        //显示窗体
        frame.pack();
        frame.setVisible(true);
    }
}
