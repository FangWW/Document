package store;
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
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
