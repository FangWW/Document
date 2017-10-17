/**
 * <p>Title: �ļ�����</p>
 * <p>Description: ��ʾ�ļ���ɾ���ͻ�ȡ�ļ�����Ϣ</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author �Ž�
 * @version 1.0
 */
public class fileOperation{
/**
 *<br>����˵����ɾ���ļ�
 *<br>���������String fileName Ҫɾ�����ļ���
 *<br>�������ͣ�boolean �ɹ�Ϊtrue
 */
  public boolean delFile(String fileName){
  	try{
  	  //ɾ���ļ�
      boolean success = (new File(fileName)).delete();
      if (!success) {
         System.out.println("delete file error!");
         return false;
      }else{
         return true;
      }
    }catch(Exception e){
      System.out.println(e);
      return false;
    }
  }
/**
 *<br>����˵������ȡ�ļ���Ϣ
 *<br>���������String Name �ļ���
 *<br>�������ͣ�String[] �ļ���Ϣ����
 */
  public String[] getFileInfo(String Name){
    try{
      File file = new File(Name);
      //��ȡ�ļ��޸����ڣ����ص��Ǿ䣩
      long modifiedTime = file.lastModified();
      //��ȡ�ļ����ȣ���λ��Bite��
      long filesize = file.length();
      //�����ļ��Ƿ�ɶ�
      boolean cr = file.canRead();
      //�����ļ��Ƿ��д
      boolean cw = file.canWrite();
      //�����ļ��Ƿ�����
      boolean ih = file.isHidden();
      
      String[] sTemp = new String[6];
      sTemp[0] = String.valueOf(filesize);
      sTemp[1] = getDateString(modifiedTime);
      sTemp[2] = String.valueOf(cr);
      sTemp[3] = String.valueOf(cw);
      sTemp[4] = String.valueOf(ih);
      sTemp[5] = String.valueOf(file.getCanonicalPath());
      return sTemp;
    }catch(Exception e){
      System.out.println(e);
      return null;
    }
  }

/**
 *<br>����˵��������������ת��Ϊ����
 *<br>���������mill    ������
 *<br>�������ͣ�String �ַ� ��ʽΪ��yyyy-mm-dd hh:mm
 */
  public static String getDateString(long mill)
  {
    if(mill < 0) return  "";
    
    Date date = new Date(mill);
    Calendar rightNow = Calendar.getInstance();
    rightNow.setTime(date);
    int year = rightNow.get(Calendar.YEAR);
    int month = rightNow.get(Calendar.MONTH);
    int day = rightNow.get(Calendar.DAY_OF_MONTH);
    int hour = rightNow.get(Calendar.HOUR_OF_DAY);
    int min = rightNow.get(Calendar.MINUTE);

    return year + "-" + (month <10 ? "0" + month : "" + month) + "-" 
           +  (day <10 ? "0" + day : "" + day)
           +  (hour <10 ? "0" + hour : "" + hour)+":"
           + (min <10 ? "0" + min : "" + min);
  }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
  public static void main(String[] args){
  	try{
      fileOperation fo = new fileOperation();
      if(args.length==0){
        return;
      }else{
        String cmd = args[0];
        if(cmd.equals("del")){
          boolean bdel = fo.delFile(args[1]);
          System.out.println(bdel);
        }else if(cmd.equals("info")){
          String[] sTemp = fo.getFileInfo(args[1]);
          for(int i=0;i<sTemp.length;i++)
            System.out.println(sTemp[i]);
        }
      
      }
    }catch(Exception e){
      return;
    }
  }
}