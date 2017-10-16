import java.io.*;

/**
 * <p>Title: 文件的读取和写入（字符）</p>
 * <p>Description: 使用FileReader和FileWriter类，采用字符文件访问方式操作文件。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author 杜江
 * @version 1.0
 */
public class CopyChar {
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
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
          File inputFile   = new File(sFile);//定义读取的文件源
          File outputFile = new File(oFile);//定义拷贝的目标文件
          //定义输入文件流
          FileReader in   = new FileReader(inputFile);
          //将文件输入流构造到缓存
          BufferedReader bin = new BufferedReader(in);
          //定义输出文件流
          FileWriter out  = new FileWriter(outputFile);
          //将输出文件流构造到缓存
          BufferedWriter bout = new BufferedWriter(out);
          int c;
          //循环读取和输入文件。
          while ((c = bin.read()) != -1)
             bout.write(c);
          bin.close();
          bout.close();
        }catch(IOException e){
          //文件操作，捕获IO异常。
          System.err.println(e);
        }
    }
}
