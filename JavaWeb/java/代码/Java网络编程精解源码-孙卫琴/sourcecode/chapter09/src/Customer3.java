public class Customer3 implements Serializable {
  private static int count; //���ڼ���Customer3�������Ŀ
  private static final int MAX_COUNT=1000;
  private String name;
  private transient String password;
  
  static{
     System.out.println("����Customer3��ľ�̬�����");
  }
  public Customer3(){
    System.out.println("����Customer3��Ĳ��������Ĺ��췽��");
    count++;
  }
  public Customer3(String name, String password) {
    System.out.println("����Customer3��Ĵ������Ĺ��췽��");
    this.name=name;
    this.password=password;
    count++;
  }

  /** �������飬��buff�����е�ÿ���ֽڵ�ÿһλȡ�� 
   *  ����13�Ķ�����Ϊ00001101��ȡ����Ϊ11110010
   */
  private byte[] change(byte[] buff){
    for(int i=0;i<buff.length;i++){
      int b=0;
      for(int j=0;j<8;j++){
        int bit=(buff[i]>>j & 1)==0 ? 1:0;
        b+=(1<<j)*bit;
      }
      buff[i]=(byte)b;
    }
    return buff;
  }

  private void writeObject(ObjectOutputStream stream)throws IOException {
    stream.defaultWriteObject();  //�Ȱ�Ĭ�Ϸ�ʽ���л� 
    stream.writeObject(change(password.getBytes()));
    stream.writeInt(count);
  }

  private void readObject(ObjectInputStream stream)
          throws IOException, ClassNotFoundException {
    stream.defaultReadObject();  //�Ȱ�Ĭ�Ϸ�ʽ�����л�
    byte[] buff=(byte[])stream.readObject();
    password = new String(change(buff));
    count=stream.readInt();
  }

  public String toString() {
    return "count="+count
           +" MAX_COUNT="+MAX_COUNT
           +" name="+name
           +" password="+ password;
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
