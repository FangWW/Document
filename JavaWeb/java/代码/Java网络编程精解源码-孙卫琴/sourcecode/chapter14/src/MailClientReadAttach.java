import javax.mail.*;
import javax.mail.internet.*;

public class MailClientReadAttach extends MailClientSendAttach{
  private String fromAddr="java_mail@citiz.net";  //�����ߵ�ַ
  private String toAddr="java_mail@citiz.net"; //�����ߵ�ַ

  public void receiveMessage()throws Exception{
    Folder folder=store.getFolder("inbox");
    folder.open(Folder.READ_ONLY);
    System.out.println("You have "+folder.getMessageCount()+" messages in inbox.");
    System.out.println("You have "+folder.getUnreadMessageCount()+" unread messages in inbox.");

    //���ʼ�
    Message[] messages=folder.getMessages();
    for(int i=1;i<=messages.length;i++){
      System.out.println("------��"+i+"���ʼ�-------");
      //�����ʼ�
      processMessage(folder.getMessage(i));
      System.out.println();
    }

    folder.close(false);  //�ر��ʼ��У�����ɾ���ʼ����б�־Ϊ��deleted�����ʼ�
  }

  public static void processMessage(Message msg)throws Exception{
    processMessageHeader(msg);  //�����ʼ�ͷ��

    Object body=msg.getContent();  //����ʼ�����
    if(body instanceof Multipart){ 
      processMultipart((Multipart)body);
    }else{
      processPart(msg);
    }
  }
  
  public static void processMessageHeader(Message msg)throws Exception{
    for(Enumeration<Header> e=msg.getAllHeaders(); e.hasMoreElements();) {
      Header header=e.nextElement();
      System.out.println(header.getName()+":"+header.getValue());
    }
  }

  public static void processMultipart(Multipart mp)throws Exception{
    for(int i=0;i<mp.getCount();i++){
      processPart(mp.getBodyPart(i));
    } 
  }   
  
  public static void processPart(Part part)throws Exception{
    String fileName=part.getFileName();
    String disposition=part.getDisposition();
    String contentType=part.getContentType();
    System.out.println("fileName="+fileName);
    System.out.println("disposition="+disposition);
    System.out.println("contentType="+contentType);

    if(contentType.toLowerCase().startsWith("multipart/")){
      processMultipart((Multipart)part.getContent());
    }else if(fileName==null
      && (Part.ATTACHMENT.equalsIgnoreCase(disposition)
      || !contentType.toLowerCase().startsWith("text/plain"))){
      fileName=File.createTempFile("attachment",".data").getName();
    }
    
    if(fileName==null){ //������Ǹ�������ӡ������̨
      part.writeTo(System.out);
      System.out.println();
    }else{  
      File file=new File(fileName);
      //����һ�����ļ�ϵͳ�в����ڵ��ļ�
      for(int i=1;file.exists();i++){
        String newName=i+"_"+fileName;
        file=new File(newName);
      }
      //�Ѹ������浽һ���ļ���
      OutputStream out=new BufferedOutputStream(new FileOutputStream(file));
      InputStream in=new BufferedInputStream(part.getInputStream());
      int b;
      while((b=in.read())!=-1)out.write(b);
      out.close();
      in.close();
    }
  }

  public static void main(String[] args)throws Exception {
    MailClientReadAttach client=new MailClientReadAttach();
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
