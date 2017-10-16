import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;


public class SaveFile extends JFrame 
{

	JFileChooser fileChooser;
	static File fff=new File("asd.txt");
	
    
    public SaveFile()
    { 
    	
        
        try
		{    //�������
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){}
		
		
        
        
        fileChooser = new JFileChooser("e:");
        fileChooser.setApproveButtonText("ȷ��");
        fileChooser.setDialogTitle("�����ļ�");
        
        fileChooser.showSaveDialog(this);

        
        
        
      }
    
    
	
  
    public static void main(String[] args)
    {
  	   new SaveFile();
  	
  	   
    }
    }
