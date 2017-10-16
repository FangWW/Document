/**
 * <p>Title: 目录操作</p>
 * <p>Description: 演示列目录下的文件，和移动一个目录</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: Dir.java</p>
 * @author 杜江
 * @version 1.0
 */
import java.io.*;
public class Dir{
 /**
 *<br>方法说明：实现目录列表
 *<br>输入参数：
 *<br>返回类型：
 */ 
  public String[] DirList(String pathName){
    try{
      File path = null;
      String[] fileList;
      //如果没有指定目录，则列出当前目录。
      if(pathName.equals(""))
        path = new File(".");
      else
        path = new File(pathName);
      //获取目录文件列表
      if(path.isDirectory())
        fileList = path.list();
      else
        return null;
     return fileList;
    }catch(Exception e){
      System.err.println(e);
      return null;
    }
  }
/**
 *<br>方法说明：移动一个目录
 *<br>输入参数：String sou 源目录, String obj 新目录
 *<br>返回类型：
 */
  public boolean DirMove(String sou, String obj){
    try{
     //检查源文件是否存在
      boolean sexists = (new File(sou)).isDirectory();
      if(!sexists) return false;
      boolean oexists = (new File(obj)).isDirectory();
      //目标目录不存在，建立一个
      if(!oexists){
        (new File(obj)).mkdirs();
      }
   
        File file = new File(sou);
        File dir = new File(obj);
        //移动目录
        boolean success = file.renameTo(new File(dir, file.getName()));
        if (!success) {
         System.out.println("copy error!");
         return false;
        }
        else return true;
    }catch(Exception e){
    	System.out.println(e);
    	return false;
    }
    
  }

/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void main(String[] args){
     Dir d = new Dir();
    if(args.length==0){
      return;
    }else{
      String cmd = args[0];
      if(cmd.equals("list")){
      	if(args.length!=2) return;
        String[] sTemp = d.DirList(args[1]);
        for(int i=0;i<sTemp.length;i++)
          System.out.println(sTemp[i]);
      }else if(cmd.equals("move")){
      	if(args.length!=3) return;      	
        d.DirMove(args[1],args[2]);
      }
      
    }
   }
} 
