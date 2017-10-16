package com.bean;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 * <p>Title: 文件上传类</p>
 * <p>Description: 既能对文件进行上传,又能取得输入框的值,最多可同时上传255个文件</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author 杜江
 * @version 1.0
 */
public class MoqUploadBean {
  private String[] sourceFile = new String[255];     //源文件名
  private String[] suffix = new String[255];         //文件后缀名
  private String canSuffix = ".gif.jpg.jpeg.png";    //可上传的文件后缀名
  private String objectPath = "c:/";                 //目标文件目录
  private String[] objectFileName = new String[255]; //目标文件名
  private ServletInputStream sis = null;             //输入流
  private String[] description = new String[255];    //描述状态
  private long size = 100 * 1024;                    //限制大小
  private int count = 0;                             //已传输文件数目
  private byte[] b = new byte[4096];                 //字节流存放数组
  private boolean successful = true;
  private Hashtable fields = new Hashtable();
  public MoqUploadBean() {
  }
  //设置上传文件的后缀名
  public void setSuffix(String canSuffix) {
    this.canSuffix = canSuffix;
  }
  //设置文件保存路径
  public void setObjectPath(String objectPath) {
    this.objectPath = objectPath;
  }
  //从配置文件获取文件保存路径
  public void getUpLoadPath() {
    this.objectPath = "F:\\bea\\user_projects\\mydomain\\applications\\jspservlet\\upload\\";
  }
  //设置文件保存路径
  public void setSize(long maxSize) {
    this.size = maxSize;
  }
  //文件上传处理程序
  public void setSourceFile(HttpServletRequest request) throws IOException {
    //获取输入流
    sis = request.getInputStream();
    int a = 0;
    int k = 0;
    String s = "";
    while ( (a = sis.readLine(b, 0, b.length)) != -1) {
      s = new String(b, 0, a);
      if ( (k = s.indexOf("filename=\"")) != -1) {
      	
        // 取得文件数据
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
        // 普通表单输入元素，获取输入元素名字
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
  //取得表单元素值
  public String getFieldValue(String fieldName) {
    if (fields == null || fieldName == null) {
      return null;
    }
    return (String) fields.get(fieldName);
  }
  //取得上传文件数
  public int getCount() {
    return count;
  }
  //取得目标路径
  public String getObjectPath() {
    return objectPath;
  }
  //取得源文件名
  public String[] getSourceFile() {
    return sourceFile;
  }
  //取得目标文件名
  public String[] getObjectFileName() {
    return objectFileName;
  }
  //取得上传状态描述
  public String[] getDescription() {
    return description;
  }
  //判断上传文件的类型
  private boolean canTransfer(int i) {
    suffix[i] = suffix[i].toLowerCase();
    //这个是用来传图片的，各位可以把后缀名改掉或者不要这个条件
    if (sourceFile[i].equals("") || (!(canSuffix.indexOf("."+suffix[i])>=0))) {
      description[i] = "ERR: File suffix is wrong.";
      return false;
    }
    else {
      return true;
    }
  }
  //上传文件转换
  private void transferFile(int i) {
    String x = Long.toString(new java.util.Date().getTime());
    try {
      objectFileName[i] = x + "." + suffix[i];
      FileOutputStream out = new FileOutputStream(objectPath + objectFileName[i]);
      int a = 0;
      int k = 0;
      long hastransfered = 0; //标示已经传输的字节数
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

