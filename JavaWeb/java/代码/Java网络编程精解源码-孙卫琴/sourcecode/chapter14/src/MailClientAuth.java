import javax.mail.*;
import javax.mail.internet.*;

public class MailClientAuth extends MailClient{
  private Authenticator ma;
  private String sendHost="localhost";
  private String receiveHost="localhost";
  private String receiveProtocol="imap";
  private String fromAddr="admin@mydomain.com";  //�����ߵ�ַ
  private String toAddr="admin@mydomain.com"; //�����ߵ�ַ

  public void init()throws Exception{
    //��������
    Properties  props = new Properties();
    props.put("mail.smtp.host", sendHost);
    
    // ����Session����
    ma=new MailAuthenticator();
    session = Session.getDefaultInstance(props,ma);

    // ����Store����
    store = session.getStore(receiveProtocol);
    //���ӵ��ʼ�������
    store.connect(receiveHost,null, null);
  }
  
  public static void main(String[] args)throws Exception {
    MailClientAuth client=new MailClientAuth();
    client.init();
    client.sendMessage(client.fromAddr,client.toAddr);
    client.receiveMessage();
    client.close();
    System.exit(0);
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
