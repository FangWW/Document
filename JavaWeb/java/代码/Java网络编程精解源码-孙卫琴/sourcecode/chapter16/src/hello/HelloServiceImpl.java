package hello;
public class HelloServiceImpl extends HelloServicePOA {
  private ORB orb;

  public void setORB(ORB orb_val) {
    orb = orb_val; 
  }
    
  public String sayHello() {
    return "\nHello world !!\n";
  }
    
  public void shutdown() {
    orb.shutdown(false);
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
