package hello;
public class HelloClient{
  static HelloService helloServiceImpl;

  public static void main(String args[]){
    try{
      //�����ͳ�ʼ��ORB
      ORB orb = ORB.init(args, null);

      //������������Context
      org.omg.CORBA.Object objRef = 
	    orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
 
      //�����Ϊ��HelloService����HelloService�����Զ������
      String name = "HelloService";
      helloServiceImpl = HelloServiceHelper.narrow(ncRef.resolve_str(name));
      
      //����HelloService�����Զ�̷���
      System.out.println("Obtained a handle on server object: " + helloServiceImpl);
      System.out.println(helloServiceImpl.sayHello());
      helloServiceImpl.shutdown();
    }catch (Exception e) {
      System.out.println("ERROR : " + e) ;
      e.printStackTrace(System.out);
    }
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
