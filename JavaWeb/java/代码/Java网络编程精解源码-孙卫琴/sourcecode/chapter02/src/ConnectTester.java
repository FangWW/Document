public class ConnectTester {
  public static void main(String args[]){
    String host="localhost";
    int port=25;
    if(args.length>1){
        host = args[0];
        port=Integer.parseInt(args[1]);
    }
    new ConnectTester().connect(host,port);
  }
  public void connect(String host,int port){
    SocketAddress remoteAddr=new InetSocketAddress(host,port);
    Socket socket=null;
    String result="";
    try {
        long begin=System.currentTimeMillis();
        socket = new Socket();
        socket.connect(remoteAddr,1000);  //��ʱʱ��Ϊ1����
        long end=System.currentTimeMillis();
        result=(end-begin)+"ms";  //��������������ʱ��
    }catch (BindException e) {
        result="Local address and port can't be binded";
    }catch (UnknownHostException e) {
        result="Unknown Host";
    }catch (ConnectException e) {
        result="Connection Refused";
    }catch (SocketTimeoutException e) {
        result="TimeOut";
    }catch (IOException e) {
        result="failure";
    } finally {
        try {
            if(socket!=null)socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    System.out.println(remoteAddr+" : "+result);
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
