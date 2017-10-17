/**
 * <p>Title: �ļ��Ի�����ʾ</p>
 * <p>Description: ��ʾ���ļ��Ի���ͱ����ļ��Ի���ʹ�����ļ����ˡ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: FileChooserDemo.java</p>
 * @author �Ž�
 * @version 1.0
 */

public class FileChooserDemo extends JPanel
                             implements ActionListener {
    static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;

    public FileChooserDemo() {
        super(new BorderLayout());

        log = new JTextArea(15,40);
        log.setMargin(new Insets(10,10,10,10));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //����һ���ļ����ˣ�ʹ�õ�ǰĿ¼
        fc = new JFileChooser(".");
        //����������MyFilter���ж���
        fc.addChoosableFileFilter(new MyFilter());

        openButton = new JButton("���ļ�",
                                 createImageIcon("images/Open16.gif"));
        openButton.addActionListener(this);

        saveButton = new JButton("�����ļ�",
                                 createImageIcon("images/Save16.gif"));
        saveButton.addActionListener(this);

        //����һ����壬��ӡ����ļ����͡������ļ���
        JPanel buttonPanel = new JPanel(); 
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }
/**
 *<br>����˵�����¼�����
 *<br>���������
 *<br>�������ͣ�
 */
    public void actionPerformed(ActionEvent e) {

        //����������ļ�����ť
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(FileChooserDemo.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //���������һЩ���ļ��Ĵ���
                log.append("���ļ�: " + file.getName() + newline);
            } else {
                log.append("���ļ����û�ȡ����" + newline);
            }

        //����������ļ�����ť
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(FileChooserDemo.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //���������һЩ���ļ��Ĵ���
                log.append("�����ļ�: " + file.getName()  + newline);
            } else {
                log.append("�����ļ����û�ȡ����" + newline);
            }
        }
    }
/**
 *<br>����˵������ȡͼ�����
 *<br>���������String path ͼƬ·��
 *<br>�������ͣ�ImageIcon ͼƬ����
 */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FileChooserDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        //��������
        JFrame frame = new JFrame("FileChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //����һ�����
        JComponent newContentPane = new FileChooserDemo();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        //��ʾ����
        frame.pack();
        frame.setVisible(true);
    }
}
