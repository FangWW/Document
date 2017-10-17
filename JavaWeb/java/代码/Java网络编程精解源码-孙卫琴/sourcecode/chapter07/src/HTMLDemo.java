public class HTMLDemo extends JFrame {
  private JLabel jLabel;
  private JButton jButton;

  public HTMLDemo(String title){
   super(title);
   
   jLabel = new JLabel("<html><b><i>Hello World!</i></b></html>");
   jButton = new JButton("<html><img src=\""+this.getClass().getResource("/go.jpg")+"\"></html>"); 
   //jButton = new JButton("<html><img src=\""+"http://www.javathinker.org/image/busy.jpg"+"\"></html>");    
   
   //��������ƶ�����Buttonʱ����ʾ��Ϣ
   jButton.setToolTipText("��ʼ");
  
   Container contentPane=getContentPane();
   contentPane.setLayout(new GridLayout(2, 1));
   contentPane.add(jLabel) ;
   contentPane.add(jButton);

   pack();
   setVisible(true);

   //���û�ѡ��JFrame�Ĺر�ͼ�꣬����������
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
    new HTMLDemo("Hello");
  }
}



/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
