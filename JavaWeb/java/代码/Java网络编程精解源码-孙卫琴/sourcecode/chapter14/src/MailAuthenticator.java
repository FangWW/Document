import javax.mail.*;

public class MailAuthenticator extends Authenticator {

  private JDialog passwordDg=new JDialog(new JFrame(),true);
  private JLabel mainLb=new JLabel("�������û����Ϳ��");
  private JLabel userLb=new JLabel("�û�����") ;
  private JLabel passwordLb=new JLabel("���");
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
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
