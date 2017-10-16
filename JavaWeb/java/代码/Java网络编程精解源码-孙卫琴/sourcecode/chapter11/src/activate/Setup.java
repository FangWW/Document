package activate;

import java.rmi.*;
import javax.naming.*;
import java.rmi.activation.*;
import java.io.File;
import java.util.Properties;

public class Setup{
  public static void main( String args[] ){
    try{
       Properties prop=new Properties();
       prop.put("java.security.policy",SimpleClient.class.getResource("server.policy").toString());
       ActivationGroupDesc group=new ActivationGroupDesc(prop,null);
        //注册ActivationGroup
       ActivationGroupID id=ActivationGroup.getSystem().registerGroup(group);
  
       String classURL=System.getProperty("java.rmi.server.codebase");
       MarshalledObject param1=new MarshalledObject("service1");
       MarshalledObject param2=new MarshalledObject("service2");

       ActivationDesc desc1=
new ActivationDesc(id,"activate.HelloServiceImpl",classURL,param1);
       ActivationDesc desc2=
new ActivationDesc(id,"activate.HelloServiceImpl",classURL,param2);
       //向rmid注册两个激活对象
       HelloService s1=(HelloService)Activatable.register(desc1);
       HelloService s2=(HelloService)Activatable.register(desc2);
       System.out.println(s1.getClass().getName());

       Context namingContext=new InitialContext();
       //向rmiregistry注册两个激活对象
       namingContext.rebind( "rmi:HelloService1", s1 );
       namingContext.rebind( "rmi:HelloService2", s2 );
    
       System.out.println( "服务器注册了两个可激活的 HelloService对象" );
    }catch(Exception e){
       e.printStackTrace();
    } 
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
