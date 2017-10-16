import javax.mail.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MailAuthenticator extends Authenticator {

  private JDialog passwordDg=new JDialog(new JFrame(),true);
  private JLabel mainLb=new JLabel("请输入用户名和口令：");
  private JLabel userLb=new JLabel("用户名：") ;
  private JLabel passwordLb=new JLabel("口令：");
  private JTextField userTfd=new JTextField(20);
  private JPasswordField passwordPfd=new JPasswordField(20);
  private JButton okBt=new JButton("ok");
  
  public MailAuthenticator(){
    this("");
  }  
  public MailAuthenticator(String username){
    Container container=passwordDg.getContentPane();
    container.setLayout(new GridLayout(4,1));
    container.add(mainLb);

    JPanel userPanel=new JPanel();
    userPanel.add(userLb);
    userPanel.add(userTfd);
    userTfd.setText(username);
    container.add(userPanel);
    
    JPanel passwordPanel=new JPanel();
    passwordPanel.add(passwordLb);
    passwordPanel.add(passwordPfd);
    container.add(passwordPanel);
    
    JPanel okPanel=new JPanel();
    okPanel.add(okBt);
    container.add(okPanel);

    passwordDg.pack();
    
    ActionListener al=new ActionListener(){
      public void actionPerformed(ActionEvent e){ 
        passwordDg.setVisible(false);
      }
    };
    
    userTfd.addActionListener(al);
    passwordPfd.addActionListener(al);
    okBt.addActionListener(al);
  }

  public PasswordAuthentication getPasswordAuthentication() {
    passwordDg.setVisible(true);
    String password=new String(passwordPfd.getPassword());
    String username=userTfd.getText();
    passwordPfd.setText("");
    return new PasswordAuthentication(username,password);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
