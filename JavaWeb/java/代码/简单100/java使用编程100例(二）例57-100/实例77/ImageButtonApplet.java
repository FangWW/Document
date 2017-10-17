/**
 * <p>Title: Appletʹ��jar��</p>
 * <p>Description: �����ļ���ͼƬ����Դ�ļ������һ�����ص����ء�
                   ��ʵ����ʾAppletʹ����Щ��Դ</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: ImageButtonApplet.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class ImageButtonApplet extends JApplet
{
private String path = "/img/ittoolbox.gif";
private ImageIcon logoButtonIcon = new ImageIcon(getClass().getResource(path));
/**
 *<br>����˵������ʼ��Applet����Ӵ�ͼƬ�İ�ť
 *<br>���������
 *<br>�������ͣ�
 */
public void init()
{
try
{
if (logoButtonIcon == null)
throw new Exception("cannot get the image!");

JButton iButton = new JButton(logoButtonIcon);

Container cp = this.getContentPane();
cp.add(iButton);
}
catch (Exception e)
{
e.printStackTrace();
}
}
}
