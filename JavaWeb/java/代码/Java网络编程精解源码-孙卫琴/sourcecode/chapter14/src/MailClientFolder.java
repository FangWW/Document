import javax.mail.*;
import javax.mail.internet.*;

public class MailClientFolder extends MailClient {
  /** �г����е��ʼ��� */
  public void listFolders()throws Exception{
    Folder rootFolder=store.getDefaultFolder();
    Folder[] folders=rootFolder.list();
    for(int i=0;i<folders.length;i++){
      System.out.println(folders[i]+"�ʼ��У�"+folders[i].getMessageCount()+"���ʼ�");
    }
  } 

  /** ɾ���ʼ��� */
  public void deleteFolder(String folderName )throws Exception {
    if(folderName.equalsIgnoreCase("inbox")||
         folderName.equalsIgnoreCase("trash")||
         folderName.equalsIgnoreCase("draft")||
         folderName.equalsIgnoreCase("sendbox")){
       throw new Exception("������ɾ�������ʼ���");
    }

    Folder folder=store.getFolder(folderName);
    if(!folder.exists())throw new Exception(folderName+"�ʼ��в�����");
    if(folder.isOpen())folder.close(true);
    folder.delete(true);
  }
  
  /** �����ʼ��� */
  public void createFolder(String folderName)throws Exception {
    if(folderName==null || folderName.equals(""))
      throw new Exception("����ָ���ʼ��е�����");
    Folder folder=store.getFolder(folderName);
    if(folder.exists())throw new Exception(folderName+"�ʼ����Ѿ�������");
    folder.create(Folder.HOLDS_MESSAGES);
  }
  
  /** �޸��ļ��е�����*/
  public void renameFolder(String fromName,String toName)throws Exception {
    if(toName==null || toName.equals(""))
       throw new Exception("����ָ���ʼ��е�������");
 
    if(fromName.equalsIgnoreCase("inbox")||
        fromName.equalsIgnoreCase("trash")||
        fromName.equalsIgnoreCase("draft")||
        fromName.equalsIgnoreCase("sendbox")||
        toName.equalsIgnoreCase("inbox")||

        toName.equalsIgnoreCase("trash")||
        toName.equalsIgnoreCase("draft")||
        toName.equalsIgnoreCase("sendbox")){
      throw new Exception("�������޸ı������ʼ��е�����");
    }

    Folder folderFrom=store.getFolder(fromName);
    Folder folderTo=store.getFolder(toName);
    if(!folderFrom.exists())throw new Exception(folderFrom+"���ʼ��в�����");
    if(folderFrom.isOpen())folderFrom.close(true);

    folderFrom.renameTo(folderTo);
  }

  /** ɾ���ʼ� */
  public void deleteMessage(int arrayOpt[],String folderName)throws Exception {
    Folder folder=store.getFolder(folderName);
    if(!folder.exists())throw new Exception(folderName+"���ʼ��в�����");
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
  
  /** �����ʼ� */ 
  public void sendMessage(Message msg)throws Exception {
    Transport.send(msg);
 
    //���ʼ����浽SendBox�ʼ���
    Folder folder=store.getFolder("sendbox");
    if(!folder.isOpen())folder.open(Folder.READ_WRITE);
    folder.appendMessages(new Message[]{msg});
  }
   
  /** �����������ʼ��� */ 
  public void createReservedFolders()throws Exception{
    String[] folderNames={"trash","draft","sendbox"};
    for(int i=0;i<folderNames.length;i++){
      Folder folder=store.getFolder(folderNames[i]);
      if(!folder.exists())
        folder.create(Folder.HOLDS_MESSAGES);
    }
  }
  
  /** ���ʼ����浽draft�ʼ��� */
  public void saveMessage(Message msg)throws Exception {
    saveMessage("draft",msg);
  }

    /** ���ʼ����浽�ض��ʼ��� */
  public void saveMessage(String folderName,Message msg)throws Exception {
    Folder folder=store.getFolder(folderName);
    if(!folder.exists())throw new Exception(folderName+"�ʼ��в�����"); 
    if(!folder.isOpen())folder.open(Folder.READ_WRITE);
    folder.appendMessages(new Message[]{msg});
  }

  /** ���ʼ���һ���ʼ����ƶ�����һ���ʼ��� */
  public void moveMessage(String fromFolderName,String toFolderName,Message msg)throws Exception {
    Folder folderFrom=store.getFolder(fromFolderName);;
    Folder folderTo=store.getFolder(toFolderName);
    if(!folderFrom.exists())throw new Exception(fromFolderName+"�ʼ��в�����"); 
    if(!folderTo.exists())throw new Exception(toFolderName+"�ʼ��в�����");
    folderFrom.copyMessages(new Message[]{msg},folderTo);
    msg.setFlag(Flags.Flag.DELETED, true);
    folderFrom.expunge();
  }

  public static void main(String[] args)throws Exception {
    MailClientFolder client=new MailClientFolder();
    client.init();
    client.createReservedFolders(); //����trash��sendbox��draft�ʼ���
    client.createFolder("myfolder"); //����myfolder�ʼ���
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
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
