import javax.mail.*;
import javax.mail.internet.*;

public class MailClientForCitiz extends MailClient{
  private Authenticator ma;
  private String sendHost="smtp.citiz.net";
  private String receiveHost="pop.citiz.net";
  private String receiveProtocol="pop3";
  private String username = "java_mail";
  private String password = "123456";
  private String fromAddr="java_mail@citiz.net";  //�����ߵ�ַ
  private String toAddr="java_mail@citiz.net"; //�����ߵ�ַ
 
  public void init()throws Exception{
    //��������
    Properties  props = new Properties();
    props.put("mail.smtp.host", sendHost);
    props.put("mail.smtp.auth","true"); //SMTP��������Ҫ�����֤

    ma=new Authenticator(){
         public PasswordAuthentication getPasswordAuthentication() {
           System.out.println("call getPasswordAuthentication()");
           return new PasswordAuthentication(username,password);
         }
    };
    
    session = Session.getDefaultInstance(props,ma);

    // ����Store����
    store = session.getStore(receiveProtocol);
    //���ӵ��ʼ��������ϵ��˻�
    store.connect(receiveHost,null, null);
  }
  
  public static void main(String[] args)throws Exception {
    MailClientForCitiz client=new MailClientForCitiz();
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
