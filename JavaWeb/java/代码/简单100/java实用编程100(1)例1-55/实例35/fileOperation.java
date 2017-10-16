import java.io.*;
import java.util.*;
/**
 * <p>Title: 文件操作</p>
 * <p>Description: 演示文件的删除和获取文件的信息</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author 杜江
 * @version 1.0
 */
public class fileOperation{
/**
 *<br>方法说明：删除文件
 *<br>输入参数：String fileName 要删除的文件名
 *<br>返回类型：boolean 成功为true
 */
  public boolean delFile(String fileName){
  	try{
  	  //删除文件
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
 *<br>方法说明：获取文件信息
 *<br>输入参数：String Name 文件名
 *<br>返回类型：String[] 文件信息数组
 */
  public String[] getFileInfo(String Name){
    try{
      File file = new File(Name);
      //获取文件修改日期（返回的是句）
      long modifiedTime = file.lastModified();
      //获取文件长度（单位：Bite）
      long filesize = file.length();
      //测试文件是否可读
      boolean cr = file.canRead();
      //测试文件是否可写
      boolean cw = file.canWrite();
      //测试文件是否隐藏
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
 *<br>方法说明：将毫秒数字转换为日期
 *<br>输入参数：mill    毫秒数
 *<br>返回类型：String 字符 格式为：yyyy-mm-dd hh:mm
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
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
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