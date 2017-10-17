package nonblock;
class Target {  //��ʾһ������
  InetSocketAddress address;
  SocketChannel channel;
  Exception failure;
  long connectStart;  //��ʼ����ʱ��ʱ��
  long connectFinish = 0;  //���ӳɹ�ʱ��ʱ��
  boolean shown = false;  //�������Ƿ��Ѿ���ӡ

  Target(String host) {
      try {
          address = new InetSocketAddress(InetAddress.getByName(host),80);
      } catch (IOException x) {
          failure = x;
      }
  }

  void show() {  //��ӡ����ִ�еĽ��
      String result;
      if (connectFinish != 0)
          result = Long.toString(connectFinish - connectStart) + "ms";
      else if (failure != null)
          result = failure.toString();
      else
          result = "Timed out";
      System.out.println(address + " : " + result);
      shown = true;
  }
}

public class PingClient{
  private Selector selector;
  //����û����ύ������
  private LinkedList targets=new LinkedList();
  //����Ѿ���ɵ���Ҫ��ӡ������
  private LinkedList finishedTargets=new LinkedList();

  public PingClient()throws IOException{
    selector=Selector.open();
    Connector connector=new Connector();
    Printer printer=new Printer();
    connector.start();
    printer.start();
    receiveTarget();
  }

  public static void main(String args[])throws IOException{
    new PingClient();
  }
  public void addTarget(Target target) {
    //��targets�����м���һ������
     SocketChannel socketChannel = null;
      try {
          socketChannel = SocketChannel.open();
          socketChannel.configureBlocking(false);
          socketChannel.connect(target.address);

          target.channel = socketChannel;
          target.connectStart = System.currentTimeMillis();

           synchronized (targets) {
             targets.add(target);
           }
           selector.wakeup();
       } catch (Exception x) {
          if (socketChannel != null) {
              try {
                  socketChannel.close();
              } catch (IOException xx) { }
          }
          target.failure = x;
          addFinishedTarget(target);
      }
  }

  public void addFinishedTarget(Target target) {
      //��finishedTargets�����м���һ������
      synchronized (finishedTargets) {
      finishedTargets.notify();
      finishedTargets.add(target);
     }
  }

  public void printFinishedTargets() {
    //��ӡfinisedTargets�����е�����
     try {
        for (;;) {
            Target target = null;
            synchronized (finishedTargets) {
                while (finishedTargets.size() == 0)
                    finishedTargets.wait();
                target = (Target)finishedTargets.removeFirst();
            }
            target.show();
        }
    } catch (InterruptedException x) {
        return;
    }
  }

  public void registerTargets(){
    //ȡ��targets�����е�������Selectorע�����Ӿ����¼�
    synchronized (targets) {
      while (targets.size() > 0) {
        Target target = (Target)targets.removeFirst();

        try {
          target.channel.register(selector, SelectionKey.OP_CONNECT, target);
        } catch (IOException x) {
              try{target.channel.close();}catch(IOException e){e.printStackTrace();}
              target.failure = x;
              addFinishedTarget(target);
        }
      }
    }
  }

  public void processSelectedKeys() throws IOException {
    //�������Ӿ����¼�
    for (Iterator it = selector.selectedKeys().iterator(); it.hasNext();) {
      SelectionKey selectionKey = (SelectionKey)it.next();
      it.remove();

      Target target = (Target)selectionKey.attachment();
      SocketChannel socketChannel = (SocketChannel)selectionKey.channel();

      try {
          if (socketChannel.finishConnect()) {
              selectionKey.cancel();
              target.connectFinish = System.currentTimeMillis();
              socketChannel.close();
              addFinishedTarget(target);
          }
      } catch (IOException x) {
          socketChannel.close();
          target.failure = x;
          addFinishedTarget(target);
      }
    }
  }

  public void receiveTarget(){
    //�����û�����ĵ�ַ����targets�����м�������
    try{
      BufferedReader localReader=new BufferedReader(new InputStreamReader(System.in));
      String msg=null;
      while((msg=localReader.readLine())!=null){
        if(!msg.equals("bye")){
          Target target=new Target(msg);
          addTarget(target);
        }else{
          shutdown=true;
          selector.wakeup();
          break;
        }
      }
    }catch(IOException e){
       e.printStackTrace();
    }
  }

  boolean shutdown=false;

  public class Printer extends Thread{
    public Printer(){
        setDaemon(true);
    }
    public void run(){
        printFinishedTargets();
    }
  }

  public class Connector extends Thread{
    public void run(){
        while (!shutdown) {
            try {
                registerTargets();
                if (selector.select() > 0) {
                    processSelectedKeys();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
       }
       try{
           selector.close();
       }catch(IOException e){e.printStackTrace();}
    }
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
