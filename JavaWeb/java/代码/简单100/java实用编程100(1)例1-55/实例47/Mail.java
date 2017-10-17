import javax.mail.*;
import javax.mail.internet.*;

/**
 * <p>Title: ʹ��javamail�����ʼ�</p>
 * <p>Description: ��ʾ���ʹ��javamail�����͵����ʼ������ʵ���ɷ��Ͷ฽��</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: Mail.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class Mail {

String to = "";//�ռ���
String from = "";//������
String host = "";//smtp����
String username = "" ;
String password = "" ;
String filename = "";//�����ļ���
String subject = "";//�ʼ�����
String content = "";//�ʼ�����
Vector file = new Vector();//�����ļ�����
/**
 *<br>����˵����Ĭ�Ϲ�����
 *<br>���������
 *<br>�������ͣ�
 */
public Mail(){
}
/**
 *<br>����˵�������������ṩֱ�ӵĲ�������
 *<br>���������
 *<br>�������ͣ�
 */
public Mail(String to,String from,String smtpServer,String username,String password,String subject,String content){
  this.to = to;
  this.from = from;
  this.host = smtpServer;
  this.username = username;
  this.password = password;
  this.subject = subject;
  this.content = content;
}
/**
 *<br>����˵���������ʼ���������ַ
 *<br>���������String host �ʼ���������ַ����
 *<br>�������ͣ�
 */
public void setHost(String host){
  this.host = host;
}
/**
 *<br>����˵�������õ�¼������У������
 *<br>���������
 *<br>�������ͣ�
 */
public void setPassWord(String pwd){
  this.password = pwd;
}
/**
 *<br>����˵�������õ�¼������У���û�
 *<br>���������
 *<br>�������ͣ�
 */
public void setUserName(String usn){
  this.username = usn;
}
/**
 *<br>����˵���������ʼ�����Ŀ������
 *<br>���������
 *<br>�������ͣ�
 */
public void setTo(String to){
  this.to = to;
}
/**
 *<br>����˵���������ʼ�����Դ����
 *<br>���������
 *<br>�������ͣ�
 */
public void setFrom(String from){
  this.from = from;
}
/**
 *<br>����˵���������ʼ�����
 *<br>���������
 *<br>�������ͣ�
 */
public void setSubject(String subject){
  this.subject = subject;
}
/**
 *<br>����˵���������ʼ�����
 *<br>���������
 *<br>�������ͣ�
 */
public void setContent(String content){
  this.content = content;
}
/**
 *<br>����˵����������ת��Ϊ����
 *<br>���������String strText 
 *<br>�������ͣ�
 */
public String transferChinese(String strText){
  try{
    strText = MimeUtility.encodeText(new String(strText.getBytes(), "GB2312"), "GB2312", "B");
  }catch(Exception e){
    e.printStackTrace();
  }
  return strText;
}
/**
 *<br>����˵�����������������Ӹ���
 *<br>���������
 *<br>�������ͣ�
 */
public void attachfile(String fname){
  file.addElement(fname);
}
/**
 *<br>����˵���������ʼ�
 *<br>���������
 *<br>�������ͣ�boolean �ɹ�Ϊtrue����֮Ϊfalse
 */
public boolean sendMail(){

  //����mail session
  Properties props = System.getProperties();
  props.put("mail.smtp.host",host);
  props.put("mail.smtp.auth","true");
  Session session=Session.getDefaultInstance(props, new Authenticator(){
   public PasswordAuthentication getPasswordAuthentication(){
    return new PasswordAuthentication(username,password); 
   }
  });
  
  try {
    //����MimeMessage ���趨������ֵ
    MimeMessage msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress(from));
    InternetAddress[] address={new InternetAddress(to)};
    msg.setRecipients(Message.RecipientType.TO,address);
    subject = transferChinese(subject);
    msg.setSubject(subject);
    
    //����Multipart
    Multipart mp = new MimeMultipart();
    
    //��Multipart�������
    MimeBodyPart mbpContent = new MimeBodyPart();
    mbpContent.setText(content);
    //��MimeMessage��ӣ�Multipart�������ģ�
    mp.addBodyPart(mbpContent);
    
    //��Multipart��Ӹ���
    Enumeration efile=file.elements();
    while(efile.hasMoreElements()){
    
      MimeBodyPart mbpFile = new MimeBodyPart();
      filename=efile.nextElement().toString();
      FileDataSource fds = new FileDataSource(filename);
      mbpFile.setDataHandler(new DataHandler(fds));
      mbpFile.setFileName(fds.getName());
      //��MimeMessage��ӣ�Multipart��������
      mp.addBodyPart(mbpFile);

    }
    
    file.removeAllElements();
    //��Multipart���MimeMessage
    msg.setContent(mp);
    msg.setSentDate(new Date());
    //�����ʼ�
    Transport.send(msg);
  
  } catch (MessagingException mex) {
    mex.printStackTrace();
    Exception ex = null;
    if ((ex=mex.getNextException())!=null){
      ex.printStackTrace();
    }
    return false;
  }
  return true;
 }
/**
 *<br>����˵���������������ڲ���
 *<br>���������
 *<br>�������ͣ�
 */
 public static void main(String[] args){
  Mail sendmail = new Mail();
  sendmail.setHost("smtp.sohu.com");
  sendmail.setUserName("du_jiang");
  sendmail.setPassWord("31415926");
  sendmail.setTo("dujiang@sricnet.com");
  sendmail.setFrom("du_jiang@sohu.com");
  sendmail.setSubject("��ã����ǲ��ԣ�");
  sendmail.setContent("�������һ�����฽���Ĳ��ԣ�");	
  //Mail sendmail = new Mail("dujiang@sricnet.com","du_jiang@sohu.com","smtp.sohu.com","du_jiang","31415926","���","θ�������");
  sendmail.attachfile("c:\\test.txt");
  sendmail.attachfile("DND.jar");
  sendmail.sendMail();

 }
}//end

