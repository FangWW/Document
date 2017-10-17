/**
 * <p>Title: ��ȡ��д���ļ�</p>
 * <p>Description: ʹ���ֽ�����ʽ�����ļ�����ȡ��д���ļ���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: CopyBytes.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class CopyBytes {
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
    public static void main(String[] args) throws IOException {
        String sFile;
        String oFile;
        if(args.length<2){
          System.out.println("USE:java CopyBytes source file | object file");
          return;
        }else{
          sFile = args[0];
          oFile = args[1];
        }
        try{
          File inputFile = new File(sFile);//�����ȡԴ�ļ�
          File outputFile = new File(oFile);//���忽��Ŀ���ļ�
          //���������ļ���
          FileInputStream in = new FileInputStream(inputFile);
          //���ļ����������쵽����
          BufferedInputStream bin = new BufferedInputStream(in);
          //��������ļ���
          FileOutputStream out = new FileOutputStream(outputFile);
          //������ļ������쵽����
          BufferedOutputStream bout = new BufferedOutputStream(out);
          int c;
          //ѭ����ȡ�ļ���д���ļ�
          while ((c = bin.read()) != -1)
             bout.write(c);
          //�ر�������������ͷ���Դ
          bin.close();
          bout.close();
        }catch(IOException e){
          //�ļ�����������IO�쳣��
          System.err.println(e);
        }
    }
}
