import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class MailClientFolder extends MailClient {
  /** 列出所有的邮件夹 */
  public void listFolders()throws Exception{
    Folder rootFolder=store.getDefaultFolder();
    Folder[] folders=rootFolder.list();
    for(int i=0;i<folders.length;i++){
      System.out.println(folders[i]+"邮件夹："+folders[i].getMessageCount()+"封邮件");
    }
  } 

  /** 删除邮件夹 */
  public void deleteFolder(String folderName )throws Exception {
    if(folderName.equalsIgnoreCase("inbox")||
         folderName.equalsIgnoreCase("trash")||
         folderName.equalsIgnoreCase("draft")||
         folderName.equalsIgnoreCase("sendbox")){
       throw new Exception("不允许删除保留邮件夹");
    }

    Folder folder=store.getFolder(folderName);
    if(!folder.exists())throw new Exception(folderName+"邮件夹不存在");
    if(folder.isOpen())folder.close(true);
    folder.delete(true);
  }
  
  /** 创建邮件夹 */
  public void createFolder(String folderName)throws Exception {
    if(folderName==null || folderName.equals(""))
      throw new Exception("必须指定邮件夹的名字");
    Folder folder=store.getFolder(folderName);
    if(folder.exists())throw new Exception(folderName+"邮件夹已经存在了");
    folder.create(Folder.HOLDS_MESSAGES);
  }
  
  /** 修改文件夹的名字*/
  public void renameFolder(String fromName,String toName)throws Exception {
    if(toName==null || toName.equals(""))
       throw new Exception("必须指定邮件夹的新名字");
 
    if(fromName.equalsIgnoreCase("inbox")||
        fromName.equalsIgnoreCase("trash")||
        fromName.equalsIgnoreCase("draft")||
        fromName.equalsIgnoreCase("sendbox")||
        toName.equalsIgnoreCase("inbox")||

        toName.equalsIgnoreCase("trash")||
        toName.equalsIgnoreCase("draft")||
        toName.equalsIgnoreCase("sendbox")){
      throw new Exception("不允许修改保留的邮件夹的名字");
    }

    Folder folderFrom=store.getFolder(fromName);
    Folder folderTo=store.getFolder(toName);
    if(!folderFrom.exists())throw new Exception(folderFrom+"该邮件夹不存在");
    if(folderFrom.isOpen())folderFrom.close(true);

    folderFrom.renameTo(folderTo);
  }

  /** 删除邮件 */
  public void deleteMessage(int arrayOpt[],String folderName)throws Exception {
    Folder folder=store.getFolder(folderName);
    if(!folder.exists())throw new Exception(folderName+"该邮件夹不存在");
    if(!folder.isOpen())folder.open(Folder.READ_WRITE);

    for(int i=0;i<arrayOpt.length;i++){
      if(arrayOpt[i]==0)continue;
      Message msg=folder.getMessage(i+1);
      if(!folder.getName().equals("trash")){
         Folder Trash=store.getFolder("trash");
         folder.copyMessages(new Message[]{msg},Trash);
         msg.setFlag(Flags.Flag.DELETED, true);
      }else{
         msg.setFlag(Flags.Flag.DELETED, true);
      }
    }
    folder.expunge();
  }
  
  /** 发送邮件 */ 
  public void sendMessage(Message msg)throws Exception {
    Transport.send(msg);
 
    //把邮件保存到SendBox邮件夹
    Folder folder=store.getFolder("sendbox");
    if(!folder.isOpen())folder.open(Folder.READ_WRITE);
    folder.appendMessages(new Message[]{msg});
  }
   
  /** 创建保留的邮件夹 */ 
  public void createReservedFolders()throws Exception{
    String[] folderNames={"trash","draft","sendbox"};
    for(int i=0;i<folderNames.length;i++){
      Folder folder=store.getFolder(folderNames[i]);
      if(!folder.exists())
        folder.create(Folder.HOLDS_MESSAGES);
    }
  }
  
  /** 把邮件保存到draft邮件夹 */
  public void saveMessage(Message msg)throws Exception {
    saveMessage("draft",msg);
  }

    /** 把邮件保存到特定邮件夹 */
  public void saveMessage(String folderName,Message msg)throws Exception {
    Folder folder=store.getFolder(folderName);
    if(!folder.exists())throw new Exception(folderName+"邮件夹不存在"); 
    if(!folder.isOpen())folder.open(Folder.READ_WRITE);
    folder.appendMessages(new Message[]{msg});
  }

  /** 把邮件从一个邮件夹移动到另一个邮件夹 */
  public void moveMessage(String fromFolderName,String toFolderName,Message msg)throws Exception {
    Folder folderFrom=store.getFolder(fromFolderName);;
    Folder folderTo=store.getFolder(toFolderName);
    if(!folderFrom.exists())throw new Exception(fromFolderName+"邮件夹不存在"); 
    if(!folderTo.exists())throw new Exception(toFolderName+"邮件夹不存在");
    folderFrom.copyMessages(new Message[]{msg},folderTo);
    msg.setFlag(Flags.Flag.DELETED, true);
    folderFrom.expunge();
  }

  public static void main(String[] args)throws Exception {
    MailClientFolder client=new MailClientFolder();
    client.init();
    client.createReservedFolders(); //创建trash、sendbox和draft邮件夹
    client.createFolder("myfolder"); //创建myfolder邮件夹
    client.renameFolder("myfolder","onefolder");
    Message msg=client.createSimpleMessage("admin@mydomain.com","admin@mydomain.com");
    client.sendMessage(msg);
    client.saveMessage("onefolder",msg);
    client.deleteMessage(new int[]{1},"onefolder");
    client.listFolders();
    client.deleteFolder("onefolder");
    client.close();
  }

}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
