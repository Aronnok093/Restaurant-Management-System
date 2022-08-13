/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kfard
 */
public final class BillingSystemUI extends javax.swing.JFrame {

    /**
     * Creates new form BillingSystemUI
     */
    private String ID; // login user id
    private String Role; // admin or cashier or somethig else
    private int uPrice;  // unit price of a product
    private int tPrice;  // total bill created
    private int credite; // customer credite to identify top customer
    private String day;
    private String month;
    private String year;
    private String time;
    private String date;
    private int vat=2;
    public BillingSystemUI() {
        initComponents();
        updateCombooD();
        showDate();
        showTime();
    }
    
     // To show System date 10/07/2022 ARONOCK
    void showDate(){
        Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
        jDate.setText(s.format(d)); // show date in Login Panel
        date=s.format(d);
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        day = dayFormat.format(d);

        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        month = monthFormat .format(d);
        
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        year = yearFormat .format(d);
    }
    //To show System time 10/07/2022 ARONOCK
    void showTime(){
    new Timer(0, (ActionEvent e) -> {
        Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("hh:mm:ss a");
        jTime.setText(s.format(d)); // Show time in Login Panel 10/07/2022 ARONOCK
        time=s.format(d);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }).start();
    }
    
    void setCashierID(String C_ID){ // setter Method to pass login id
        ID=C_ID;
        Role="CASHIER";
    }
    void setVat(int vat){ // SETTER METHOD FOR VAT
        this.vat=vat;
    }
    /*getter method*/
    public String getYear(){  // TO GET CURRENT YEAR
        return year;
    }
    
    public String getDay(){  // TO GET CURRENT DAY
        return day;
    }
    
    public String getMonth(){ // TO GET CURRENT MONTH
        return month;
    }
    
    void updateCombooD(){ // update value datebase to combobox
        try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                //Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-OECCDJF\\SQLEXPRESS;databaseName=RMS","sa","alphacoders4T4");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "select * from FOOD_MENU ;";
                ResultSet rs = stmt.executeQuery(qrry);
                while(rs.next()){
                    FoodListComboBox.addItem(rs.getString("DISH_NAME"));
                }
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                }
    }
    void readFromFoodMenu(){ // This function has been created to read unit price form food menu
        try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "select * from FOOD_MENU WHERE DISH_NAME="+"'"+FoodListComboBox.getSelectedItem().toString()+"'"+";";
                ResultSet rs = stmt.executeQuery(qrry);
                while(rs.next()){
                    String s=rs.getString("PRICE");
                    uPrice=Integer.parseInt(s);
                }
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                }
    }
    void AddTempBill(){  // This will create temporary bill in database system
        String s=quantityTextBox.getText();
        int Q=Integer.parseInt(s);
        int Price= Q*uPrice;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
            Statement stmt = conn.createStatement();
            String qrry;
            qrry = "INSERT INTO TEMP_BILL VALUES("+"'"+FoodListComboBox.getSelectedItem().toString()+"'"+","+Q+","+uPrice+","+Price+");";
            boolean gotResults=stmt.execute(qrry);
            ResultSet rs = null;
            if(!gotResults){
                System.out.println("No results returned");
            }
            else {
                rs = stmt.getResultSet();
                }
            
            //JOptionPane.showMessageDialog(rootPane,"Done");
            
        }catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(rootPane,ex);
        }
        orgerList();
    }
    
    void DellTempBill(){ // this will delete item from j table and database
        try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "DELETE from TEMP_BILL WHERE ITEM="+"'"+FoodListComboBox.getSelectedItem().toString()+"'"+"AND QUANTITY="+quantityTextBox.getText()+";";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                }
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                }
        orgerList();
    }
    
    void orgerList(){ // Order List that will show in J table sory for wrong spelling
        DefaultTableModel MenuDetail =(DefaultTableModel)OrderList.getModel();
        MenuDetail.setRowCount(0);
        
        try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "select * from TEMP_BILL;";
                ResultSet rs = stmt.executeQuery(qrry);
                while(rs.next()){
                    String ITEM = rs.getString("ITEM");
                    int QUANTITY = rs.getInt("QUANTITY");
                    int UNIT_PRICE = rs.getInt("UNIT_PRICE");
                    int TOTAL = rs.getInt("TOTAL");
                   
               
                    MenuDetail.addRow(new Object[]{ITEM ,QUANTITY,UNIT_PRICE,TOTAL});
                }
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                }
    }
    
    void TotalBIll(){ // Totable bill will calculate using temp bill database 
         try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "SELECT SUM(TOTAL) AS TotalBill FROM TEMP_BILL;";
                ResultSet rs = stmt.executeQuery(qrry);
                //rs.next();
                while(rs.next()){
                    String s=rs.getString("TotalBill");
                    tPrice=Integer.parseInt(s);
                    System.out.println(tPrice);
                }
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                }
    }
    
    void FinalBillInfo(){
         PayableAmount.setText(String.valueOf(tPrice+tPrice*(double)((vat*1.0)/100)));
         String Paid= paid.getText();
         int iPaid=Integer.parseInt(Paid);
         int cashBack= (int) (iPaid-tPrice-tPrice*(double)((vat*1.0)/100));
         CashBack.setText(String.valueOf(cashBack));
         String serverID=ServerID.getText();
         String Table= TableNumbers.getText();
         String paymentMethod=PaymentMethodComboBox.getSelectedItem().toString(); 
         String CustomerID;
                 
         String M_ID= Membership.getSelection().getActionCommand();
         if("Yes".equals(M_ID)){
             CustomerID=OldCustomerID.getText();
         }
         else{
             CustomerID=NewCustomerPhone.getText();
         }
         ToBills(CustomerID,serverID,ID, (int) (tPrice+tPrice*(double)((vat*1.0)/100)),paymentMethod,year,month,time);
         ToTableS(serverID,Table,CustomerID);
         printBill(CustomerID);
    }
    
    void ToBills(String CustomerID,String serverID,String id,int paid,String paymentMethod,String year,String month,String time){
        
        try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "INSERT INTO BILLS VALUES("+"'"+CustomerID+"'"+","+"'"+serverID+"'"+","+"'"+id+"'"+","+paid+","+"'"+paymentMethod+"'"+","+Integer.parseInt(year)+","+Integer.parseInt(month)+","+"'"+date+"'"+");";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                }
                JOptionPane.showMessageDialog(null,"Done");
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                } 
       
    }
    
    void ToTableS(String serverID,String TableNumber,String CustomerID){
        try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "INSERT INTO TABLE_S VALUES("+"'"+serverID+"'"+","+"'"+TableNumber+"'"+","+"'"+CustomerID+"'"+","+"'"+date+"'"+");";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                }
                JOptionPane.showMessageDialog(null,"Done");
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                } 
    }
    
    
    void readFromCustomer(){ // Customer old credite will read
         try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID ="+"'"+OldCustomerID.getText()+"';";
                ResultSet rs = stmt.executeQuery(qrry);
                //rs.next();
                while(rs.next()){
                    String s=rs.getString("CREDIT");
                    credite=Integer.parseInt(s);
                   // System.out.println(credite);
                }
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                }
    }
    void customer(){ // credite will be update to the old customer and for new customer new account will be created
        readFromCustomer();
        String M_ID= Membership.getSelection().getActionCommand();
       if("Yes".equals(M_ID)){
           int tmp=(int) (tPrice+tPrice*(double)((vat*1.0)/100));
           int sum=credite+tmp;
           //System.out.println("Yes"+tmp);
           //System.out.println("->"+credite);
           try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "UPDATE CUSTOMER SET CREDIT="+sum+"WHERE CUSTOMER_ID ="+"'"+OldCustomerID.getText()+"'"+";";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                }
                JOptionPane.showMessageDialog(null,"Done");
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                }
       }
       else{
           String tempPassword="11111111";
           try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "INSERT INTO CUSTOMER VALUES("+"'"+NewCustomerPhone.getText()+"'"+","+"'"+NewCustomerName.getText()+"'"+","+"'"+NewCustomerPhone.getText()+"'"+","+"'"+NewCustomerAddress.getText()+"'"+","+"'"+tempPassword+"'"+","+tPrice+");";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                }
                JOptionPane.showMessageDialog(null,"Done");
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                } 
       }
    }
    
    void DeleteAllTempBIll(){// all data will delete from j table and temp data base
        try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "DELETE from TEMP_BILL;";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                }
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                }
        orgerList();
        quantityTextBox.setText(null);
        OldCustomerID.setText(null);
        NewCustomerPhone.setText(null);
        NewCustomerName.setText(null);
        NewCustomerAddress.setText(null);
        paid.setText(null);
        ServerID.setText(null);
        TableNumbers.setText(null);
        PayableAmount.setText(null);
        CashBack.setText(null);
        
    }
    
    void printBill(String CustomerID){
        
        pBill.setText(pBill.getText()+"Date: "+date+"\n");
        pBill.setText(pBill.getText()+"Time: "+time+"\n");
        pBill.setText(pBill.getText()+"Cashier ID: "+ID+"\n");
        pBill.setText(pBill.getText()+"Customer ID: "+CustomerID+"\n");
        pBill.setText(pBill.getText()+"=========================RMS======================================\n");
        DefaultTableModel MenuDetail =(DefaultTableModel)OrderList.getModel();
        //MenuDetail.setRowCount(0);
        pBill.setText(pBill.getText()+"ITEM"+"\t"+"QUANTITY"+"\t"+"UNIT_PRICE"+"\t"+"TOTAL"+"\n");
        for(int i=0;i<OrderList.getRowCount();i++){
            String ITEM= OrderList.getValueAt(i,0).toString();
            String QUANTITY= OrderList.getValueAt(i,1).toString();
            String UNIT_PRICE= OrderList.getValueAt(i,2).toString();
            String TOTAL= OrderList.getValueAt(i,3).toString();
         pBill.setText(pBill.getText()+ITEM+"\t"+QUANTITY+"\t"+UNIT_PRICE+"\t"+TOTAL+"\n");
        }
        pBill.setText(pBill.getText()+"===========================Thank You================================\n");
        pBill.setText(pBill.getText()+"Total Bill:"+String.valueOf(tPrice+tPrice*(double)((vat*1.0)/100))+" ("+vat+"%Vat Included)\n");
        pBill.setText(pBill.getText()+"Payment Method:"+PaymentMethodComboBox.getSelectedItem().toString()+"\n");
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        Membership = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        OrderList = new javax.swing.JTable();
        DeleteAll = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pBill = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        BillGenarate = new javax.swing.JButton();
        printBill = new javax.swing.JButton();
        ServerID = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        PayableAmount = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        paid = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        PaymentMethodComboBox = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        CashBack = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jDate = new javax.swing.JLabel();
        jTime = new javax.swing.JLabel();
        TableNumbers = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        FoodListComboBox = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        quantityTextBox = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        NewCustomerName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        NewCustomerPhone = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        NewCustomerAddress = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        OldCustomerID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Yes = new javax.swing.JRadioButton();
        No = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        SubmitCustomerInfo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        OrderList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Item Name", "Quantity", "Unit Price", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(OrderList);

        DeleteAll.setText("Delete All");
        DeleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(DeleteAll)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DeleteAll)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jScrollPane2.setViewportView(pBill);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        BillGenarate.setText("Genarate Bill");
        BillGenarate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BillGenarateActionPerformed(evt);
            }
        });

        printBill.setText("Print Bill");
        printBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBillActionPerformed(evt);
            }
        });

        jLabel8.setText("Payable Amount:");

        jLabel9.setText("Paid:");

        paid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paidActionPerformed(evt);
            }
        });

        jLabel10.setText("Server ID:");

        PaymentMethodComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Checks", "Debit cards", "Credit cards", "bkash" }));

        jLabel11.setText("Payment Method:");

        jLabel12.setText("Cash Back:");

        jDate.setText("Date:");

        jTime.setText("Time:");

        jLabel13.setText("Table Number's:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PaymentMethodComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PayableAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(ServerID)
                    .addComponent(paid, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TableNumbers, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(CashBack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(BillGenarate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(printBill, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDate)
                    .addComponent(jTime))
                .addGap(31, 31, 31))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PayableAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(printBill)
                        .addComponent(BillGenarate))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(paid, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CashBack, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDate)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(19, 19, 19)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ServerID, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTime)
                            .addComponent(TableNumbers, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel13)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PaymentMethodComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));

        FoodListComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TEMP" }));
        FoodListComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FoodListComboBoxActionPerformed(evt);
            }
        });

        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Remove");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Name:");

        jLabel2.setText("Phone:");

        jLabel3.setText("Address:");

        jLabel4.setText("Order :");

        jLabel5.setText("Quantity:");

        jLabel6.setText("Customer ID:");

        Membership.add(Yes);
        Yes.setText("Yes");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, Yes, org.jdesktop.beansbinding.ELProperty.create("${actionCommand}"), Yes, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        Membership.add(No);
        No.setText("No");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, No, org.jdesktop.beansbinding.ELProperty.create("${actionCommand}"), No, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        jLabel7.setText("Have Membership:");

        SubmitCustomerInfo.setText("Submit");
        SubmitCustomerInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitCustomerInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SubmitCustomerInfo)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(Yes)
                        .addGap(18, 18, 18)
                        .addComponent(No))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(OldCustomerID)
                        .addComponent(NewCustomerAddress)
                        .addComponent(NewCustomerPhone)
                        .addComponent(NewCustomerName)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46)
                            .addComponent(jButton2))
                        .addComponent(FoodListComboBox, 0, 232, Short.MAX_VALUE)
                        .addComponent(quantityTextBox)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(FoodListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(quantityTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addComponent(NewCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(NewCustomerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(NewCustomerAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Yes)
                            .addComponent(No)
                            .addComponent(jLabel7))
                        .addGap(95, 95, 95)
                        .addComponent(OldCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(SubmitCustomerInfo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void FoodListComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FoodListComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FoodListComboBoxActionPerformed

    private void paidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paidActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        readFromFoodMenu();
        AddTempBill();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        DellTempBill();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void SubmitCustomerInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitCustomerInfoActionPerformed
        // TODO add your handling code here:
        TotalBIll();
        customer();
        PayableAmount.setText(String.valueOf(tPrice+tPrice*(double)((vat*1.0)/100)));
    }//GEN-LAST:event_SubmitCustomerInfoActionPerformed

    private void DeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteAllActionPerformed
        // TODO add your handling code here:
        DeleteAllTempBIll();
    }//GEN-LAST:event_DeleteAllActionPerformed

    private void BillGenarateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BillGenarateActionPerformed
        // TODO add your handling code here:
        FinalBillInfo();
    }//GEN-LAST:event_BillGenarateActionPerformed

    private void printBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBillActionPerformed
        try {
            // TODO add your handling code here:
            pBill.print();
        } catch (PrinterException ex) {
            Logger.getLogger(BillingSystemUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        pBill.setText(null);
        DeleteAllTempBIll();
    }//GEN-LAST:event_printBillActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BillingSystemUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BillingSystemUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BillingSystemUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillingSystemUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new BillingSystemUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BillGenarate;
    private javax.swing.JTextField CashBack;
    private javax.swing.JButton DeleteAll;
    private javax.swing.JComboBox<String> FoodListComboBox;
    private javax.swing.ButtonGroup Membership;
    private javax.swing.JTextField NewCustomerAddress;
    private javax.swing.JTextField NewCustomerName;
    private javax.swing.JTextField NewCustomerPhone;
    private javax.swing.JRadioButton No;
    private javax.swing.JTextField OldCustomerID;
    private javax.swing.JTable OrderList;
    private javax.swing.JTextField PayableAmount;
    private javax.swing.JComboBox<String> PaymentMethodComboBox;
    private javax.swing.JTextField ServerID;
    private javax.swing.JButton SubmitCustomerInfo;
    private javax.swing.JTextField TableNumbers;
    private javax.swing.JRadioButton Yes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jTime;
    private javax.swing.JTextPane pBill;
    private javax.swing.JTextField paid;
    private javax.swing.JButton printBill;
    private javax.swing.JTextField quantityTextBox;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
