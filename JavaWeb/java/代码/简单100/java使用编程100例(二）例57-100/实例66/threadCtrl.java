/**
 * <p>Title: 线程控制</p>
 * <p>Description: 实现对线程的控制，中断、挂起、恢复、停止</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: threadCtrl.java</p>
 * @author 杜江
 * @version 1.0
 */
public class threadCtrl{
  public static void main(String [] main){
     new threadCtrl();
  }
/**
 *<br>方法说明：构造器，控制其它线程
 *<br>输入参数：
 *<br>返回类型：
 */
  threadCtrl(){
    try{
     Thread tm = Thread.currentThread();
     threadchild td = new threadchild();
     td.start();
     tm.sleep(500);
     System.out.println("interrupt child thread");
     td.interrupt();

     System.out.println("let child thread wait!");
     //td.wait();
     //td.suspend();
     tm.sleep(1000);

     System.out.println("let child thread working");
     td.fauxresume();
     //td.resume();
     tm.sleep(1000);
     td.runflag = false;
     System.out.println("main over..............");
   }catch(InterruptedException ie){
   	 System.out.println("inter main::"+ie);
   }catch(Exception e){
     System.out.println("main::"+e);
   }
  }

}
/**
 *<br>类说明：被控线程类
 */
  class threadchild extends Thread {
    boolean runflag = true;
    boolean suspended = true;
    threadchild(){
    }
    public synchronized void fauxresume(){
      suspended = false;
      notify();
    } 
    public  void run(){
      while(runflag){
        System.out.println("I am working..............");
        try{
          sleep(1000);
        }catch(InterruptedException e){
          System.out.println("sleep::"+e);
        }
       synchronized(this){
        try{
          if(suspended)
           wait();
        }catch(InterruptedException e){
          System.out.println("wait::"+e);
        }
       }
      }
      System.out.println("thread over...........");
    }
  }