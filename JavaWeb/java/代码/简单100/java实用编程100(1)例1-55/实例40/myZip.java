//�ļ�����myZip.java
/**
 * <p>Title: �ļ�ѹ���ͽ�ѹ</p>
 * <p>Description: ʹ��ZipInputStream��ZipOutputStream���ļ�
 *                 ��Ŀ¼����ѹ���ͽ�ѹ����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: myZip.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class myZip{
/**
 *<br>����˵����ʵ���ļ���ѹ������
 *<br>���������String[] fs ѹ�����ļ�����
 *<br>�������ͣ�
 */
  public void ZipFiles(String[] fs){
   try{
     String fileName = fs[0];
     FileOutputStream f =
       new FileOutputStream(fileName+".zip");
     //ʹ����������
     CheckedOutputStream cs = 
        new CheckedOutputStream(f,new Adler32());
      //�������zip��
      ZipOutputStream out =
        new ZipOutputStream(new BufferedOutputStream(cs));
      //дһ��ע��
      out.setComment("A test of Java Zipping");
      //�Զ��ļ�����ѹ��
      for(int i=1;i<fs.length;i++){
        System.out.println("Write file "+fs[i]);
        BufferedReader in =
           new BufferedReader(
             new FileReader(fs[i]));
         out.putNextEntry(new ZipEntry(fs[i]));
         int c;
         while((c=in.read())!=-1)
          out.write(c);
        in.close();
       }
       //�ر������
       out.close();
       System.out.println("Checksum::"+cs.getChecksum().getValue());
    }catch(Exception e){
       System.err.println(e);
    }
  }

/**
 *<br>����˵������ѹ��Zip�ļ�
 *<br>���������String fileName ��ѹzip�ļ���
 *<br>�������ͣ�
 */
  public void unZipFile(String fileName){
    try{
       System.out.println("��ȡZIP�ļ�........");
       //�ļ�������
       FileInputStream fi =
         new FileInputStream(fileName+".zip");
       //���������
       CheckedInputStream csi = new CheckedInputStream(fi,new Adler32());
       //������ѹ��
       ZipInputStream in2 =
         new ZipInputStream(
           new BufferedInputStream(csi));
       ZipEntry ze;
       System.out.println("Checksum::"+csi.getChecksum().getValue());
       //��ѹȫ���ļ�
       while((ze = in2.getNextEntry())!=null){
         System.out.println("Reading file "+ze);
         int x;
         while((x= in2.read())!=-1)
           //������д�ļ���write����byte��ʽ�����
           System.out.write(x);
       }
       in2.close();
    }catch(Exception e){
      System.err.println(e);
    }
  }
/**
 *<br>����˵������ȡZip�ļ��б�
 *<br>���������String fileName zip�ļ���
 *<br>�������ͣ�Vector �ļ��б�
 */
  public Vector listFile(String fileName){
    try{
       String[] aRst=null;
       Vector vTemp = new Vector();
       //zip�ļ�����
       ZipFile zf = new ZipFile(fileName+".zip");
       Enumeration e = zf.entries();
       while(e.hasMoreElements()){
         ZipEntry ze2 = (ZipEntry)e.nextElement();
         System.out.println("File: "+ze2);
         vTemp.addElement(ze2);
       }
       return  vTemp;
    }catch(Exception e){
      System.err.println(e);
      return null;
    }
  }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
  public static void main(String[] args){
    try{
     String fileName = args[0];
     myZip myZip = new myZip();
     myZip.ZipFiles(args);
     myZip.unZipFile(fileName);
     Vector dd = myZip.listFile(fileName);
     System.out.println("File List: "+dd);
    }catch(Exception e){
    	e.printStackTrace();
    }
  }
}
