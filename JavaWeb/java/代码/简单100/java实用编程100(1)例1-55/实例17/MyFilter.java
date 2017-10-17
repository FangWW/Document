import java.io.File;
/**
 * <p>Title: �ļ���������ʾ</p>
 * <p>Description: FileChooserDemo�ļ�ʹ�õ��ļ�������</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: MyFilter.java</p>
 * @author �Ž�
 * @version 1.0
 */

public class MyFilter extends FileFilter {
   private String files;
   public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = getExtension(f);
        if (extension != null) {
            
            if (extension.equals("java")) {//�������Java�ļ�
                    return true;
            } else {
                return false;
            }

        }

        return false;
    }

    //����������
    public String getDescription() {
        return "Java";
    }
/**
 *<br>����˵������ȡ�ļ���չ��
 *<br>���������
 *<br>�������ͣ�
 */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
