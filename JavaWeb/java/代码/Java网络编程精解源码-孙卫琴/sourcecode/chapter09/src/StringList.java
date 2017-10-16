import java.io.*;
public class StringList implements Serializable{
  private int size=0;
  private Node head=null;
  private Node end=null;
  
  private static class Node implements Serializable{ //Node类也必须实现Serializable接口
    String data;
    Node next;
    Node previous;
  }
  
  /** 在列表末尾加入一个字符串 */
  public void add(String data){
    Node node=new Node();
    node.data=data;
    node.next=null;
    node.previous=end;
    if(end!=null)end.next=node;
    size++;
    end=node;
    if(size==1)head=end;
  }	
  
  /** 读取列表中指定位置的一个字符串 */
  public String get(int index){
    if(index>=size)return null;
    Node node=head;
    for(int i=1;i<=index;i++){
      node=node.next;
    }
    return node.data;
  }
  
  public int size(){return size;}

  public static void main(String args[])throws Exception{
    StringList list=new StringList();
    //向列表中加入1500个字符串 
    for(int i=0;i<1500;i++)list.add("hello"+i);

    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    
    //把StringList对象序列化到一个字节缓存中
    ObjectOutputStream o =new ObjectOutputStream(buf);
    o.writeObject(list);
    
    //从字节缓存中反序列化StringList对象
    ObjectInputStream in =new ObjectInputStream(
       new ByteArrayInputStream(buf.toByteArray()));
    list= (StringList)in.readObject();
    System.out.println("After Serialization:" + list.size());
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
