package store;
public class StoreException extends Exception{
  public StoreException() {
    this("StoreException");
  }
  public StoreException(String msg) {
    super(msg);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
