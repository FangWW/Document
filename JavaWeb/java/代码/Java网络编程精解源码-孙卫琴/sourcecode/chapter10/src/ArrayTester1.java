import java.lang.reflect.*;
public class ArrayTester1 {
  public static void main(String args[])throws Exception {
    Class classType = Class.forName("java.lang.String");
    //创建一个长度为10的字符串数组
    Object array = Array.newInstance(classType, 10);
    //把索引位置为5的元素设为"hello"
    Array.set(array, 5, "hello");
    //获得索引位置为5的元素的值
    String s = (String) Array.get(array, 5);
    System.out.println(s);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
