import javax.mail.*;
import javax.mail.internet.*;

public class MailClientSendAttach extends MailClientForCitiz{
  private String fromAddr="java_mail@citiz.net";  //�����ߵ�ַ
  private String toAddr="java_mail@citiz.net"; //�����ߵ�ַ

  public void sendMessage(String fromAddr,String toAddr) throws Exception{
    //����һ���ʼ�
    Message msg = new MimeMessage(session);
    InternetAddress[] toAddrs =InternetAddress.parse(toAddr, false);
    msg.setRecipients(Message.RecipientType.TO, toAddrs);
    msg.setSubject("hello");
    msg.setSentDate(new Date());
    msg.setFrom(new InternetAddress(fromAddr));
    
    String attch1="attch1.rar";
    String attch2="attch2.rar";

    Multipart multipart=new MimeMultipart();
    
    //�����ı�����
    MimeBodyPart mimeBodyPart1=new MimeBodyPart(); 
    mimeBodyPart1.setText("How are you");
    multipart.addBodyPart(mimeBodyPart1); 
    
    //�����һ������
    MimeBodyPart mimeBodyPart2=new MimeBodyPart(); 
    FileDataSource fds=new FileDataSource(attch1); //�õ�����Դ 
    mimeBodyPart2.setDataHandler(new DataHandler(fds)); 
    mimeBodyPart2.setDisposition(Part.ATTACHMENT);
    mimeBodyPart2.setFileName(fds.getName()); //�����ļ���
    multipart.addBodyPart(mimeBodyPart2); 

    //����ڶ�������
    MimeBodyPart mimeBodyPart3=new MimeBodyPart(); 
    fds=new FileDataSource(attch2); //�õ�����Դ 
    mimeBodyPart3.setDataHandler(new DataHandler(fds)); 
    mimeBodyPart3.setDisposition(Part.ATTACHMENT);
    mimeBodyPart3.setFileName(fds.getName()); //�����ļ���
    multipart.addBodyPart(mimeBodyPart3); 

    //�����ʼ�������
    msg.setContent(multipart); 
    
    //�����ʼ�
    Transport.send(msg);
  }
  
  public static void main(String[] args)throws Exception {
    MailClientSendAttach client=new MailClientSendAttach();
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
