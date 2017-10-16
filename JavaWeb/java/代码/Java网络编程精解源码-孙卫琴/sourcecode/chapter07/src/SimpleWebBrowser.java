import javax.swing.text.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.html.*;
import java.beans.*;

public class SimpleWebBrowser extends JFrame implements HyperlinkListener,ActionListener{
  private JTextField jtf=new JTextField(40); 
  private JEditorPane jep=new JEditorPane();
  private String initialPage="http://www.javathinker.org/helloapp/index.htm";
  public SimpleWebBrowser(String title){
    super(title);
   
    jtf.setText(initialPage);
    jtf.addActionListener(this);
    jep.setEditable(false);
    jep.addHyperlinkListener(this);
     
    //监听editorKit属性被重新设置的事件
    jep.addPropertyChangeListener("editorKit", new PropertyChangeListener(){
      public void propertyChange(PropertyChangeEvent evt){
        System.out.println("set editorKit");
        EditorKit kit = jep.getEditorKit();
          if(kit.getClass() == HTMLEditorKit.class) {
            ((HTMLEditorKit)kit).setAutoFormSubmission(false);
          }
        }
      });


    try{
      jep.setPage(initialPage);
    }catch(IOException e){showError(initialPage); } 

    JScrollPane scrollPane=new JScrollPane(jep);
    Container container=getContentPane();
    container.add(jtf,BorderLayout.NORTH);
    container.add(scrollPane,BorderLayout.CENTER);
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }
    
  public void showError(String url){
     jep.setContentType("text/html"); 
     jep.setText("<html>无法打开网页:"+url+"。输入的URL不合法，或者网页不存在。</html>");
  }

  public static void main(String[] args){
    SimpleWebBrowser browser=new SimpleWebBrowser("Simple Web Browser");
  }
  
  public void actionPerformed(ActionEvent evt){
    try{
      jep.setPage(jtf.getText());
    }catch(Exception e){
       showError(jtf.getText());
    }
  }
  /** 处理用户选择超级链接或者提交表单事件 */
  public void hyperlinkUpdate(HyperlinkEvent evt){
    try{ 
      if (evt.getClass() == FormSubmitEvent.class) {  //处理提交表单事件
        FormSubmitEvent fevt = (FormSubmitEvent)evt;
        URL url=fevt.getURL(); //获得URL
        String method=fevt.getMethod().toString(); //获得请求方式
        String data=fevt.getData(); //获得表单数据
       
        if(method.equals("GET")){  //如果为GET请求方式
           jep.setPage(url.toString()+"?"+data);
           jtf.setText(url.toString()+"?"+data); //把文本框设为用户选择的超级链接
        }else if(method.equals("POST")){  //如果为POST请求方式
           URLConnection uc=url.openConnection(); 
           //发送HTTP响应正文
           uc.setDoOutput(true);
           OutputStreamWriter out=new OutputStreamWriter(uc.getOutputStream()); 
           out.write(data);
           out.close();
          
           //接收HTTP响应正文    
           InputStream in=uc.getInputStream();
           ByteArrayOutputStream buffer=new ByteArrayOutputStream();
           byte[] buff=new byte[1024];  
           int len=-1;

           while((len=in.read(buff))!=-1){
             buffer.write(buff,0,len);
           }
           in.close();

           jep.setText(new String(buffer.toByteArray()));
           jtf.setText(url.toString()); //把文本框设为用户选择的超级链接  
        }
        System.out.println(fevt.getData()+"|"+fevt.getMethod()+"|"+fevt.getURL());
      }else if(evt.getEventType()==HyperlinkEvent.EventType.ACTIVATED){  
        //处理用户选择的超级链接
        jep.setPage(evt.getURL());
        jtf.setText(evt.getURL().toString()); //把文本框设为用户选择的超级链接 
      } 
      
    }catch(Exception e){
       showError(evt.getURL().toString());
    }
  }
}   


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
