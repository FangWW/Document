
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;

public class Introductions extends JDialog               //帮助信息Dialog窗口
{
	//声明并创建面板对象
    JPanel panel1 = new JPanel();
    
    //创建文字标签对象
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JLabel jLabel7 = new JLabel();
    
    //设置布局管理器
    GridLayout gridLayout1 = new GridLayout();

    //构造函数重载
    public Introductions()                                             
    {
    	//调用父类构造方法
         super(new Frame(), "姜曙光聊天室简介", false);
         setSize(400,400);
         
        //设置帮助信息窗口中的显示信息

        panel1.setLayout(gridLayout1);
        gridLayout1.setColumns(1);
        gridLayout1.setRows(7);
        jLabel1.setText("姜曙光聊天室1.0版，谢谢支持");
        jLabel2.setText("最左边是用户列表，显示了聊天室的在线用户列表；");
        jLabel3.setText("在线用户列表下边的标签可以显示在线人数，极大的方便了用户；");
        jLabel4.setText("右边的大的文本框显示出聊天纪录，用户可以在这看到其他成员发");
        jLabel5.setText("给自己的聊天信息；");
        jLabel6.setText("下边的单行文本框输入用户预发送的内容；");
        jLabel7.setText("聊天室附加了文件传送的功能。");
       

        panel1.add(jLabel1, null);
        panel1.add(jLabel2);
        panel1.add(jLabel3);
        panel1.add(jLabel4);
        panel1.add(jLabel5);
        panel1.add(jLabel6);
        panel1.add(jLabel7);
        getContentPane().add(panel1);

    }
}

