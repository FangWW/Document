package sync;
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

/**  �������߳�  */
class Producer extends Thread {
  private Stack theStack;

  public Producer (Stack s,String name) {
    super(name);
    theStack = s;
    start();  //���������������߳�
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

/**  �������߳� */
class Consumer extends Thread {
  private Stack theStack;

  public Consumer (Stack s,String name) {
    super(name);
    theStack = s;  
    start();  //���������������߳�
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
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
