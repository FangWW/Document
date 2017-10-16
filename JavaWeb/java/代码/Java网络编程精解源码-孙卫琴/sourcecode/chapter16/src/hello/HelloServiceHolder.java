package hello;

/**
* hello/HelloServiceHolder.java .
* 由 IDL-to-Java 编译器（可移植），版本 "3.2" 生成
* 来自 HelloService.idl
* 2006年12月18日 星期一 下午09时14分13秒 CST
*/

public final class HelloServiceHolder implements org.omg.CORBA.portable.Streamable
{
  public hello.HelloService value = null;

  public HelloServiceHolder ()
  {
  }

  public HelloServiceHolder (hello.HelloService initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = hello.HelloServiceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    hello.HelloServiceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return hello.HelloServiceHelper.type ();
  }

}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
