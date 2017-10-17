import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
/**
 * <p>Title: �Լ�����ı��</p>
 * <p>Description: �̳�AbstractTableModel�࣬ʵ���Լ��ı��</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: MyTableDemo.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class MyTableDemo extends JFrame {

    public MyTableDemo() {
        super("MyTableDemo");
        //�����Լ��ı�񣬲���ӵ�JTable��
        MyTableModel myModel = new MyTableModel();
        JTable table = new JTable(myModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));

        //�������ӵ��ɹ��������
        JScrollPane scrollPane = new JScrollPane(table);

        //�����������ӵ�����
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        //��Ӽ���
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
/**
 * <p>Title: �����Լ��ı��ģʽ</p>
 * <p>Description: ͨ���̳�AbstractTableModel���������Լ��ı��ģʽ��
 *    ����ʹ�õ������Ժ�ſ��Ա༭</p>
 */
    class MyTableModel extends AbstractTableModel {
        //�����ͷ
        final String[] columnNames = {"����", 
                                      "�Ա�",
                                      "ѧ��",
                                      "����",
                                      "�Ƿ��ѻ�"};
        //��ʼ��������
        final Object[][] data = {
            {"����", "��", 
             "��", new Integer(25), new Boolean(false)},
            {"����", "��", 
             "��", new Integer(33), new Boolean(true)},
            {"����", "��",
             "����", new Integer(20), new Boolean(false)},
            {"��ٻ", "Ů",
             "��ר", new Integer(26), new Boolean(true)},
            {"�ܴ�", "��",
             "��", new Integer(24), new Boolean(false)}
        };
/**
 *<br>����˵�����̳�AbstractTableModel����ʵ�ֵķ���
 *<br>���������
 *<br>�������ͣ�int ����
 */
        public int getColumnCount() {
            return columnNames.length;
        }
/**
 *<br>����˵�����̳�AbstractTableModel����ʵ�ֵķ���
 *<br>���������
 *<br>�������ͣ�int ����
 */        
        public int getRowCount() {
            return data.length;
        }
/**
 *<br>����˵�����̳�AbstractTableModel����ʵ�ֵķ���
 *<br>���������
 *<br>�������ͣ�String ����
 */
        public String getColumnName(int col) {
            return columnNames[col];
        }
/**
 *<br>����˵�����̳�AbstractTableModel����ʵ�ֵķ�������ȡ����е�����
 *<br>���������int row ����
 *<br>���������int col ����
 *<br>�������ͣ�Object ���ݶ���
 */
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
/**
 *<br>����˵����ʵ���������ʹ�����һ�в�����ʾtrue��false������ʹ�ü���
 *<br>���������
 *<br>�������ͣ�
 */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
/**
 *<br>����˵�������������һ����Ҫʵ�֡�������Ϊ�˶���ɱ༭����
 *<br>���������
 *<br>�������ͣ�boolean �Ƿ�ɱ༭
 */
        public boolean isCellEditable(int row, int col) {
            if (col < 2) { 
                return false;
            } else {
                return true;
            }
        }
/**
 *<br>����˵����ʵ�ִ˷�������ñ༭���ݡ�
 *<br>���������
 *<br>�������ͣ�
 */
        public void setValueAt(Object value, int row, int col) {
                System.out.println("�޸�����λ�ã� " + row + "," + col
                                   + " ������Ϊ�� " + value);

            data[row][col] = value;
            fireTableCellUpdated(row, col);

                System.out.println("���������:");
                printDebugData();

        }
/**
 *<br>����˵�����������е�������
 *<br>���������
 *<br>�������ͣ�
 */
        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    �� " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
    public static void main(String[] args) {
    	JFrame.setDefaultLookAndFeelDecorated(true);
        MyTableDemo frame = new MyTableDemo();
        frame.pack();
        frame.setVisible(true);
    }
}
