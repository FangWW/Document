import java.net.*;
import java.nio.*;
import java.nio.charset.*;
import java.util.regex.*;
/* 代表客户的HTTP请求 */
public class Request {
  static class Action {  //枚举类，表示HTTP请求方式
    private String name;
    private Action(String name) { this.name = name; }
    public String toString() { return name; }

    static Action GET = new Action("GET");
    static Action PUT = new Action("PUT");
    static Action POST = new Action("POST");
    static Action HEAD = new Action("HEAD");

    public static Action parse(String s) {
        if (s.equals("GET"))
            return GET;
        if (s.equals("PUT"))
            return PUT;
        if (s.equals("POST"))
            return POST;
        if (s.equals("HEAD"))
            return HEAD;
        throw new IllegalArgumentException(s);
    }
  }

  private Action action;
  private String version;
  private URI uri;

  public Action action() { return action; }
  public String version() { return version; }
  public URI uri() { return uri; }

  private Request(Action a, String v, URI u) {
    action = a;
    version = v;
    uri = u;
  }

  public String toString() {
    return (action + " " + version + " " + uri);
  }

  private static Charset requestCharset = Charset.forName("GBK");

  /* 判断ByteBuffer是否包含了HTTP请求的所有数据。
   * HTTP请求以“\r\n\r\n”结尾。
   */
  public static boolean isComplete(ByteBuffer bb) {
    ByteBuffer temp=bb.asReadOnlyBuffer();
    temp.flip();
    String data=requestCharset.decode(temp).toString();
    if(data.indexOf("\r\n\r\n")!=-1){
      return true;
    }
    return false;
  }

  /*
   * 删除请求正文，本例子仅支持GET和HEAD请求方式，忽略HTTP请求中的正文部分
   */
  private static ByteBuffer deleteContent(ByteBuffer bb) {
    ByteBuffer temp=bb.asReadOnlyBuffer();
    String data=requestCharset.decode(temp).toString();
    if(data.indexOf("\r\n\r\n")!=-1){
        data=data.substring(0,data.indexOf("\r\n\r\n")+4);
        return requestCharset.encode(data);
    }
    return bb;
  }

  /*
   * 设定用于解析HTTP请求的字符串匹配模式。对于以下形式的HTTP请求：
   *
   *     GET /dir/file HTTP/1.1
   *     Host: hostname
   *
   * 将被解析成:
   *
   *     group[1] = "GET"
   *     group[2] = "/dir/file"
   *     group[3] = "1.1"
   *     group[4] = "hostname"
   */
  private static Pattern requestPattern
      = Pattern.compile("\\A([A-Z]+) +([^ ]+) +HTTP/([0-9\\.]+)$"
                        + ".*^Host: ([^ ]+)$.*\r\n\r\n\\z",
                        Pattern.MULTILINE | Pattern.DOTALL);

  /* 解析HTTP请求，创建相应的Request对象 */
  public static Request parse(ByteBuffer bb) throws MalformedRequestException {
    bb=deleteContent(bb); //删除请求正文
    CharBuffer cb = requestCharset.decode(bb); //解码
    Matcher m = requestPattern.matcher(cb);  //进行字符串匹配
    //如果HTTP请求与指定的字符串模式不匹配，说明请求数据不正确
    if (!m.matches())
        throw new MalformedRequestException();
    Action a;
    try {  //获得请求方式
        a = Action.parse(m.group(1));
     } catch (IllegalArgumentException x) {
        throw new MalformedRequestException();
    }
    URI u;
    try { //获得URI
        u = new URI("http://"
                    + m.group(4)
                    + m.group(2));
    } catch (URISyntaxException x) {
        throw new MalformedRequestException();
    }
    //创建一个Request对象，并将其返回
    return new Request(a, m.group(3), u);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
