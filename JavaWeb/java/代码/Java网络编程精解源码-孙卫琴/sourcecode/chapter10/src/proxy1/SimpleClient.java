package proxy1;
public class SimpleClient {
  public static void main(String args[])throws Exception {
     //������̬������ʵ��
    HelloService helloService1=new HelloServiceProxy("localhost",8000);
    System.out.println(helloService1.echo("hello"));
    System.out.println(helloService1.getTime());

    //������̬������ʵ��
    HelloService helloService2=
         (HelloService)ProxyFactory.getProxy(HelloService.class,"localhost",8000);
    System.out.println(helloService2.echo("hello"));
    System.out.println(helloService2.getTime());
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
