package activate;

import java.util.Properties;

public class Setup{
  public static void main( String args[] ){
    try{
       Properties prop=new Properties();
       prop.put("java.security.policy",SimpleClient.class.getResource("server.policy").toString());
       ActivationGroupDesc group=new ActivationGroupDesc(prop,null);
        //ע��ActivationGroup
       ActivationGroupID id=ActivationGroup.getSystem().registerGroup(group);
  
       String classURL=System.getProperty("java.rmi.server.codebase");
       MarshalledObject param1=new MarshalledObject("service1");
       MarshalledObject param2=new MarshalledObject("service2");

       ActivationDesc desc1=
new ActivationDesc(id,"activate.HelloServiceImpl",classURL,param1);
       ActivationDesc desc2=
new ActivationDesc(id,"activate.HelloServiceImpl",classURL,param2);
       //��rmidע�������������
       HelloService s1=(HelloService)Activatable.register(desc1);
       HelloService s2=(HelloService)Activatable.register(desc2);
       System.out.println(s1.getClass().getName());

       Context namingContext=new InitialContext();
       //��rmiregistryע�������������
       namingContext.rebind( "rmi:HelloService1", s1 );
       namingContext.rebind( "rmi:HelloService2", s2 );
    
       System.out.println( "������ע���������ɼ���� HelloService����" );
    }catch(Exception e){
       e.printStackTrace();
    } 
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
