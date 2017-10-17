package store;
public class Customer implements Serializable {
  private long id;
  private String name="";
  private String addr="";
  private int age;
  public Customer(long id,String name,String addr,int age) {
    this.id=id;
    this.name=name;
    this.addr=addr;
    this.age=age;
  }
  
  public Customer(long id){
    this.id=id;
  }
  public Long getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public String getAddr(){
    return addr;
  }
  
  public int getAge(){
    return age;
  }

  public void setName(String name){
    this.name=name;
  }

  public void setAddr(String addr){
    this.addr=addr;
  }
  
  public void setAge(int age){
    this.age=age;
  }

  public String toString(){
    return "Customer: "+id+" "+name+" "+addr+" "+age;
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
