import java.io.*;
import java.net.*;
/**
 * <p>Title: 客户请求分析</p>
 * <p>Description: 获取客户的HTTP请求，分析客户所需要的文件</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: Request.java</p>
 * @author 杜江
 * @version 1.0
 */
public class Request{
  InputStream in = null;
/**
 *<br>方法说明：构造器，获得输入流。这时客户的请求数据。
 *<br>输入参数：
 *<br>返回类型：
 */
  public Request(InputStream input){
    this.in = input;
  }
/**
 *<br>方法说明：解析客户的请求
 *<br>输入参数：
 *<br>返回类型：String 请求文件字符
 */
  public String parse() {
    //从Socket读取一组数据
    StringBuffer requestStr = new StringBuffer(2048);
    int i;
    byte[] buffer = new byte[2048];
    try {
        i = in.read(buffer);
    }
    catch (IOException e) {
        e.printStackTrace();
        i = -1;
    }
    for (int j=0; j<i; j++) {
        requestStr.append((char) buffer[j]);
    }
    System.out.print(requestStr.toString());
    return getUri(requestStr.toString());
  }
/**
 *<br>方法说明：获取URI字符
 *<br>输入参数：String requestString 请求字符
 *<br>返回类型：String URI信息字符
 */
  private String getUri(String requestString) {
    int index1, index2;
    index1 = requestString.indexOf(' ');
    if (index1 != -1) {
        index2 = requestString.indexOf(' ', index1 + 1);
        if (index2 > index1)
           return requestString.substring(index1 + 1, index2);
    }
    return null;
  }
}
