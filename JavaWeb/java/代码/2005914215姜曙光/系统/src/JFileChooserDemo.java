
import javax.swing.*;
import java.awt.*;



public class JFileChooserDemo extends JFrame 
{

	JFileChooser fileChooser;
	
    
    public JFileChooserDemo()
    { 
    	
        
        try
		{    //设置外观
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){}
		
		
        
        
        fileChooser = new JFileChooser("e:");
        fileChooser.setApproveButtonText("确定");
        fileChooser.setDialogTitle("打开文件");
      
        fileChooser.showOpenDialog(this);
      //  fileChooser.setfile
//         d = new FileDialog(this,
//                "What file do you want to save?", FileDialog.SAVE);
//              d.setFile("fdjfi");
//              //d.setDirectory(".");
//              d.show();
        
      }
    
    
	
  
    public static void main(String[] args)
    {
  	   new JFileChooserDemo();
  	   
    }
  
  
	
	    
	   
  	    
  	
}







