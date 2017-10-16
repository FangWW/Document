package hello;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.Properties;

public class HelloServer {
  public static void main(String args[]) {
    try{
      //创建和初始化ORB
      ORB orb = ORB.init(args, null);

      //获得根POA的引用，并且激活POAManager
      POA rootpoa =POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();

      //创建一个HelloServiceImpl对象，并且把它与ORB关联
      HelloServiceImpl helloServiceImpl = new HelloServiceImpl();
      helloServiceImpl.setORB(orb); 

      //获得HelloServiceImpl对象的CORBA类型的对象引用
      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloServiceImpl);
      HelloService href = HelloServiceHelper.narrow(ref);
	  
      //获得命名服务的Context
      org.omg.CORBA.Object objRef =
          orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      //把HelloService对象与“HelloService”名字绑定
      String name = "HelloService";
      NameComponent path[] = ncRef.to_name( name );
      ncRef.rebind(path, href);

      System.out.println("HelloServer ready and waiting ...");

      //等待客户端访问HelloService对象
      orb.run();
    }catch (Exception e) {
      System.err.println("ERROR: " + e);
      e.printStackTrace(System.out);
    }
    System.out.println("HelloServer Exiting ...");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
