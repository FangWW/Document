/**
 * <p>Title: ����HTTP���ݺ��ļ�����</p>
 * <p>Description: ����û�������û���Ҫ���ļ������������HTTPӦ��ͷ�����͸��ͻ��ˡ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: Response.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class Response{
  OutputStream out = null;
/**
 *<br>����˵����������Ϣ
 *<br>���������String ref ������ļ���
 *<br>�������ͣ�
 */
  public void Send(String ref) throws IOException {
    byte[] bytes = new byte[2048];
    FileInputStream fis = null;
    try {
        //�����ļ�
        File file  = new File(WebServer.WEBROOT, ref);
        if (file.exists()) {
            //���������ļ���
            fis = new FileInputStream(file);
            int ch = fis.read(bytes, 0, 2048);
            //��ȡ�ļ�
            String sBody = new String(bytes,0);
            //���������Ϣ
            String sendMessage = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: "+ch+"\r\n" +
                "\r\n" +sBody;
            //����ļ�
            out.write(sendMessage.getBytes());
        }else {
            // �Ҳ����ļ�
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: 23\r\n" +
                "\r\n" +
                "<h1>File Not Found</h1>";
            out.write(errorMessage.getBytes());
        }
       
    }
    catch (Exception e) {
        // �粻��ʵ����File�����׳��쳣��
        System.out.println(e.toString() );
    }
    finally {
        if (fis != null)
            fis.close();
    }
 }
/**
 *<br>����˵��������������ȡ�����
 *<br>���������
 *<br>�������ͣ�
 */
 public Response(OutputStream output) {
    this.out = output;
}
}
