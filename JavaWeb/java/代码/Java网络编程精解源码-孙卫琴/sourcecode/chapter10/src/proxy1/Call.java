package proxy1;
import java.io.*;
public class Call implements Serializable{
  private String className;  //表示类名
  private String methodName; //表示方法名
  private Class[] paramTypes; //表示方法参数类型
  private Object[] params; //表示方法参数值
  private Object result;  //表示方法的返回值或者方法抛出的异常
  
  public Call(){}
  public Call(String className,String methodName,Class[] paramTypes,
                            Object[] params){
    this.className=className;
    this.methodName=methodName;
    this.paramTypes=paramTypes;
    this.params=params;
  }
  
  public String getClassName(){return className;}
  public void setClassName(String className){this.className=className;}

  public String getMethodName(){return methodName;}
  public void setMethodName(String methodName){this.methodName=methodName;}

  public Class[] getParamTypes(){return paramTypes;}
  public void setParamTypes(Class[] paramTypes){this.paramTypes=paramTypes;}

  public Object[] getParams(){return params;}
  public void setParams(Object[] params){this.params=params;}

  public Object getResult(){return result;}
  public void setResult(Object result){this.result=result;}

  public String toString(){
    return "className="+className+" methodName="+methodName;
  }
} 


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
