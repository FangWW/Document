/**
 * <p>Title: 实现Runnable接口，获得线程。</p>
 * <p>Description: 通过实现Runnable接口来获得自己的线程（t2）。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: twothread.java</p>
 * @author 杜江
 * @version 1.0
 */
public class twothread implements Runnable {
/**
 *<br>方法说明：构造器。实际线程，并启动这个线程。
 *<br>输入参数：
 *<br>返回类型：
 */
 twothread() { 
   //获取当前的线程
   Thread t1 =Thread.currentThread(); 
   t1.setName("The first main thread"); 
   System.out.println("The running thread:" + t1); 
   //通过将本类（Runnable接口）和名称构造一个线程
   Thread t2 = new Thread(this,"the second thread"); 
   System.out.println("creat another thread"); 
   //启动线程
   t2.start(); 
   try { 
     System.out.println("first thread will sleep"); 
     Thread.sleep(3000); 
   }catch (InterruptedException e) {
     System.out.println("first thread has wrong"); 
   } 
   System.out.println("first thread exit"); 
 } 
/**
 *<br>方法说明：线程主体。实现run方法。
 *<br>输入参数：
 *<br>返回类型：
 */
 public void run() { 
   try { 
     for (int i=0;i<5;i++) { 
       System.out.println("Sleep time for thread 2:"+i); 
       Thread.sleep(1000); 
     }
  } catch (InterruptedException e) {
    System.out.println("thread has wrong"); 
  }
  System.out.println("second thread exit"); 
 } 
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
 public static void main(String args[]) { 
   new twothread(); 
 } 
}

