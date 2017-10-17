public class Customer1 implements Serializable {
  private static int count; //���ڼ���Customer�������Ŀ
  private static final int MAX_COUNT=1000;
  private String name;
  private transient String password;
  
  static{
     System.out.println("����Customer1��ľ�̬�����");
  }
  public Customer1(){
    System.out.println("����Customer1��Ĳ��������Ĺ��췽��");
    count++;
  }
  public Customer1(String name, String password) {
    System.out.println("����Customer1��Ĵ������Ĺ��췽��");
    this.name=name;
    this.password=password;
    count++;
  }
  public String toString() {
    return "count="+count
           +" MAX_COUNT="+MAX_COUNT
           +" name="+name
           +" password="+ password;
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
