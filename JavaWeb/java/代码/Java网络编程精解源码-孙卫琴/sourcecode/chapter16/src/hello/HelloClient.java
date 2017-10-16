package hello;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class HelloClient{
  static HelloService helloServiceImpl;

  public static void main(String args[]){
    try{
      //创建和初始化ORB
      ORB orb = ORB.init(args, null);

      //获得命名服务的Context
      org.omg.CORBA.Object objRef = 
	    orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
 
      //获得名为“HelloService”的HelloService对象的远程引用
      String name = "HelloService";
      helloServiceImpl = HelloServiceHelper.narrow(ncRef.resolve_str(name));
      
      //调用HelloService对象的远程方法
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
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
