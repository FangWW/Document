import java.io.*;
/**
 * <p>Title: 读取随机文件</p>
 * <p>Description: 演示使用RandomAccessFile类读取文件。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: RandFile.java</p>
 * @author 杜江
 * @version 1.0
 */
public class RandFile{
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void main(String[] args){
    String sFile;
    if(args.length<1){
      System.out.println("USE:java RandFile fileName");
      return;
    }else{
      sFile = args[0];
    }
    //接受IOException异常
    try{
      //构造随机访问文件，使用可读写方式。
      RandomAccessFile rf = new  RandomAccessFile(sFile, "rw");
      for(int i = 0; i < 10; i++)
      	rf.writeDouble(i*1.414);
      rf.close();
      //构造一个随机访问文件，使用只读方式
      rf = new RandomAccessFile(sFile, "rw");
      rf.seek(5*8);
      rf.writeDouble(47.0001);
      rf.close();
      //构造一个随机文件访问文件，使用只读方式。
      rf = new RandomAccessFile(sFile, "r");
      for(int i = 0; i < 10; i++)
       	System.out.println("Value " + i + ": " + rf.readDouble());
      rf.close();
     }catch(IOException e){
       System.out.println(e);
     }
  }
}
