public class ResultSetDemo extends JFrame implements ActionListener{
  private final Connection con;
  private Statement stmt;
  private ResultSet resultSet;

  private JLabel rowLabel=new JLabel();

  private JTextField idTxtFid=new JTextField();
  private JTextField nameTxtFid=new JTextField();
  private JTextField ageTxtFid=new JTextField();
  private JTextField addressTxtFid=new JTextField();
  private JLabel idLabel=new JLabel("id");
  private JLabel nameLabel=new JLabel("name");
  private JLabel ageLabel=new JLabel("age");
  private JLabel addressLabel=new JLabel("address");

  private JButton firstBt=new JButton("first");
  private JButton previousBt=new JButton("previous");
  private JButton nextBt=new JButton("next");
  private JButton lastBt=new JButton("last");
  private JButton insertBt=new JButton("insert");
  private JButton deleteBt=new JButton("delete");
  private JButton updateBt=new JButton("update");

  private JPanel headPanel=new JPanel();
  private JPanel centerPanel=new JPanel();
  private JPanel bottomPanel=new JPanel();
   
  public ResultSetDemo(String title)throws SQLException{
   super(title);
   con=new ConnectionProvider().getConnection();
   stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
   ResultSet.CONCUR_UPDATABLE);
   resultSet=stmt.executeQuery("select ID,NAME,AGE,ADDRESS from CUSTOMERS");
   
   if(resultSet.next())refresh();
   buildDisplay();
  }
  
  private void buildDisplay(){  //����GUI����      
    firstBt.addActionListener(this);
    previousBt.addActionListener(this);
    nextBt.addActionListener(this);
    lastBt.addActionListener(this);
    insertBt.addActionListener(this);
    updateBt.addActionListener(this);
    deleteBt.addActionListener(this);
    Container contentPane=getContentPane();
    headPanel.add(rowLabel);
    centerPanel.setLayout(new GridLayout(4,2,2,2));
    centerPanel.add(idLabel);
    centerPanel.add(idTxtFid);
    idTxtFid.setEditable(false);
    centerPanel.add(nameLabel);
    centerPanel.add(nameTxtFid); 
    centerPanel.add(ageLabel);
    centerPanel.add(ageTxtFid);
    centerPanel.add(addressLabel);
    centerPanel.add(addressTxtFid); 
   
    bottomPanel.add(firstBt);
    bottomPanel.add(previousBt);
    bottomPanel.add(nextBt);
    bottomPanel.add(lastBt);
    bottomPanel.add(insertBt);
    bottomPanel.add(updateBt);
    bottomPanel.add(deleteBt);

    contentPane.add(headPanel,BorderLayout.NORTH) ;
    contentPane.add(centerPanel,BorderLayout.CENTER);
    contentPane.add(bottomPanel,BorderLayout.SOUTH);
 
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    addWindowListener( new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        try{con.close();}catch(Exception ex){ex.printStackTrace();}
        System.exit(0);
      } 
    });

    pack();
    setVisible(true);
  }
  
  
  public void actionPerformed(ActionEvent e) {
    JButton b=(JButton)e.getSource();
    try{
      if(b.getText().equals("first")){
        resultSet.first(); //���α��ƶ�����һ����¼
      }else if(b.getText().equals("last")){
        resultSet.last(); //���α��ƶ��������һ����¼
      }else if(b.getText().equals("next")){
        if(resultSet.isLast())return;
        resultSet.next(); //���α��ƶ�����һ����¼
      }else if(b.getText().equals("previous")){
        if(resultSet.isFirst())return;
        resultSet.previous();  //���α��ƶ���ǰһ����¼
      }else if(b.getText().equals("update")){
        resultSet.updateString("NAME",nameTxtFid.getText());
        resultSet.updateInt("AGE",Integer.parseInt(ageTxtFid.getText()));
        resultSet.updateString("ADDRESS",addressTxtFid.getText());
        resultSet.updateRow();  //���¼�¼
      }else if(b.getText().equals("delete")){
        resultSet.deleteRow();  //ɾ����¼
        resultSet.first(); //��ʾ��һ����¼
      }else if(b.getText().equals("insert")){
        resultSet.moveToInsertRow();
        resultSet.updateString("NAME",nameTxtFid.getText());
        resultSet.updateInt("AGE",Integer.parseInt(ageTxtFid.getText()));
        resultSet.updateString("ADDRESS",addressTxtFid.getText());
        resultSet.insertRow();  //����һ����¼
        resultSet.moveToCurrentRow();  //���α��ƶ�������ǰ��λ��
      }
      refresh();  //ˢ�½����ϵ�����
    }catch(SQLException ex){ex.printStackTrace();}
  }
  
  private void refresh()throws SQLException{  //ˢ�½����ϵ�����
    int row=resultSet.getRow();  //�����α굱ǰ���ڵ�λ��
    rowLabel.setText("��ʾ��"+row+"����¼");
    if(row==0){
      idTxtFid.setText(""); 
      nameTxtFid.setText("");
      ageTxtFid.setText("");
      addressTxtFid.setText("");
    }else{
      idTxtFid.setText(new Long(resultSet.getLong(1)).toString()); 
      nameTxtFid.setText(resultSet.getString(2));
      ageTxtFid.setText(new Integer(resultSet.getInt(3)).toString());
      addressTxtFid.setText(resultSet.getString(4));
    }
  }

  public static void main(String[] args)throws SQLException {
    new ResultSetDemo("��ʾResultSet���÷�");
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
