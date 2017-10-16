
/**
 * <p>Title: 线程组群</p>
 * <p>Description: 通过线程组管理下面的多个线程。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: myThreadgroup.java</p>
 * @author 杜江
 * @version 1.0
 */
public class myThreadgroup extends Thread {
  public static int flag=1;
  ThreadGroup tgA;
  ThreadGroup tgB;
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void main(String[] args){
    myThreadgroup dt = new myThreadgroup();
    //声明线程组A
    dt.tgA = new ThreadGroup("A");
    //声明线程组B
    dt.tgB = new ThreadGroup("B");
    for(int i=1;i<3;i++)
      new thread1(dt.tgA,i*1000,"one"+i);
    for(int i=1;i<3;i++)
      new thread1(dt.tgB,1000,"two"+i);
    //启动本线程和所有线程组
    dt.start();
  }
/**
 *<br>方法说明：覆盖run方法，控制线程组
 *<br>输入参数：
 *<br>返回类型：
 */
  public void run(){
    try{
     this.sleep(5000);
     this.tgB.checkAccess();
     //停止线程组B，
     this.tgB.stop();
     System.out.println("**************tgB stop!***********************");
     this.sleep(1000);
     //检查线程组A是否可以更改
     this.tgA.checkAccess();
     //停止线程组A
     this.tgA.stop();
     System.out.println("**************tgA stop!***********************");
     
    }catch(SecurityException es){
       System.out.println("**"+es);
    }catch(Exception e){
       System.out.println("::"+e);
    }
   }
}
/**
 * <p>Title: 线程类</p>
 * <p>Description: 通过构造器的参数，实现不同的线程</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: thread1.java</p>
 * @author 杜江
 * @version 1.0
 */
class thread1 extends Thread {
    int pauseTime;
    String name;
    public thread1(ThreadGroup g,int x, String n) {
        super(g,n);
        pauseTime = x;
        name = n;
        start();
    }
/**
 *<br>方法说明：必须覆盖的方法。
 *<br>输入参数：
 *<br>返回类型：
 */
   public void run () 
   {
     while(true) {
      try {
          System.out.print(name+"::::");
          this.getThreadGroup().list();//获取线程组信息
          Thread.sleep(pauseTime);
      } catch(Exception e) {
          System.out.println(e);
      }
    }
   }
}
