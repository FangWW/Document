/**
 * <p>Title: Applet���������ļ�</p>
 * <p>Description: ʹ��AudioClip����������ļ��������������ʼ����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: PlayAudio.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class PlayAudio extends Applet 
implements ActionListener{
AudioClip audio;  
Button btExit,btOpen,btPlay,btLoop,btStop;
/**
 *<br>����˵������ʼ��Applet
 *<br>���������
 *<br>�������ͣ�
 */
public void init() {
//���������ļ�
 audio = getAudioClip(getDocumentBase(),"img/1.mid");
//���찴ť
 setLayout(new FlowLayout()); //ʹ�ò��ֹ�����
 btPlay=new Button("Play"); //����Play��ť                   
 btPlay.addActionListener(this); //��Play��ť���һ�������¼�
 btLoop=new Button("Loop"); //����Play��ť
 btLoop.addActionListener(this); //��Play��ť���һ�������¼�
 btStop=new Button("Stop"); //����Play��ť
 btStop.addActionListener(this); //��Play��ť���һ�������¼�
//����ť��ӵ�Applet��
 add(btPlay);
 add(btLoop);
 add(btStop);

}

public void actionPerformed(ActionEvent e) {

  //����������Play��ť
	if (e.getSource()==btPlay) {
       play();
	}
	//����������loop��ť
	if (e.getSource()==btLoop) {
       loop();
	}
	//����������stop��ť
	if (e.getSource()==btStop) {
       stop();
	}
}
/**
 *<br>����˵������������
 *<br>���������
 *<br>�������ͣ�
 */
public void play(){
  	if (audio!=null) stop();
    audio.play();
}
/**
 *<br>����˵����ѭ����������
 *<br>���������
 *<br>�������ͣ�
 */
public void loop(){
  	if (audio!=null)
    audio.loop();
}
/**
 *<br>����˵����ֹͣ��������
 *<br>���������
 *<br>�������ͣ�
 */
public void stop(){
  	if (audio!=null)
  		audio.stop();
     
}
} 

