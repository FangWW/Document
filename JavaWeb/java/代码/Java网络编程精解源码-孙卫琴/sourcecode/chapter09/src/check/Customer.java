package check;
public class Customer implements Serializable {
  private int age;
  public Customer(int age) {
    //�Ϸ��Լ��
    if(age<0)
      throw new IllegalArgumentException("���䲻��С����");
    this.age=age;
  }
  public String toString() {
    return "age="+age;
  }
  
  private void readObject(ObjectInputStream stream)
          throws IOException, ClassNotFoundException {
    stream.defaultReadObject();  //�Ȱ�Ĭ�Ϸ�ʽ�����л�
       //�Ϸ��Լ��
    if(age<0)
      throw new IllegalArgumentException("���䲻��С����");
  }
  
  public static void main(String[] args) throws Exception{
    Customer customer = new Customer(25);
    System.out.println("Before Serialization:" + customer);
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
   
    //��Customer�������л���һ���ֽڻ�����
    ObjectOutputStream o =new ObjectOutputStream(buf);
    o.writeObject(customer);
    
    
    byte[] byteArray=buf.toByteArray(); 
    for(int i=0;i<byteArray.length;i++){
      System.out.print(byteArray[i]+" ");
      if((i % 10==0 && i!=0) || i==byteArray.length-1) System.out.println();
    }
    
    
    //�۸����л�����
    byteArray[byteArray.length-4]=-1;
    byteArray[byteArray.length-3]=-1;
    byteArray[byteArray.length-2]=-1;
    byteArray[byteArray.length-1]=-10;
   
    //���ֽڻ����з����л�Customer����
    ObjectInputStream in =new ObjectInputStream(
          new ByteArrayInputStream(byteArray));
    customer= (Customer)in.readObject();
    System.out.println("After Serialization:" + customer);
   }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
