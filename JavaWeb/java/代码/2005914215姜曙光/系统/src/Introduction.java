import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;

public class Introduction extends JDialog               //帮助信息Dialog窗口
{
	//声明并创建面板对象
    JPanel panel1 = new JPanel();
    
    //创建文字标签对象
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JLabel jLabel7 = new JLabel();
    
    //设置布局管理器
    GridLayout gridLayout1 = new GridLayout();

    //构造函数重载
    public Introduction()                                             
    {
    	//调用父类构造方法
         super(new Frame(), "姜曙光聊天室目录", false);
         setSize(400,400);
         
        //设置帮助信息窗口中的显示信息

        panel1.setLayout(gridLayout1);
        gridLayout1.setColumns(1);
        gridLayout1.setRows(7);
        jLabel3.setText("1. 公聊");
        jLabel4.setText("2. 私聊 ");
        jLabel5.setText("3. 群聊");
        jLabel6.setText("4. 发送文件");
        jLabel7.setText("6. 播放音乐");
        
       

        panel1.add(jLabel3);
        panel1.add(jLabel4);
        panel1.add(jLabel5);
        panel1.add(jLabel6);
        panel1.add(jLabel7);
        getContentPane().add(panel1);

    }
}

