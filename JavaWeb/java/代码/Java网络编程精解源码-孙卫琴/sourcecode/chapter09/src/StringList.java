public class StringList implements Serializable{
  private int size=0;
  private Node head=null;
  private Node end=null;
  
  private static class Node implements Serializable{ //Node��Ҳ����ʵ��Serializable�ӿ�
    String data;
    Node next;
    Node previous;
  }
  
  /** ���б�ĩβ����һ���ַ��� */
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
  
  /** ��ȡ�б���ָ��λ�õ�һ���ַ��� */
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
    //���б��м���1500���ַ��� 
    for(int i=0;i<1500;i++)list.add("hello"+i);

    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    
    //��StringList�������л���һ���ֽڻ�����
    ObjectOutputStream o =new ObjectOutputStream(buf);
    o.writeObject(list);
    
    //���ֽڻ����з����л�StringList����
    ObjectInputStream in =new ObjectInputStream(
       new ByteArrayInputStream(buf.toByteArray()));
    list= (StringList)in.readObject();
    System.out.println("After Serialization:" + list.size());
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
