import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.io.File;


public class SaveFile extends JFrame 
{

	JFileChooser fileChooser;
	static File fff=new File("asd.txt");
	
    
    public SaveFile()
    { 
    	
        
        try
		{    //设置外观
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){}
		
		
        
        
        fileChooser = new JFileChooser("e:");
        fileChooser.setApproveButtonText("确定");
        fileChooser.setDialogTitle("保存文件");
        
        fileChooser.showSaveDialog(this);

        
        
        
      }
    
    
	
  
    public static void main(String[] args)
    {
  	   new SaveFile();
  	
  	   
    }
    }
