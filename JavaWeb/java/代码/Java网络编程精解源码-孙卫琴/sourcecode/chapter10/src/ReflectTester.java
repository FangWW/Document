public class ReflectTester {
  public Object copy(Object object) throws Exception{
    //��ö��������
    Class classType=object.getClass();
    System.out.println("Class:"+classType.getName());

    //ͨ��Ĭ�Ϲ��췽������һ���µĶ���
    Object objectCopy=classType.getConstructor(new Class[]{}).newInstance(new Object[]{});

    //��ö������������
    Field fields[]=classType.getDeclaredFields();

    for(int i=0; i<fields.length;i++){
          Field field=fields[i];

          String fieldName=field.getName();
          String firstLetter=fieldName.substring(0,1).toUpperCase();
          //��ú����Զ�Ӧ��getXXX()����������
          String getMethodName="get"+firstLetter+fieldName.substring(1);
          //��ú����Զ�Ӧ��setXXX()����������
          String setMethodName="set"+firstLetter+fieldName.substring(1);

          //��ú����Զ�Ӧ��getXXX()����
          Method getMethod=classType.getMethod(getMethodName,new Class[]{});
//��ú����Զ�Ӧ��setXXX()����
          Method setMethod=classType.getMethod(setMethodName,new Class[]{field.getType()});

          //����ԭ�����getXXX()����
          Object value=getMethod.invoke(object,new Object[]{});
          System.out.println(fieldName+":"+value);
          //���ÿ��������setXXX()����
         setMethod.invoke(objectCopy,new Object[]{value});
    }
    return objectCopy;
  }

  public static void main(String[] args) throws Exception{
    Customer customer=new Customer("Tom",21);
    customer.setId(new Long(1));

    Customer customerCopy=(Customer)new ReflectTester().copy(customer);
    System.out.println("Copy information:"+customerCopy.getName()+" "+customerCopy.getAge());
  }
}

class Customer{
  private Long id;
  private String name;
  private int age;
  
  public Customer(){}
  public Customer(String name,int age){
    this.name=name;
    this.age=age;
  }
  
  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
  
  public String getName(){return name;}
  public void setName(String name){this.name=name;}
  
  public int getAge(){return age;}
  public void setAge(int age){this.age=age;}
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
