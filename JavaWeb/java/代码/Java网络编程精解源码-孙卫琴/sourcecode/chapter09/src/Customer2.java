public class Customer2 implements Serializable {
  private String name;
  private Set<Order2> orders=new HashSet<Order2>();
  static{
     System.out.println("����Customer2��ľ�̬�����");
  }
  public Customer2(){
    System.out.println("����Customer2��Ĳ��������Ĺ��췽��");
  }
  public Customer2(String name) {
    System.out.println("����Customer2��Ĵ������Ĺ��췽��");
    this.name=name;
  }
  
  public void addOrder(Order2 order){
    orders.add(order);
  }

  public String toString() {
    String result=super.toString()+"\r\n"
           +orders+"\r\n";
    return result;
  }
}

class Order2 implements Serializable {
  private String number;
  private Customer2 customer;
  public Order2(){
    System.out.println("����Order2��Ĳ��������Ĺ��췽��");
  }
  public Order2(String number,Customer2 customer){
    System.out.println("����Order2��Ĵ������Ĺ��췽��");
    this.number=number;
    this.customer=customer; 
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
