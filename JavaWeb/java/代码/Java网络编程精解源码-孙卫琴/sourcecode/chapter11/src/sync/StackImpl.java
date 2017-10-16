package sync;
import java.rmi.*;
import java.rmi.server.*;

public class StackImpl extends UnicastRemoteObject implements Stack{ 
  private String name;
  private String[] buffer=new String[100];
  int point=-1;

  public StackImpl(String name)throws RemoteException{this.name=name;}
  public String getName()throws RemoteException{return name;}

  public synchronized int getPoint()throws RemoteException{return point;}
  public synchronized String pop() throws RemoteException{
      this.notifyAll();

      while(point==-1){
        System.out.println(Thread.currentThread().getName()+": wait");
        try{
          this.wait();
        }catch(InterruptedException e){throw new RuntimeException(e);}
      }

      String goods = buffer[point];
      buffer[point]=null;
      Thread.yield();
      point--;
      return goods;
  }
  public synchronized void push(String goods) throws RemoteException{  
     this.notifyAll();

     while(point==buffer.length-1){
       System.out.println(Thread.currentThread().getName()+": wait");
       try{
         this.wait();
       }catch(InterruptedException e){throw new RuntimeException(e);}
    }
    point++;
    Thread.yield();
    buffer[point]=goods;
  }
}




/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
