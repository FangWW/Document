import java.io.*;
/**
 * <p>Title: 读取和写入文件</p>
 * <p>Description: 使用字节流方式操作文件，读取和写入文件。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: CopyBytes.java</p>
 * @author 杜江
 * @version 1.0
 */
public class CopyBytes {
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
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
          File inputFile = new File(sFile);//定义读取源文件
          File outputFile = new File(oFile);//定义拷贝目标文件
          //定义输入文件流
          FileInputStream in = new FileInputStream(inputFile);
          //将文件输入流构造到缓存
          BufferedInputStream bin = new BufferedInputStream(in);
          //定义输出文件流
          FileOutputStream out = new FileOutputStream(outputFile);
          //将输出文件流构造到缓存
          BufferedOutputStream bout = new BufferedOutputStream(out);
          int c;
          //循环读取文件和写入文件
          while ((c = bin.read()) != -1)
             bout.write(c);
          //关闭输入输出流，释放资源
          bin.close();
          bout.close();
        }catch(IOException e){
          //文件操作，捕获IO异常。
          System.err.println(e);
        }
    }
}
