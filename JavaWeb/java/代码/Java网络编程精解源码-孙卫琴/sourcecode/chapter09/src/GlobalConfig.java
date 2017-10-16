import java.io.*;
import java.util.*;
public class GlobalConfig implements Serializable{
  private static final GlobalConfig INSTANCE=new GlobalConfig();
  private Properties properties = new Properties();
  private GlobalConfig(){
    try{
      //加载配置信息
      InputStream in=getClass().getResourceAsStream("myapp.properties");
      properties.load(in);
      in.close();
    }catch(IOException e){throw new RuntimeException("加载配置信息失败");}
  }
  public static GlobalConfig getInstance(){  //静态工厂方法
    return INSTANCE;
  }
  public Properties getProperties() {
    return properties;
  }
  
  private Object readResolve() throws ObjectStreamException{
    return INSTANCE;
  }

  public static void main(String args[])throws Exception{
    GlobalConfig config=GlobalConfig.getInstance();

    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    
    //把GlobalConfig对象序列化到一个字节缓存中
    ObjectOutputStream o =new ObjectOutputStream(buf);
    o.writeObject(config);
    
    //从字节缓存中反序列化GlobalConfig对象
    ObjectInputStream in =new ObjectInputStream(
       new ByteArrayInputStream(buf.toByteArray()));
    GlobalConfig configNew= (GlobalConfig)in.readObject();
    System.out.println("config==configNew:"+(config==configNew));
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
