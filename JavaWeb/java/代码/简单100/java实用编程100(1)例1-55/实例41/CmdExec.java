import java.io.*;
/**
 * <p>Title: 运行系统命令</p>
 * <p>Description:运行一个系统的命令，演示使用Runtime类。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: CmdExec.java</p>
 * @author 杜江
 * @version 1.0
 */
public class CmdExec {
/**
 *<br>方法说明：构造器，运行系统命令
 *<br>输入参数：String cmdline 命令字符
 *<br>返回类型：
 */
  public CmdExec(String cmdline) {
    try {
     String line;
     //运行系统命令
     Process p = Runtime.getRuntime().exec(cmdline);
     //使用缓存输入流获取屏幕输出。
     BufferedReader input = 
       new BufferedReader
         (new InputStreamReader(p.getInputStream()));
     //读取屏幕输出
     while ((line = input.readLine()) != null) {
       System.out.println("java print:"+line);
       }
     //关闭输入流
     input.close();
     } 
    catch (Exception err) {
     err.printStackTrace();
     }
   }
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
public static void main(String argv[]) {
   new CmdExec("myprog.bat");
  }
}

