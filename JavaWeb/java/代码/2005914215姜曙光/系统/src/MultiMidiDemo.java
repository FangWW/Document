import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.sound.midi.*;



import java.io.File;
public class MultiMidiDemo extends JFrame 
{
	private String[] midiFile={"betsy.mid","camptown.mid"};
	private MultiMidiPlayer midiPanel;

    public MultiMidiDemo() 
    {
        super("姜曙光音乐播放器");
        setSize(300, 500);
       
        //获取内容窗格
        Container container = getContentPane();
        
        midiPanel = new MultiMidiPlayer(midiFile);
        container.add(midiPanel);
        
        setVisible(true);
        this.addWindowListener(new Windowcloseee());
//   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
      }
    
    
   private class Windowcloseee extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			exit11() ;
		}
	}
   
   
	public void exit11() 
	{   
		this.dispose();
		
	}
	
	
	
    public static void main(String[] args)
    {
        MultiMidiDemo application = new MultiMidiDemo();
    }
}




class MultiMidiPlayer extends JPanel implements Runnable, ActionListener 
{
	Thread runner;
	private JPanel buttonPanel;              //存放按钮的面
    private JButton play, stop;              //播放, 结束按钮 
    private JLabel label,labelMusic;                    //显示信息
    private Sequence currentSound;           //音序
    Sequencer player;                //音序器
    private String[] songFile;               //播放文件
    private int songToPlay;
    private JTextField musicList;
    java.awt.List MusicList;

    public MultiMidiPlayer(String[] songs) {
    	super();
    	this.setLayout(null);
        songFile = songs;
        songToPlay=0;
        label = new JLabel("正在播放：");
          
        Icon iconmusic = new ImageIcon("images\\music.jpg");
		labelMusic = new JLabel(iconmusic);
		labelMusic.setBounds(0, 0, 300, 500);
		
		musicList=new JTextField();
		MusicList = new java.awt.List();
		MusicList.add(songs[0]);
		MusicList.add(songs[1]);
//		MusicList.add("夜归人传奇");
//		MusicList.add("海阔天空");
		MusicList.setBackground(Color.GREEN);
		MusicList.setBounds(20, 70, 200, 280);
        
        
        //创建按钮
        play = new JButton("Play");
        stop = new JButton("Stop");
        stop.setEnabled(false);
        
        play.setBounds(50, 400, 60, 30);
		stop.setBounds(150, 400, 60, 30);
		label.setBounds(20, 360, 80, 30);
		musicList.setBounds(100, 360, 180, 30);
        //注册监听器
        play.addActionListener(this);
        stop.addActionListener(this);
        MusicList.addActionListener(this);
        
        
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        
        
        buttonPanel.add(play);
        buttonPanel.add(stop);
        buttonPanel.add(label);
        buttonPanel.add(musicList);
        buttonPanel.add(labelMusic);
        buttonPanel.add(MusicList);
        
        
        
        //将buttonPanel面板加入内容窗口
        buttonPanel.setBounds(0, 0, 300, 500);
        this.add(buttonPanel);
        
        
        
        if (songFile.length == 0) //如果播放列表中为空，播放按钮设置为不能工作
        {
            play.setEnabled(false);
            runner = null;
            
        }
     }
    
    
    
    
     //处理按钮事件
     public void actionPerformed(ActionEvent event) 
     {
    	 if (event.getSource() == play)
    		 play();
         if(event.getSource()==stop)
             stop();
         else
        	 play();
         if(event.getSource()==MusicList)
         
        	 play();
     }
     
     
     
     //播放音乐
     public void play() 
     {
         if (runner == null) 
         {
            runner = new Thread(this);
            runner.start();
            play.setEnabled(false);
            stop.setEnabled(true);
         }
     }
     
     
     
     //停止播放
     public void stop() 
     {
        if (runner != null) 
        {
            runner = null;
            stop.setEnabled(false);
            play.setEnabled(true);
        }
     }


     public void run() 
     {
        try 
        {
            player = MidiSystem.getSequencer();      //获取音序器
        } 
        catch (Exception exception) 
        {
            musicList.setText(exception.toString());
        }
        
        
        
        while (Thread.currentThread() == runner) 
        {
            for (songToPlay = 0; songToPlay < songFile.length; songToPlay++) 
            {
                if (songFile[songToPlay] != null) 
                {
                    try 
                    {
                        File song = new File(songFile[songToPlay]);
                        //获取音序
                        currentSound = MidiSystem.getSequence(song);
                        player.open();
                        //设置音序器的音序
                        player.setSequence(currentSound);
                        //开始播放
                        player.start();
                        musicList.setText( song.getName());
                        while (player.isRunning() && runner != null) 
                        {
                            try 
                            {
                                Thread.sleep(100);
                            } 
                            catch (InterruptedException exception){}
                        }
                        musicList.setText("");
                        player.close();
                    } 
                    catch (Exception exception) 
                    {
                        label.setText(exception.toString());
                        break;
                    }
                }
            }
        }
    }
}
