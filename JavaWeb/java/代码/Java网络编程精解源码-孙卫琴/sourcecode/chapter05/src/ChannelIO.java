import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class ChannelIO {
  protected SocketChannel socketChannel;
  protected ByteBuffer requestBuffer;
  private static int requestBufferSize = 4096;

  public ChannelIO(SocketChannel socketChannel, boolean blocking)
    throws IOException {
    this.socketChannel = socketChannel;
    socketChannel.configureBlocking(blocking); //设置阻塞模式
    requestBuffer = ByteBuffer.allocate(requestBufferSize);
  }

  public SocketChannel getSocketChannel() {
    return socketChannel;
  }

  /*
   * 如果原缓冲区的剩余容量不够，就创建一个新的缓冲区，容量为原来的两倍,
   * 把原来缓冲区的数据拷贝到新缓冲区
   */
  protected void resizeRequestBuffer(int remaining) {
    if (requestBuffer.remaining() < remaining) {
      // 把容量增大到原来的两倍
      ByteBuffer bb = ByteBuffer.allocate(requestBuffer.capacity() * 2);
      requestBuffer.flip();
      bb.put(requestBuffer);  //把原来缓冲区中的数据拷贝到新的缓冲区
      requestBuffer = bb;
    }
  }

  /*
   * 接收数据，把它们存放到requestBuffer中，如果requsetBuffer的剩余容量不足5%，
   * 就调用resizeRequestBuffer()方法扩充容量
   */
  public int read() throws IOException {
    resizeRequestBuffer(requestBufferSize/20);
    return socketChannel.read(requestBuffer);
  }

  /*
   * 返回requestBuffer，它存放了所有的请求数据
   */
  public ByteBuffer getReadBuf() {
      return requestBuffer;
  }

  /*
   * 发送参数指定的ByteBuffer中的数据
   */
  public int write(ByteBuffer src) throws IOException {
    return socketChannel.write(src);
  }

  /*
   * 把FileChannel中的数据写到SocketChannel中
   */
  public long transferTo(FileChannel fc, long pos, long len) throws IOException {
    return fc.transferTo(pos, len, socketChannel);
  }

  /*
   * 关闭SocketChannel
   */
  public void close() throws IOException {
    socketChannel.close();
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
