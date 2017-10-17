import javax.mail.*;
import javax.mail.internet.*;

public class MailClientURLName extends MailClient {
  private String sendHost="localhost"; //�����ʼ�������
  private String receiveHost;
  private String receiveProtocol; //�����ʼ�Э��
  private String username;
  private String password;
  private String fromAddr="admin@mydomain.com";  //�����ߵ�ַ
  private String toAddr="admin@mydomain.com"; //�����ߵ�ַ

  public void init()throws Exception{
    init(new URLName("imap://admin:1234@localhost/"));
  }
  public void init(URLName urlName)throws Exception{
    receiveProtocol=urlName.getProtocol();   
    receiveHost=urlName.getHost();
    username=urlName.getUsername();
    password=urlName.getPassword();
   
    //��������
    Properties props = new Properties();
    props.put("mail.smtp.host", sendHost);
    
    // ����Session����
    session = Session.getDefaultInstance(props);

    // ����Store����
    store = session.getStore(receiveProtocol);
    //���ӵ��ʼ�������
    store.connect(receiveHost,username,password);
  }
  
  public static void main(String[] args)throws Exception {
    MailClientURLName client=new MailClientURLName();
    client.init();
    client.sendMessage(client.fromAddr,client.toAddr);
    client.receiveMessage();
    client.close();
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
