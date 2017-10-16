import java.lang.reflect.*;
public class InvokeTester {
  public int add(int param1,int param2){
    return param1+param2;
  }
  public String echo(String msg){
    return "echo:"+msg;
  }
  public static void main(String[] args) throws Exception{
    Class classType=InvokeTester.class;
    Object invokeTester=classType.newInstance();
    
    //调用InvokeTester对象的add()方法
    Method addMethod=classType.getMethod("add",new Class[]{int.class,int.class});
    Object result=addMethod.invoke(invokeTester,new Object[]{new Integer(100),new Integer(200)});
    System.out.println((Integer)result);

    //调用InvokeTester对象的echo()方法
    Method echoMethod=classType.getMethod("echo",new Class[]{String.class});
    result=echoMethod.invoke(invokeTester,new Object[]{"Hello"});
    System.out.println((String)result);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
