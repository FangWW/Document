import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class HTMLDemo extends JFrame {
  private JLabel jLabel;
  private JButton jButton;

  public HTMLDemo(String title){
   super(title);
   
   jLabel = new JLabel("<html><b><i>Hello World!</i></b></html>");
   jButton = new JButton("<html><img src=\""+this.getClass().getResource("/go.jpg")+"\"></html>"); 
   //jButton = new JButton("<html><img src=\""+"http://www.javathinker.org/image/busy.jpg"+"\"></html>");    
   
   //设置鼠标移动到该Button时的提示信息
   jButton.setToolTipText("开始");
  
   Container contentPane=getContentPane();
   contentPane.setLayout(new GridLayout(2, 1));
   contentPane.add(jLabel) ;
   contentPane.add(jButton);

   pack();
   setVisible(true);

   //当用户选择JFrame的关闭图标，将结束程序
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
    new HTMLDemo("Hello");
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
