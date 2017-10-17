/**
 * <p>Title: �ļ��Ķ�ȡ��д�루�ַ���</p>
 * <p>Description: ʹ��FileReader��FileWriter�࣬�����ַ��ļ����ʷ�ʽ�����ļ���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author �Ž�
 * @version 1.0
 */
public class CopyChar {
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
    public static void main(String[] args) throws IOException {
        String sFile;
        String oFile;
        if(args.length<2){
          System.out.println("USE:java CopyChar source file | object file");
          return;
        }else{
          sFile = args[0];
          oFile = args[1];
        }
        try{
          File inputFile   = new File(sFile);//�����ȡ���ļ�Դ
          File outputFile = new File(oFile);//���忽����Ŀ���ļ�
          //���������ļ���
          FileReader in   = new FileReader(inputFile);
          //���ļ����������쵽����
          BufferedReader bin = new BufferedReader(in);
          //��������ļ���
          FileWriter out  = new FileWriter(outputFile);
          //������ļ������쵽����
          BufferedWriter bout = new BufferedWriter(out);
          int c;
          //ѭ����ȡ�������ļ���
          while ((c = bin.read()) != -1)
             bout.write(c);
          bin.close();
          bout.close();
        }catch(IOException e){
          //�ļ�����������IO�쳣��
          System.err.println(e);
        }
    }
}
