public class ArrayTester1 {
  public static void main(String args[])throws Exception {
    Class classType = Class.forName("java.lang.String");
    //����һ������Ϊ10���ַ�������
    Object array = Array.newInstance(classType, 10);
    //������λ��Ϊ5��Ԫ����Ϊ"hello"
    Array.set(array, 5, "hello");
    //�������λ��Ϊ5��Ԫ�ص�ֵ
    String s = (String) Array.get(array, 5);
    System.out.println(s);
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
