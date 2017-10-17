/**
 * <p>Title: ��ȡһ��URL�ı�</p>
 * <p>Description: ͨ��ʹ��URL�࣬����һ��������󣬲���ȡ�����ݡ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: getURL.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class getURL{

 public static void main(String[] arg){
  if(arg.length!=1){
    System.out.println("USE java getURL  url");
    return;
  }
  new getURL(arg[0]);
 }
/**
 *<br>����˵����������
 *<br>���������String URL ����������ҳ��ַ��
 *<br>�������ͣ�
 */
 public getURL(String URL){
    try {
        //����һ��URL����
        URL url = new URL(URL);
    
        //��ȡ�ӷ��������ص������ı�
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String str;
        while ((str = in.readLine()) != null) {
            //������ı�����
            display(str);
        }
        in.close();
    } catch (MalformedURLException e) {
    } catch (IOException e) {
    }
 }
/**
 *<br>����˵������ʾ��Ϣ
 *<br>���������
 *<br>�������ͣ�
 */
 private void display(String s){
   if(s!=null)
     System.out.println(s);
 }
}
