import java.lang.reflect.*;
public class ReflectTester {
  public Object copy(Object object) throws Exception{
    //获得对象的类型
    Class classType=object.getClass();
    System.out.println("Class:"+classType.getName());

    //通过默认构造方法创建一个新的对象
    Object objectCopy=classType.getConstructor(new Class[]{}).newInstance(new Object[]{});

    //获得对象的所有属性
    Field fields[]=classType.getDeclaredFields();

    for(int i=0; i<fields.length;i++){
          Field field=fields[i];

          String fieldName=field.getName();
          String firstLetter=fieldName.substring(0,1).toUpperCase();
          //获得和属性对应的getXXX()方法的名字
          String getMethodName="get"+firstLetter+fieldName.substring(1);
          //获得和属性对应的setXXX()方法的名字
          String setMethodName="set"+firstLetter+fieldName.substring(1);

          //获得和属性对应的getXXX()方法
          Method getMethod=classType.getMethod(getMethodName,new Class[]{});
//获得和属性对应的setXXX()方法
          Method setMethod=classType.getMethod(setMethodName,new Class[]{field.getType()});

          //调用原对象的getXXX()方法
          Object value=getMethod.invoke(object,new Object[]{});
          System.out.println(fieldName+":"+value);
          //调用拷贝对象的setXXX()方法
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
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
