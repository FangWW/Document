package hello;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.Properties;

public class HelloServiceImpl extends HelloServicePOA {
  private ORB orb;

  public void setORB(ORB orb_val) {
    orb = orb_val; 
  }
    
  public String sayHello() {
    return "\nHello world !!\n";
  }
    
  public void shutdown() {
    orb.shutdown(false);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
