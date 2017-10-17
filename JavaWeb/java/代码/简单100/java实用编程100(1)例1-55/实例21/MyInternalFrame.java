import javax.swing.JInternalFrame;
import javax.swing.JTextArea;
/**
 * <p>Title: �ڲ�����</p>
 * <p>Description: ����һ���ڲ����壬�ṩInternalFrameDemo��ʹ��</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: MyInternalFrame.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class MyInternalFrame extends JInternalFrame {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

    public MyInternalFrame() {
        super("�ĵ� #" + (++openFrameCount), 
              true, //�ɱ�ߴ�
              true, //�йرհ�ť
              true, //����󻯰�ť
              true);//��С����ť

        //���ڲ��������һ���ı���
        JTextArea j = new JTextArea(5,20);
        getContentPane().add(j);
        //�����ڲ�����Ĵ�С
        setSize(300,300);

        //�����ڲ��������ʾλ��
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    }
}
