package hello;
import org.omg.PortableServer.POA;

public class HelloServer {
  public static void main(String args[]) {
    try{
      //�����ͳ�ʼ��ORB
      ORB orb = ORB.init(args, null);

      //��ø�POA�����ã����Ҽ���POAManager
      POA rootpoa =POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();

      //����һ��HelloServiceImpl���󣬲��Ұ�����ORB����
      HelloServiceImpl helloServiceImpl = new HelloServiceImpl();
      helloServiceImpl.setORB(orb); 

      //���HelloServiceImpl�����CORBA���͵Ķ�������
      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloServiceImpl);
      HelloService href = HelloServiceHelper.narrow(ref);
	  
      //������������Context
      org.omg.CORBA.Object objRef =
          orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      //��HelloService�����롰HelloService�����ְ�
      String name = "HelloService";
      NameComponent path[] = ncRef.to_name( name );
      ncRef.rebind(path, href);

      System.out.println("HelloServer ready and waiting ...");

      //�ȴ��ͻ��˷���HelloService����
      orb.run();
    }catch (Exception e) {
      System.err.println("ERROR: " + e);
      e.printStackTrace(System.out);
    }
    System.out.println("HelloServer Exiting ...");
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
