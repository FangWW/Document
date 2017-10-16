/**
 * <p>Title: 线程间合作</p>
 * <p>Description: 本实例使用二个线程共同合作绘制一个实体三角。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: mainThread.java</p>
 * @author 杜江
 * @version 1.0
 */
public class mainThread{
  public static int flag = 0;
  int count = 10;
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void main(String[] arg){
    new mainThread();
  }
/**
 *<br>方法说明：构造器，启动两个子线程。
 *<br>输入参数：
 *<br>返回类型：
 */
  mainThread(){
    thread1 t1 = new mainThread.thread1(this.count);
    thread2 t2 = new mainThread.thread2(this.count);
    //启动两线程
    t1.start();
    t2.start();
    //让线程一首先工作。
    flag = 1;
  }
/**
 *<br>类说明：内部类，继承了Thread，
 *<br>类描述：实现了在输出每行前面的空格。
 */
  class thread1 extends Thread{
    int count1 = 0; 
    thread1(int i){
      count1 = i;
    }
    public void run(){
      
      while(true){
      	if(count1<=0) break;
      	if(mainThread.flag==1){
         
         for(int i=0;i<count1;i++){
           System.out.print(" ");
         }
         count1--;
         mainThread.flag=2;
        }
      }
    }
  }
/**
 *<br>类说明：内部类，继承了Thread，
 *<br>类描述：实现了在输出每行第“*”号。并提供换行。
 */
  class thread2 extends Thread{
    int count2 = 0; 
    thread2(int i){
      count2 = i;
    }
    public void run(){
      int count = 0;
      while(true){
        if(count>=count2) break;
        if(mainThread.flag==2){
         for(int i=0;i<(count*2+1);i++){
           System.out.print("*");
         }
         System.out.print("\n");
         count++;
         mainThread.flag=1;
        }
      }
    }
  } 
}