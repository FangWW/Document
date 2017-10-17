package store;

public class StoreGui {

  //�������Ҫ�������
  protected JFrame frame;
  protected Container contentPane;
  protected CardLayout card=new CardLayout();
  protected JPanel cardPan=new JPanel();

  //�������ְ�ť��ѡ������ϵ����
  protected JPanel selPan=new JPanel();
  protected JButton custBt=new JButton("�ͻ���ϸ��Ϣ");
  protected JButton allCustBt=new JButton("���пͻ��嵥");

  //��ʾ�����ͻ�������ϵ����
  protected JPanel custPan=new JPanel();
  protected JLabel nameLb=new JLabel("�ͻ�����");
  protected JLabel idLb=new JLabel("ID");
  protected JLabel addrLb=new JLabel("��ַ");
  protected JLabel ageLb=new JLabel("����");

  protected JTextField nameTf=new JTextField(25);
  protected JTextField idTf=new JTextField(25);
  protected JTextField addrTf=new JTextField(25);
  protected JTextField ageTf=new JTextField(25);
  protected JButton getBt=new JButton("��ѯ�ͻ�");
  protected JButton updBt=new JButton("���¿ͻ�");
  protected JButton addBt=new JButton("��ӿͻ�");
  protected JButton delBt=new JButton("ɾ���ͻ�");

  //�о����пͻ�������ϵ����
  protected JPanel allCustPan=new JPanel();
  protected JLabel allCustLb=new JLabel("���пͻ��嵥",SwingConstants.CENTER);
  protected JTextArea allCustTa=new JTextArea();
  protected JScrollPane allCustSp=new JScrollPane(allCustTa);

  String[] tableHeaders={"ID","����","��ַ","����"};
  JTable table;
  JScrollPane tablePane;
  DefaultTableModel tableModel;

  //��־����ϵ����
  protected JPanel logPan=new JPanel();
  protected JLabel logLb=new JLabel("������־",SwingConstants.CENTER);

  protected JTextArea logTa=new JTextArea(9,50);
  protected JScrollPane logSp=new JScrollPane(logTa);
  
  /** ��ʾ��ˢ�µ����ͻ���� custPan */
  public void refreshCustPane(Customer cust){
    showCard("customer");

    if(cust==null || cust.getId()==-1){
      idTf.setText(null);
      nameTf.setText(null);
      addrTf.setText(null);
      ageTf.setText(null);
      return;
    }
    idTf.setText(new Long(cust.getId()).toString());
    nameTf.setText(cust.getName().trim());
    addrTf.setText(cust.getAddr().trim());
    ageTf.setText(new Integer(cust.getAge()).toString());
  }
  
  /** ��ʾ��ˢ�����пͻ���� allCustPan */
  public void refreshAllCustPan(Set<Customer> custs){
    showCard("allcustomers");
    String newData[][];
    newData=new String[custs.size()][4];
    Iterator<Customer> it=custs.iterator();
    int i=0; 
    while(it.hasNext()){
      Customer cust=it.next();
      newData[i][0]=new Long(cust.getId()).toString();
      newData[i][1]=cust.getName();
      newData[i][2]=cust.getAddr();
      newData[i][3]=new Integer(cust.getAge()).toString();
      i++; 
    }

    tableModel.setDataVector(newData,tableHeaders);
  }
  
  /** ����־���logPan�������־��Ϣ */
  public void updateLog(String msg){
    logTa.append(msg+"\n");
  }

  /** ��ÿͻ����custPan���û������ID */
  public long getCustIdOnCustPan(){
     try{
       return Long.parseLong(idTf.getText().trim());
     }catch(Exception e){
       updateLog(e.getMessage());
       return -1;
     }
  }
  
  /** ��õ����ͻ����custPan���û�����Ŀͻ���Ϣ */
  public Customer getCustomerOnCustPan(){
    try{
      return new Customer(Long.parseLong(idTf.getText().trim()),
        nameTf.getText().trim(),addrTf.getText().trim(),
        Integer.parseInt(ageTf.getText().trim()));
    }catch(Exception e){
      updateLog(e.getMessage());
      return null; 
    }
  }
  
  /** ��ʾ�����ͻ����custPan�������пͻ����allCustPan */
  private void showCard(String cardStr){
    card.show(cardPan,cardStr);
  }
  
  /** ���췽�� */
  public StoreGui(){
    buildDisplay();
  }
  
  /** ����ͼ�ν��� */
  private void buildDisplay(){
   frame=new JFrame("�̵�Ŀͻ�����ϵͳ");
   buildSelectionPanel();
   buildCustPanel();
   buildAllCustPanel();
   buildLogPanel();
   
   /** carPan����CardLayout���ֹ�����������custPan��allCustPan���ſ�Ƭ */
   cardPan.setLayout(card);
   cardPan.add(custPan,"customer");
   cardPan.add(allCustPan,"allcustomers");

   //���������м���������
   contentPane=frame.getContentPane();
   contentPane.setLayout(new BorderLayout());
   contentPane.add(cardPan,BorderLayout.CENTER);
   contentPane.add(selPan,BorderLayout.NORTH);
   contentPane.add(logPan,BorderLayout.SOUTH);

   frame.pack();
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setVisible(true);

  }
 
  /** ����ѡ�����selPan */ 
  private void buildSelectionPanel(){
    selPan.setLayout(new GridLayout(1,2));
    selPan.add(custBt);
    selPan.add(allCustBt);
  }

  /** Ϊѡ�����selPan�е�2����ťע������� */
  public void addSelectionPanelListeners(ActionListener a[]){
   int len=a.length;
   if(len!=2){ return;}

   custBt.addActionListener(a[0]);
   allCustBt.addActionListener(a[1]);
 }
  
  /**�����������ͻ�custPan��� */
  private void buildCustPanel(){
   custPan.setLayout(new GridLayout(6,2));
   custPan.add(idLb);
   custPan.add(idTf);
   custPan.add(nameLb);
   custPan.add(nameTf);
   custPan.add(addrLb);
   custPan.add(addrTf);
   custPan.add(ageLb);
   custPan.add(ageTf);

   custPan.add(getBt);
   custPan.add(updBt);
   custPan.add(addBt);
   custPan.add(delBt);

  }
  
  /** Ϊ�����ͻ����custPan�е�4����ťע������� */
  public void addCustPanelListeners(ActionListener a[]){
    int len=a.length;
    if(len!=4){ return;}

   getBt.addActionListener(a[0]);
   addBt.addActionListener(a[1]);
   delBt.addActionListener(a[2]);
   updBt.addActionListener(a[3]);
  }
  
  /** �������пͻ�allCustPan��� */
  private void buildAllCustPanel(){
    allCustPan.setLayout(new BorderLayout());
    allCustPan.add(allCustLb,BorderLayout.NORTH);
    allCustTa.setText("all customer display");

    tableModel=new DefaultTableModel(tableHeaders,10);
    table=new JTable(tableModel);
    tablePane=new JScrollPane(table);

    allCustPan.add(tablePane,BorderLayout.CENTER);

    Dimension dim=new Dimension(500,150);
    table.setPreferredScrollableViewportSize(dim);
  }
  
  /** ������־���*/
  private void buildLogPanel(){
   logPan.setLayout(new BorderLayout());
   logPan.add(logLb,BorderLayout.NORTH);
   logPan.add(logSp,BorderLayout.CENTER);
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
