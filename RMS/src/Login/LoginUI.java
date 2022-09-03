/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author kfard
 */
public final class LoginUI extends javax.swing.JFrame {

    /**
     * Creates new form LoginUI
     */
    private String Name;
    private String Phone;
    private String Address;
    
    public LoginUI() {
        initComponents();
        showDate(); // To show date 10/07/2022 ARONOCK
        showTime(); // to show time 10/07/2022 ARONOCK
                
    }
    
    // To show System date 10/07/2022 ARONOCK
    void showDate(){
        Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
        jDate.setText(s.format(d)); // show date in Login Panel
    }
    //To show System time 10/07/2022 ARONOCK
    void showTime(){
    new Timer(0, (ActionEvent e) -> {
        Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("hh:mm:ss a");
        jTime.setText(s.format(d)); // Show time in Login Panel 10/07/2022 ARONOCK
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }).start();
    }
    

   void LoginCall(){ //10/07/2022 ARONOCK LOGIN PANEL THROUGH DATABASE
        boolean flag = false;
        String loginType=loginTypeComboBox.getSelectedItem().toString();
        if("ADMIN".equals(loginType)){
            try{
                 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
               //Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-OECCDJF\\SQLEXPRESS;databaseName=RMS","sa","alphacoders4T4");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "select * from "+loginType+" WHERE A_ID="+loginID.getText() +" AND PASSWORD ="+loginPassword.getText()+";";
                ResultSet rs = stmt.executeQuery(qrry);
                while(rs.next()){
                   flag=true;
                   //String NAME = rs.getString("NAME");
                   //JOptionPane.showMessageDialog(rootPane,NAME);
                }
                if(flag){
                    JOptionPane.showMessageDialog(null, "Successfully Logged In (^_*)");
                    //System.out.print(qrry);
                    adminPanel temp = new adminPanel();
                    temp.setVisible(true);
                    dispose();
                    UserID tmp=new UserID();
                    tmp.setterID(loginID.getText());
                    }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid User-ID or Password (o_O) ");
                    //System.out.print(qrry);
                    }
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                }
        }
        else if("CUSTOMER".equals(loginType)){
            
            try{
                 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "select * from "+loginType+" WHERE CUSTOMER_ID="+loginID.getText() +" AND PASSWORD ="+loginPassword.getText()+";";
                ResultSet rs = stmt.executeQuery(qrry);
                while(rs.next()){
                   flag=true;
                   //String NAME = rs.getString("NAME");
                   //JOptionPane.showMessageDialog(rootPane,NAME);
                }
                if(flag){
                    JOptionPane.showMessageDialog(null, "Successfully Logged In (^_*)");
                    cuntomerInfo();
                    
                    Customer c = new Customer();
                    c.setName(Name);
                    c.setAddress(Address);
                    c.setPhone(Phone);
                    c.setVisible(true);
                    dispose();
                    //System.out.print(qrry);
                    UserID tmp=new UserID();
                    tmp.setterID(loginID.getText());
                    }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid User-ID or Password (o_O) ");
                    //System.out.print(qrry);
                    }
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                }
        }
        else if("CASHIER".equals(loginType)){
            
            try{
                 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "select * from "+loginType+" WHERE C_ID="+loginID.getText() +" AND PASSWORD ="+loginPassword.getText()+";";
                ResultSet rs = stmt.executeQuery(qrry);
                while(rs.next()){
                   flag=true;
                   //String NAME = rs.getString("NAME");
                   //JOptionPane.showMessageDialog(rootPane,NAME);
                }
                if(flag){
                    JOptionPane.showMessageDialog(null, "Successfully Logged In (^_*)");
                    //System.out.print(qrry);
                    dispose();
                    BillingSystemUI tmp = new BillingSystemUI();
                    tmp.setVisible(true);
                    tmp.setCashierID(loginID.getText());
                    }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid User-ID or Password (o_O) ");
                    //System.out.print(qrry);
                    }
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                }
        }
        else if("EMPLOYEE".equals(loginType)){
            
            try{
                 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "select * from "+loginType+" WHERE E_ID="+loginID.getText() +" AND PASSWORD ="+loginPassword.getText()+";";
                ResultSet rs = stmt.executeQuery(qrry);
                while(rs.next()){
                   flag=true;
                   //String NAME = rs.getString("NAME");
                   //JOptionPane.showMessageDialog(rootPane,NAME);
                }
                if(flag){
                    JOptionPane.showMessageDialog(null, "Successfully Logged In (^_*)");
                    //System.out.print(qrry);
                    
                    EmployeeUI emp = new EmployeeUI();
                    emp.setterID(loginID.getText());
                    emp.setVisible(true);
                    UserID tmp=new UserID();
                    tmp.setterID(loginID.getText());
                    dispose();
                    
                    }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid User-ID or Password (o_O) ");
                    //System.out.print(qrry);
                    }
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                }
        }
        else if("MANEGER".equals(loginType)){
            
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "select * from "+loginType+" WHERE M_ID="+loginID.getText() +" AND PASSWORD ="+loginPassword.getText()+";";
                ResultSet rs = stmt.executeQuery(qrry);
                while(rs.next()){
                   flag=true;
                   //String NAME = rs.getString("NAME");
                   //JOptionPane.showMessageDialog(rootPane,NAME);
                }
                if(flag){
                    JOptionPane.showMessageDialog(null, "Successfully Logged In (^_*)");
                    //System.out.print(qrry);
                    UserID tmp=new UserID();
                    tmp.setterID(loginID.getText());
                    }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid User-ID or Password (o_O) ");
                    //System.out.print(qrry);
                    }
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                }
        }
        //To make login fild empty for sequrity security reason 10/07/2022 ARONOCK
        loginID.setText(null);
        loginPassword.setText(null);
    }
    void cuntomerInfo(){
        try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID ="+"'"+loginID.getText()+"';";
                ResultSet rs = stmt.executeQuery(qrry);
                while(rs.next()){
                    
                    String MOBILE=rs.getString("PHONE_NO");
                    Phone=MOBILE;
                    String NAME=rs.getString("NAME");
                    Name=NAME;
                    String ADDRESS=rs.getString("ADDRESS");
                    Address = ADDRESS;
                }
            }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                }
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jDate = new javax.swing.JLabel();
        jTime = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        loginID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        loginPassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        loginTypeComboBox = new javax.swing.JComboBox<>();
        createAccount = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));

        jLabel3.setFont(new java.awt.Font("Trajan Pro", 0, 24)); // NOI18N
        jLabel3.setText("Welcome TO RMS");
        jLabel3.setToolTipText("");

        jDate.setFont(new java.awt.Font("Trajan Pro", 1, 14)); // NOI18N
        jDate.setText("Date:");

        jTime.setFont(new java.awt.Font("Trajan Pro", 1, 14)); // NOI18N
        jTime.setText("Time:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jDate)
                        .addGap(130, 130, 130)
                        .addComponent(jTime)
                        .addGap(0, 71, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDate)
                    .addComponent(jTime))
                .addContainerGap(381, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Trajan Pro", 1, 24)); // NOI18N
        jLabel1.setText("PASSWORD");

        jLabel2.setFont(new java.awt.Font("Trajan Pro", 1, 24)); // NOI18N
        jLabel2.setText("USER ID");

        loginButton.setFont(new java.awt.Font("Trajan Pro", 1, 14)); // NOI18N
        loginButton.setText("SUBMIT");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        loginTypeComboBox.setFont(new java.awt.Font("Trajan Pro", 0, 13)); // NOI18N
        loginTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "CUSTOMER", "MANEGER", "CASHIER", "EMPLOYEE" }));
        loginTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginTypeComboBoxActionPerformed(evt);
            }
        });

        createAccount.setFont(new java.awt.Font("Trajan Pro", 1, 13)); // NOI18N
        createAccount.setText("Create A customer Account");
        createAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAccountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(82, 82, 82)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(createAccount)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(loginPassword)
                        .addComponent(loginID, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(loginTypeComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 103, Short.MAX_VALUE)
                        .addComponent(loginButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(235, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(loginID, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(loginPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(loginTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(createAccount)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginTypeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginTypeComboBoxActionPerformed
    // LOGIN BUTTON 10/07/2022 ARONOCK
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(rootPane,loginTypeComboBox.getSelectedItem().toString());
        if(!loginID.getText().isEmpty())
            LoginCall(); //TO CALL FUNCTION 10/07/2022 ARONOCK 
        else
            JOptionPane.showMessageDialog(rootPane,"Fild is empty");
        
    }//GEN-LAST:event_loginButtonActionPerformed

    private void createAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createAccountActionPerformed
        // TODO add your handling code here:
        CreateCustomerAccount tmp = new CreateCustomerAccount();
        tmp.setVisible(true);
        dispose();
    }//GEN-LAST:event_createAccountActionPerformed

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
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LoginUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createAccount;
    private javax.swing.JLabel jDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jTime;
    private javax.swing.JButton loginButton;
    private javax.swing.JTextField loginID;
    private javax.swing.JPasswordField loginPassword;
    private javax.swing.JComboBox<String> loginTypeComboBox;
    // End of variables declaration//GEN-END:variables
}
