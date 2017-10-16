import java.util.*;
/**
 * <p>Title: 提高线程优先级</p>
 * <p>Description: 通过修改线程的优先级，是线程获得优先处理。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: upPRIThread.java</p>
 * @author 杜江
 * @version 1.0
 */
public class upPRIThread {
 //主方法
 public static void main(String[] args) throws Exception {
  Thread1 t1 = new Thread1();
  t1.start();
  Thread2 t2 = new Thread2();
  t2.start();
  t1.setPriority(Thread.MIN_PRIORITY);
  t2.setPriority(Thread.MIN_PRIORITY);
  new Thread().sleep(105);
  t2.setPriority(Thread.MAX_PRIORITY);
  new Thread().sleep(10500);
 }
 //类说明：线程1，不更改优先级
 static class Thread1 extends Thread {
  public void run(){
   while(true){
    try {
     Thread.sleep(100);
     } 
    catch (Exception e){
     e.printStackTrace();
     }
     System.out.println("我是线程111");
    }
   }
  }
 //类说明：线程2，提高优先级
 static class Thread2 extends Thread {

  public void run(){
   while(true){
    try {
     Thread.sleep(100);
     } 
    catch (Exception e){
     e.printStackTrace();
     }
     System.out.println("我是线程222.........................");
    }
   }
 }
}
