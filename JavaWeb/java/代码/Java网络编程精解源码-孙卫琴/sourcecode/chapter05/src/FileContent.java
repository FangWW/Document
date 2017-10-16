import java.io.*;
import java.net.*;
import java.nio.channels.*;
import java.nio.charset.*;

/*文件形式的响应正文*/
public class FileContent implements Content {
  //假定文件的根目录为"root"
  private static File ROOT = new File("root");
  private File file;

  public FileContent(URI uri) {
    file = new File(ROOT,
                  uri.getPath()
                  .replace('/',File.separatorChar));
  }

  private String type = null;

  /* 确定文件类型 */
  public String type() {
    if (type != null) return type;
    String nm = file.getName();
    if (nm.endsWith(".html")|| nm.endsWith(".htm"))
        type = "text/html; charset=GBK";  //HTML网页
    else if ((nm.indexOf('.') < 0) || nm.endsWith(".txt"))
        type = "text/plain; charset=GBK";  //文本文件
    else
        type = "application/octet-stream";  //应用程序
    return type;
  }

  private FileChannel fileChannel = null;
  private long length = -1;  //文件长度
  private long position = -1; //文件的当前位置

  public long length() {
      return length;
  }

  /* 创建FileChannel对象*/
  public void prepare() throws IOException {
    if (fileChannel == null)
        fileChannel = new RandomAccessFile(file, "r").getChannel();
    length = fileChannel.size();
    position = 0;
  }

  /* 发送正文，如果发送完毕，就返回false，否则返回true */
  public boolean send(ChannelIO channelIO) throws IOException {
    if (fileChannel == null)
        throw new IllegalStateException();
    if (position < 0)
        throw new IllegalStateException();

    if (position >= length) {
        return false;  //如果发送完毕，就返回false
    }

    position += channelIO.transferTo(fileChannel, position, length - position);
    return (position < length);
  }

  public void release() throws IOException {
    if (fileChannel != null){
        fileChannel.close();  //关闭fileChannel
        fileChannel = null;
    }
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
