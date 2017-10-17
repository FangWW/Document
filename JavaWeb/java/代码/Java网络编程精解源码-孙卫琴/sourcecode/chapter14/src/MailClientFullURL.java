import javax.mail.*;
import javax.mail.internet.*;

public class MailClientFullURL extends MailClient {
  private Folder folder;
  public void init()throws Exception{
    init(new URLName("imap://admin:1234@localhost/inbox"));
    //init(new URLName("pop3://java_mail:123456@pop.citiz.net/inbox"));
  }
  public void init(URLName urlName)throws Exception{
    //��������
    Properties props = new Properties();
     
    // ����Session����
    session = Session.getDefaultInstance(props);
    folder=session.getFolder(urlName);
    if(folder==null){ 
      System.out.println(urlName.getFile()+"�ʼ��в�����");
      System.exit(0);
    }
   }
  
  public static void main(String[] args)throws Exception {
    MailClientFullURL client=new MailClientFullURL();
    client.init();
    client.browseMessagesFromFolder(client.folder);
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
