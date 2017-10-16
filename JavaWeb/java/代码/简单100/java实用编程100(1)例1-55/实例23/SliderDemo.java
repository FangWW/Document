import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * <p>Title: 滑动杆演示</p>
 * <p>Description: 使用滑动杆控制定时器，来控制图片的播放速度</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: SliderDemo.java</p>
 * @author 杜江
 * @version 1.0
 */
public class SliderDemo extends JPanel
                        implements ActionListener,
                                   WindowListener,
                                   ChangeListener {
    //设置图片的参数
    static final int FPS_MIN = 0; //设置最小值
    static final int FPS_MAX = 30; //设置最大值
    static final int FPS_INIT = 15;  //初始数值
    int frameNumber = 0;
    int NUM_FRAMES = 14;
    ImageIcon[] images = new ImageIcon[NUM_FRAMES];
    int delay;
    Timer timer;
    boolean frozen = false;

    //这个标签用来显示这只小狗
    JLabel picture;

    public SliderDemo() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        delay = 1000 / FPS_INIT;

        //信息提示标签
        JLabel sliderLabel = new JLabel("调整滑动杆，改变播放速度！", JLabel.CENTER);
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //创建一个滑动杆，定义了最小值和最大值以及初始值
        JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL,
                                              FPS_MIN, FPS_MAX, FPS_INIT);
        framesPerSecond.addChangeListener(this);

        //定义滑动杆参数
        framesPerSecond.setMajorTickSpacing(10);//每10刻度标注一次
        framesPerSecond.setMinorTickSpacing(1);//最小刻度为1
        framesPerSecond.setPaintTicks(true);//绘制滑动杆上的刻度
        framesPerSecond.setPaintLabels(true);//在滑动过程中绘制滑动块
        framesPerSecond.setBorder(
                BorderFactory.createEmptyBorder(0,0,10,0));

        //定义一个用来显示图片的标签
        picture = new JLabel();
        picture.setHorizontalAlignment(JLabel.CENTER);
        picture.setAlignmentX(Component.CENTER_ALIGNMENT);
        picture.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLoweredBevelBorder(),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        updatePicture(0); //显示第一张图

        //将成员添加到面板上
        add(sliderLabel);
        add(framesPerSecond);
        add(picture);
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        //设置一个定时器来触发这个事件
        timer = new Timer(delay, this);
        timer.setInitialDelay(delay * 7); //在每轮循环停顿时间
        timer.setCoalesce(true);//设置重复循环
    }
/**
 *<br>方法说明：添加一个窗体监听
 *<br>输入参数：
 *<br>返回类型：
 */
    void addWindowListener(Window w) {
        w.addWindowListener(this);
    }
    public void windowIconified(WindowEvent e) {
        stopAnimation();
    }
    public void windowDeiconified(WindowEvent e) {
        startAnimation();
    }
    public void windowOpened(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
/**
 *<br>方法说明：对滑动杆进行监听
 *<br>输入参数：ChangeEvent e 滑动杆变动事件
 *<br>返回类型：
 */
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int fps = (int)source.getValue();//获得滑动杆的值
            if (fps == 0) {
                if (!frozen) stopAnimation();
            } else {
                delay = 1000 / fps;
                timer.setDelay(delay);
                timer.setInitialDelay(delay * 10);
                if (frozen) startAnimation();
            }
        }
    }
/**
 *<br>方法说明：开始动画
 *<br>输入参数：
 *<br>返回类型：
 */
    public void startAnimation() {
        timer.start();
        frozen = false;
    }
/**
 *<br>方法说明：停止动画
 *<br>输入参数：
 *<br>返回类型：
 */
    public void stopAnimation() {
        timer.stop();
        frozen = true;
    }
/**
 *<br>方法说明：事件监听
 *<br>输入参数：
 *<br>返回类型：
 */
    public void actionPerformed(ActionEvent e) {
        //改变图片帧
        if (frameNumber == (NUM_FRAMES - 1)) {
            frameNumber = 0;
        } else {
            frameNumber++;
        }

        updatePicture(frameNumber); //显示下张图

        if ( frameNumber==(NUM_FRAMES - 1)
          || frameNumber==(NUM_FRAMES/2 - 1) ) {
            timer.restart();
        }
    }
/**
 *<br>方法说明：绘制当前帧
 *<br>输入参数：int frameNum 图片帧数数
 *<br>返回类型：
 */
    protected void updatePicture(int frameNum) {
        if (images[frameNumber] == null) {
            images[frameNumber] = createImageIcon("images/doggy/T"
                                                  + frameNumber
                                                  + ".gif");
        }

        //绘制图片
        if (images[frameNumber] != null) {
            picture.setIcon(images[frameNumber]);
        } else { //如果没有发现图片
            picture.setText("image #" + frameNumber + " not found");
        }
    }
/**
 *<br>方法说明：获取图片
 *<br>输入参数：String path 图片路径
 *<br>返回类型：ImageIcon 图片对象
 */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = SliderDemo.class.getResource(path);
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
        JFrame frame = new JFrame("SliderDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //实例化本类
        SliderDemo animator = new SliderDemo();
        animator.setOpaque(true);
        frame.setContentPane(animator);

        //显示窗体
        frame.pack();
        frame.setVisible(true);
        animator.startAnimation(); 
    }
}
