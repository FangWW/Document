package store;
import java.util.*;
import java.io.*;

public class PropertyReader {
  static private Properties ps;

  static{
    ps=new Properties();
    try{
      InputStream in=PropertyReader.class.getResourceAsStream("db.conf");
      ps.load(in);
      in.close();
    }catch(Exception e){e.printStackTrace();}
  }

  public static String get(String key){
     return (String)ps.get(key);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
