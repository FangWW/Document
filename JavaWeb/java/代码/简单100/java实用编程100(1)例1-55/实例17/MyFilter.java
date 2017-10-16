import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;
/**
 * <p>Title: 文件过滤器演示</p>
 * <p>Description: FileChooserDemo文件使用的文件过滤器</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: MyFilter.java</p>
 * @author 杜江
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
            
            if (extension.equals("java")) {//定义过滤Java文件
                    return true;
            } else {
                return false;
            }

        }

        return false;
    }

    //过滤器描述
    public String getDescription() {
        return "Java";
    }
/**
 *<br>方法说明：获取文件扩展名
 *<br>输入参数：
 *<br>返回类型：
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
