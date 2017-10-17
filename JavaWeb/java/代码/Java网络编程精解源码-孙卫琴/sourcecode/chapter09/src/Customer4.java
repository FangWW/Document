public class Customer4 implements Externalizable {
  private String name;
  private Set<Order4> orders=new HashSet<Order4>();
  static{
     System.out.println("����Customer4��ľ�̬�����");
  }
  public Customer4(){
    System.out.println("����Customer4��Ĳ��������Ĺ��췽��");
  }
  public Customer4(String name) {
    System.out.println("����Customer4��Ĵ������Ĺ��췽��");
    this.name=name;
  }
  
  public void addOrder(Order4 order){
    orders.add(order);
  }
  
  public void writeExternal(ObjectOutput out)throws IOException{
    out.writeObject(name);
    out.writeObject(orders);
  }
  public void readExternal(ObjectInput in)throws IOException,ClassNotFoundException{
    name=(String)in.readObject();
    orders=(Set<Order4>)in.readObject();
  }
  public String toString() {
    String result=super.toString()+"\r\n"
           +orders+"\r\n";
    return result;
  }
}

class Order4 implements Externalizable {
  private String number;
  private Customer4 customer;
  public Order4(){
    System.out.println("����Order4��Ĳ��������Ĺ��췽��");
  }
  public Order4(String number,Customer4 customer){
    System.out.println("����Order4��Ĵ������Ĺ��췽��");
    this.number=number;
    this.customer=customer; 
  }

    public void writeExternal(ObjectOutput out)throws IOException{
    out.writeObject(number);
    out.writeObject(customer);
  }
  public void readExternal(ObjectInput in)throws IOException,ClassNotFoundException{
    number=(String)in.readObject();
    customer=(Customer4)in.readObject();
  }

}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
