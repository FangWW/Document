public class GlobalConfig implements Serializable{
  private static final GlobalConfig INSTANCE=new GlobalConfig();
  private Properties properties = new Properties();
  private GlobalConfig(){
    try{
      //����������Ϣ
      InputStream in=getClass().getResourceAsStream("myapp.properties");
      properties.load(in);
      in.close();
    }catch(IOException e){throw new RuntimeException("����������Ϣʧ��");}
  }
  public static GlobalConfig getInstance(){  //��̬��������
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
    
    //��GlobalConfig�������л���һ���ֽڻ�����
    ObjectOutputStream o =new ObjectOutputStream(buf);
    o.writeObject(config);
    
    //���ֽڻ����з����л�GlobalConfig����
    ObjectInputStream in =new ObjectInputStream(
       new ByteArrayInputStream(buf.toByteArray()));
    GlobalConfig configNew= (GlobalConfig)in.readObject();
    System.out.println("config==configNew:"+(config==configNew));
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
