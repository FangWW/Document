package sync;
import javax.naming.*;
import java.rmi.*;

public class SimpleClient {
  public static void main(String args[]) throws Exception{
    String url="rmi://localhost/";
    Context namingContext=new InitialContext();
    Stack stack=(Stack)namingContext.lookup(url+"MyStack");
    Producer producer1 = new Producer(stack,"producer1");
    Producer producer2 = new Producer(stack,"producer2");
    Consumer consumer1 = new Consumer(stack,"consumer1");
  }
}

/**  生产者线程  */
class Producer extends Thread {
  private Stack theStack;

  public Producer (Stack s,String name) {
    super(name);
    theStack = s;
    start();  //启动自身生产者线程
  }

  public void run() {
    String goods;
    try{
      for (;;) {
        synchronized(theStack){
          goods="goods"+(theStack.getPoint()+1);
          theStack.push(goods);
        }
        System.out.println(getName()+ ": push " + goods +" to "+theStack.getName());
        try{Thread.sleep(500);}catch(InterruptedException e){
          throw new RuntimeException(e);
        }
      }
    }catch(RemoteException e){throw new RuntimeException(e);} 
  }
}

/**  消费者线程 */
class Consumer extends Thread {
  private Stack theStack;

  public Consumer (Stack s,String name) {
    super(name);
    theStack = s;  
    start();  //启动自身消费者线程
  }

  public void run() {
    String goods;
    try{
      for(;;) {
        goods = theStack.pop();
        System.out.println(getName() + ": pop " + goods +" from "+theStack.getName());
        try{Thread.sleep(400);}catch(InterruptedException e){
          throw new RuntimeException(e);
        }
      } 
    }catch(RemoteException e){throw new RuntimeException(e);}
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
