/**
 * <p>Title: �����Լ��Ĵ���</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: mainFrame.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class mainFrame extends JFrame
{
/**
 *<br>����˵������������ͨ�����ݲ�������ɴ���Ļ��ơ�
 *<br>���������String sTitle �������
 *<br>���������int iWidth ����Ŀ��
 *<br>���������int iHeight ����ĸ߶� 
 *<br>�������ͣ�
 */
  public mainFrame(String sTitle,int iWidth,int iHeight)
  {
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//��ȡ��Ļ�ߴ�
    ImageIcon ii = new ImageIcon("images/middle.gif");
    setTitle(sTitle);//���ô������
    setIconImage(ii.getImage());//���ô����ͼ��
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//���õ��رմ���ʱ�˳�����
    setSize(iWidth,iHeight);//���ô����С
    int w = getSize().width;//��ȡ������
    int h = getSize().height;//��ȡ����߶�
    System.out.println("�����"+w+" ����ߣ�"+h);
    int x = (dim.width-w)/2;
    int y = (dim.height-h)/2;
    setLocation(x,y);//�������Ƶ���Ļ�м�
    setVisible(true);//��ʾ����
  }
  public static void main(String[] args)
  {
    JFrame.setDefaultLookAndFeelDecorated(true);//ʹ�����µ�SWING���
    mainFrame mF = new mainFrame("main Frame Demo",400,300);
  }
}