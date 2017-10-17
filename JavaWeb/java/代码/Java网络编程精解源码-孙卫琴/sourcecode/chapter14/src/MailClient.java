import javax.mail.*;
import javax.mail.internet.*;

public class MailClient {
  protected Session session;
  protected Store store;
  private String sendHost="localhost"; //�����ʼ�������
  private String receiveHost="localhost"; //�����ʼ�������
  private String sendProtocol="smtp"; //�����ʼ�Э��
  private String receiveProtocol="imap"; //�����ʼ�Э��
  private String username = "admin";
  private String password = "1234";
  private String fromAddr="admin@mydomain.com";  //�����ߵ�ַ
  private String toAddr="admin@mydomain.com"; //�����ߵ�ַ

  public void init()throws Exception{
    //��������
    Properties  props = new Properties();
    props.put("mail.transport.protocol", sendProtocol);
    props.put("mail.store.protocol", receiveProtocol);
    props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");
    props.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");
    props.put("mail.smtp.host", sendHost); //���÷����ʼ�������

    // ����Session����
    session = Session.getDefaultInstance(props);
    //session.setDebug(true); //���������־

    // ����Store����
    store = session.getStore(receiveProtocol);
    //���ӵ����ʼ�������
    store.connect(receiveHost,username,password);
  }
  
  public void close()throws Exception{
    store.close();
  }
  public void sendMessage(String fromAddr,String toAddr)throws Exception{
    //����һ���ʼ�
    Message msg = createSimpleMessage(fromAddr,toAddr);
    //�����ʼ�
    Transport.send(msg);
  }
  
  public Message createSimpleMessage(String fromAddr,String toAddr)throws Exception{
    //����һ�ⴿ�ı����͵��ʼ�
    Message msg = new MimeMessage(session);
    InternetAddress[] toAddrs =InternetAddress.parse(toAddr, false);
    msg.setRecipients(Message.RecipientType.TO, toAddrs);
    msg.setSentDate(new Date());     
    msg.setSubject("hello");
    msg.setFrom(new InternetAddress(fromAddr));
    msg.setText("How are you");
    return msg; 
  }
  public void receiveMessage()throws Exception{
    browseMessagesFromFolder("inbox");
  }
  
  public void browseMessagesFromFolder(String folderName)throws Exception{
    Folder folder=store.getFolder(folderName);
    if(folder==null)
      throw new Exception(folderName+"�ʼ��в�����");
    browseMessagesFromFolder(folder);
  }
  
  public void browseMessagesFromFolder(Folder folder)throws Exception{
    folder.open(Folder.READ_ONLY);
    System.out.println("You have "+folder.getMessageCount()+" messages in inbox.");
    System.out.println("You have "+folder.getUnreadMessageCount()+" unread messages in inbox.");

    //���ʼ�
    Message[] messages=folder.getMessages();
    for(int i=1;i<=messages.length;i++){
      System.out.println("------��"+i+"���ʼ�-------");
      //��ӡ�ʼ���Ϣ
      folder.getMessage(i).writeTo(System.out);
      System.out.println();
    }
    folder.close(false);  //�ر��ʼ��У�����ɾ���ʼ����б�־Ϊ��deleted�����ʼ�
  }

  public static void main(String[] args)throws Exception {
    MailClient client=new MailClient();
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
