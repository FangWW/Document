import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;
import java.io.*;

public class MailClientReadAttach extends MailClientSendAttach{
  private String fromAddr="java_mail@citiz.net";  //发送者地址
  private String toAddr="java_mail@citiz.net"; //接收者地址

  public void receiveMessage()throws Exception{
    Folder folder=store.getFolder("inbox");
    folder.open(Folder.READ_ONLY);
    System.out.println("You have "+folder.getMessageCount()+" messages in inbox.");
    System.out.println("You have "+folder.getUnreadMessageCount()+" unread messages in inbox.");

    //读邮件
    Message[] messages=folder.getMessages();
    for(int i=1;i<=messages.length;i++){
      System.out.println("------第"+i+"封邮件-------");
      //处理邮件
      processMessage(folder.getMessage(i));
      System.out.println();
    }

    folder.close(false);  //关闭邮件夹，但不删除邮件夹中标志为“deleted”的邮件
  }

  public static void processMessage(Message msg)throws Exception{
    processMessageHeader(msg);  //处理邮件头部

    Object body=msg.getContent();  //获得邮件正文
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
    
    if(fileName==null){ //如果不是附件，打印到控制台
      part.writeTo(System.out);
      System.out.println();
    }else{  
      File file=new File(fileName);
      //创建一个在文件系统中不存在的文件
      for(int i=1;file.exists();i++){
        String newName=i+"_"+fileName;
        file=new File(newName);
      }
      //把附件保存到一个文件中
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
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
