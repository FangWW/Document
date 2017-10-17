public class SimpleClient {
  public void receive()throws Exception{
    Socket socket = new Socket("localhost",8000);
    InputStream in=socket.getInputStream();
    ObjectInputStream ois=new ObjectInputStream(in);
    Object object1=ois.readObject();
    Object object2=ois.readObject();
    System.out.println(object1);
    System.out.println(object2);
    System.out.println("object1��object2�Ƿ�Ϊͬһ������:"
                       +(object1==object2));
  }
  public static void main(String args[])throws Exception {
    new SimpleClient().receive(); 
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
