package com.bean;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 * <p>Title: �ļ��ϴ���</p>
 * <p>Description: ���ܶ��ļ������ϴ�,����ȡ��������ֵ,����ͬʱ�ϴ�255���ļ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author �Ž�
 * @version 1.0
 */
public class MoqUploadBean {
  private String[] sourceFile = new String[255];     //Դ�ļ���
  private String[] suffix = new String[255];         //�ļ���׺��
  private String canSuffix = ".gif.jpg.jpeg.png";    //���ϴ����ļ���׺��
  private String objectPath = "c:/";                 //Ŀ���ļ�Ŀ¼
  private String[] objectFileName = new String[255]; //Ŀ���ļ���
  private ServletInputStream sis = null;             //������
  private String[] description = new String[255];    //����״̬
  private long size = 100 * 1024;                    //���ƴ�С
  private int count = 0;                             //�Ѵ����ļ���Ŀ
  private byte[] b = new byte[4096];                 //�ֽ����������
  private boolean successful = true;
  private Hashtable fields = new Hashtable();
  public MoqUploadBean() {
  }
  //�����ϴ��ļ��ĺ�׺��
  public void setSuffix(String canSuffix) {
    this.canSuffix = canSuffix;
  }
  //�����ļ�����·��
  public void setObjectPath(String objectPath) {
    this.objectPath = objectPath;
  }
  //�������ļ���ȡ�ļ�����·��
  public void getUpLoadPath() {
    this.objectPath = "F:\\bea\\user_projects\\mydomain\\applications\\jspservlet\\upload\\";
  }
  //�����ļ�����·��
  public void setSize(long maxSize) {
    this.size = maxSize;
  }
  //�ļ��ϴ��������
  public void setSourceFile(HttpServletRequest request) throws IOException {
    //��ȡ������
    sis = request.getInputStream();
    int a = 0;
    int k = 0;
    String s = "";
    while ( (a = sis.readLine(b, 0, b.length)) != -1) {
      s = new String(b, 0, a);
      if ( (k = s.indexOf("filename=\"")) != -1) {
      	
        // ȡ���ļ�����
        s = s.substring(k + 10);
        k = s.indexOf("\"");
        s = s.substring(0, k);
        sourceFile[count] = s;
        k = s.lastIndexOf(".");
        suffix[count] = s.substring(k + 1);
        if (canTransfer(count)) {
          transferFile(count);
        }
        ++count;
      } else if ( (k = s.indexOf("name=\"")) != -1) {
        // ��ͨ������Ԫ�أ���ȡ����Ԫ������
        String fieldName = s.substring(k+6, s.length()-3);
        sis.readLine(b, 0, b.length);
        String fieldValue = new String();
        while ( (a = sis.readLine(b, 0, b.length)) != -1) {
          s = new String(b, 0, a-2);
          if ( (b[0] == 45) && (b[1] == 45) && (b[2] == 45) && (b[3] == 45) && (b[4] == 45)) {
            break;
          } else {
              fieldValue+=s;
          }
        }
        fields.put(fieldName, fieldValue);
      }
      if (!successful)
        break;
    }
  }
  //ȡ�ñ�Ԫ��ֵ
  public String getFieldValue(String fieldName) {
    if (fields == null || fieldName == null) {
      return null;
    }
    return (String) fields.get(fieldName);
  }
  //ȡ���ϴ��ļ���
  public int getCount() {
    return count;
  }
  //ȡ��Ŀ��·��
  public String getObjectPath() {
    return objectPath;
  }
  //ȡ��Դ�ļ���
  public String[] getSourceFile() {
    return sourceFile;
  }
  //ȡ��Ŀ���ļ���
  public String[] getObjectFileName() {
    return objectFileName;
  }
  //ȡ���ϴ�״̬����
  public String[] getDescription() {
    return description;
  }
  //�ж��ϴ��ļ�������
  private boolean canTransfer(int i) {
    suffix[i] = suffix[i].toLowerCase();
    //�����������ͼƬ�ģ���λ���԰Ѻ�׺���ĵ����߲�Ҫ�������
    if (sourceFile[i].equals("") || (!(canSuffix.indexOf("."+suffix[i])>=0))) {
      description[i] = "ERR: File suffix is wrong.";
      return false;
    }
    else {
      return true;
    }
  }
  //�ϴ��ļ�ת��
  private void transferFile(int i) {
    String x = Long.toString(new java.util.Date().getTime());
    try {
      objectFileName[i] = x + "." + suffix[i];
      FileOutputStream out = new FileOutputStream(objectPath + objectFileName[i]);
      int a = 0;
      int k = 0;
      long hastransfered = 0; //��ʾ�Ѿ�������ֽ���
      String s = "";
      while ( (a = sis.readLine(b, 0, b.length)) != -1) {
        s = new String(b, 0, a);
        if ( (k = s.indexOf("Content-Type:")) != -1) {
          break;
        }
      }
      sis.readLine(b, 0, b.length);
      while ( (a = sis.readLine(b, 0, b.length)) != -1) {
        s = new String(b, 0, a);
        if ( (b[0] == 45) && (b[1] == 45) && (b[2] == 45) && (b[3] == 45) && (b[4] == 45)) {
          break;
        }
        out.write(b, 0, a);
        hastransfered += a;
        if (hastransfered >= size) {
          description[count] = "ERR: The file " + sourceFile[count] +
              " is too large to transfer. The whole process is interrupted.";
          successful = false;
          break;
        }
      }
      if (successful) {
        description[count] = "Right: The file " + sourceFile[count] +
            " has been transfered successfully.";
      }
      out.close();
      if (!successful) {
        sis.close();
        File tmp = new File(objectPath + objectFileName[count]);
        tmp.delete();
      }
    }
    catch (IOException ioe) {
      description[i] = ioe.toString();
    }
  }
  public static void main(String[] args) {
    System.out.println("Test OK");
  }
}

