// @version 1.0 
import java.io.*;

public class Customer5 implements Serializable {
  private String name;
  private int age;
 
  public Customer5(String name,int age) {
    this.name=name;
    this.age=age;
  }

  public String toString() {
    return "name="+name+" age="+age;
  }
}

/*
// @version 2.0 
import java.io.*;
public class Customer5 implements Serializable {
  //private static final long serialVersionUID=-1443651131474384429L;
  private String name;
  private boolean isMarried;
 
  public Customer5(String name,boolean isMarried) {
    this.name=name;
    this.isMarried=isMarried;
  }

  public String toString() {
    return "name="+name+" isMarried="+isMarried;
  }
}
*/


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
