/**
 * <p>Title: 继承Thread，实现线程</p>
 * <p>Description: 通过继承Thread类，实现其run方法，实现自己的线程</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: oneThread.java</p>
 * @author 杜江
 * @version 1.0
 */
public class oneThread extends Thread {
/**
 *<br>方法说明：构造器，本类没有使用
 *<br>输入参数：
 *<br>返回类型：
 */
public oneThread() {
   
 }
/**
 *<br>方法说明：继承Thread类必须实现的方法，当调用start方法时运行本方法
 *<br>输入参数：
 *<br>返回类型：
 */
 public void run() {
  System.out.println("...............oneThread begining................");
  int flag = 0;
  while(true) {
  	if(flag==20){
  	  System.out.println("\n...............oneThread end............. ");
  	  break;
  	}
     for(int i=0;i<flag;i++)
       System.out.print("*");
     System.out.println("");
     flag++;
   try{
     //睡眠0.1秒
     sleep(100);
   }catch(Exception e){
   }   
  }
 }
/**
 *<br>方法说明：主方法。启动本线程
 *<br>输入参数：
 *<br>返回类型：
 */
 public static void main(String args[]) {
    new oneThread().start();
 }
}
