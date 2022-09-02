/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kfard
 */
public class OrderUiAdmin extends javax.swing.JFrame {
     private String CustomerID;
     private int credite;
     private int price;
     private String payMethod;
     private String date;
    /**
     * Creates new form OrderUiAdmin
     */
    public OrderUiAdmin() {
        
        initComponents();
        updateJtable();
    }
    
    void updateJtable(){ // order list whitch are not accepted
        DefaultTableModel MenuDetail =(DefaultTableModel)orderList.getModel();
        MenuDetail.setRowCount(0);
        
        try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "select * from ONLINE_ORDER WHERE IS_CHECKED = 'NO'";
                ResultSet rs = stmt.executeQuery(qrry);
                while(rs.next()){
                    String CUSTOMER_ID = rs.getString("CUSTOMER_ID");
                    String  NAME = rs.getString("NAME");
                    String FOOD_LIST = rs.getString("FOOD_LIST");
                    String IS_CHECKED = rs.getString("IS_CHECKED");
                    int    TOTAL_PRICE = rs.getInt("TOTAL_PRICE");
                    String DATE = rs.getString("DATE");
                    String  TIME = rs.getString("TIME");
                    String ADDRESS = rs.getString("ADDRESS");
                    String PHONE_NUMBER = rs.getString("PHONE_NUMBER");
                    String paymentMethod = rs.getString("paymentMethod");
               
                    MenuDetail.addRow(new Object[]{CUSTOMER_ID,NAME,FOOD_LIST,IS_CHECKED,TOTAL_PRICE,DATE,TIME,ADDRESS,PHONE_NUMBER,paymentMethod});
                }
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                }
    }
    
    void readFromTable(){ // read order info from jtable
        int row = orderList.getSelectedRow();
        DefaultTableModel model =(DefaultTableModel)orderList.getModel();
        Name.setText(model.getValueAt(row,1).toString());
        totalPrice.setText(model.getValueAt(row,4).toString());
        Order_item.setText(Order_item.getText()+"ITEM : "+model.getValueAt(row,2).toString()+"\n");
        CustomerID=model.getValueAt(row,0).toString();
        price=Integer.parseInt(model.getValueAt(row,4).toString());
        payMethod=model.getValueAt(row,9).toString();
        date=model.getValueAt(row,5).toString();
    }
    
    void readFromCustomer(){ // Customer old credite will read
         try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID ="+"'"+CustomerID+"';";
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
    
    void updateCustomerCredit(){ // customer credit will updated to his/her account
            readFromCustomer();
           int sum=credite+price;
           //System.out.println("Yes"+tmp);
           //System.out.println("->"+credite);
           try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "UPDATE CUSTOMER SET CREDIT="+sum+"WHERE CUSTOMER_ID ="+"'"+CustomerID+"'"+";";
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
    
    void updateOnlineOrder(){
        try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "DELETE FROM ONLINE_ORDER WHERE CUSTOMER_ID ="+"'"+CustomerID+"'"+";";
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
    void updateBills(){
        try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "INSERT INTO BILLS VALUES("+"'"+CustomerID+"'"+","+"'"+"00000000"+"'"+","+"'"+"00000000"+"'"+","+price+","+"'"+payMethod+"'"+","+0+","+0+","+"'"+date+"'"+");";
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
    void notificationAccept(){
        UserID tmp = new UserID();
        String from=tmp.getterID();
        try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "INSERT INTO notification VALUES("+"'"+from+"'"+","+"'"+CustomerID+"'"+","+"'"+date+" Your order has been placed.Our Rider Will knock You Soon"+"'"+");";
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
    void notificationCancel(){
        UserID tmp = new UserID();
        String from=tmp.getterID();
        try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "INSERT INTO notification VALUES("+"'"+from+"'"+","+"'"+CustomerID+"'"+","+"'"+date+" sorry Your order has been canceled.Stay with us"+"'"+");";
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
    
    void accept(){
        updateCustomerCredit();
        updateOnlineOrder();
        updateBills();
        updateJtable();
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        Name = new javax.swing.JTextField();
        totalPrice = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        Order_item = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderList = new javax.swing.JTable();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        Name.setBorder(javax.swing.BorderFactory.createTitledBorder("Name"));

        totalPrice.setBorder(javax.swing.BorderFactory.createTitledBorder("Total Price"));
        totalPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalPriceActionPerformed(evt);
            }
        });

        Order_item.setColumns(20);
        Order_item.setRows(5);
        jScrollPane2.setViewportView(Order_item);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ACCEPT");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setText("QUIT");
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Name)
                        .addGap(27, 27, 27))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(totalPrice, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(135, 135, 135)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(27, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(Name, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        orderList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CUSTOMER_ID", "NAME", "FOOD_LIST", "CHECKED", " TOTAL_PRICE", "DATE", "TIME", "ADDRESS", "PHONE_NUMBER", "paymentMethod"
            }
        ));
        orderList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(orderList);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void totalPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalPriceActionPerformed

    private void orderListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderListMouseClicked
        // TODO add your handling code here:
        readFromTable(); // read infor
        
    }//GEN-LAST:event_orderListMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        accept();
        notificationAccept();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         updateOnlineOrder();
         updateJtable();
         notificationCancel();
        
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(OrderUiAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderUiAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderUiAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderUiAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderUiAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Name;
    private javax.swing.JTextArea Order_item;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable orderList;
    private javax.swing.JTextField totalPrice;
    // End of variables declaration//GEN-END:variables
}
