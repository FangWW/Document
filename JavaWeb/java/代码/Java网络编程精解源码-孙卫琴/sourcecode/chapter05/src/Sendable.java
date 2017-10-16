import java.io.*;
/*
 *表示服务器可以发送给客户端的东西
 */
public interface Sendable {
  // 准备发送的内容
  public void prepare() throws IOException;

  // 利用通道发送部分内容，如果所有内容发送完毕，就返回false
  // 如果还有内容未发送，就返回true
  // 如果内容还没有准备好，就抛出IllegalStateException
  public boolean send(ChannelIO cio) throws IOException;

  //当服务器发送内容完毕，就调用此方法，释放内容占用的资源
  public void release() throws IOException;
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
