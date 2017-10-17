/**
 * <p>Title: �ͻ��������</p>
 * <p>Description: ��ȡ�ͻ���HTTP���󣬷����ͻ�����Ҫ���ļ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: Request.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class Request{
  InputStream in = null;
/**
 *<br>����˵�������������������������ʱ�ͻ����������ݡ�
 *<br>���������
 *<br>�������ͣ�
 */
  public Request(InputStream input){
    this.in = input;
  }
/**
 *<br>����˵���������ͻ�������
 *<br>���������
 *<br>�������ͣ�String �����ļ��ַ�
 */
  public String parse() {
    //��Socket��ȡһ������
    StringBuffer requestStr = new StringBuffer(2048);
    int i;
    byte[] buffer = new byte[2048];
    try {
        i = in.read(buffer);
    }
    catch (IOException e) {
        e.printStackTrace();
        i = -1;
    }
    for (int j=0; j<i; j++) {
        requestStr.append((char) buffer[j]);
    }
    System.out.print(requestStr.toString());
    return getUri(requestStr.toString());
  }
/**
 *<br>����˵������ȡURI�ַ�
 *<br>���������String requestString �����ַ�
 *<br>�������ͣ�String URI��Ϣ�ַ�
 */
  private String getUri(String requestString) {
    int index1, index2;
    index1 = requestString.indexOf(' ');
    if (index1 != -1) {
        index2 = requestString.indexOf(' ', index1 + 1);
        if (index2 > index1)
           return requestString.substring(index1 + 1, index2);
    }
    return null;
  }
}
