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
public class SalaryUIAdmin extends javax.swing.JFrame {

    /**
     * Creates new form SalaryUIAdmin
     */
    public SalaryUIAdmin() {
        initComponents();
         updateJtable();
    }
    
    void hideLogOutButton(){
        Login.setVisible(false);
    }
    void hideBackButton(){
        Back.setVisible(false);
    }
    void addSalary(){
        String s=jComboBox.getSelectedItem().toString();
        if("ADMIN".equals(s)){
                try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "INSERT INTO SALARY(A_ID,SALARY) VALUES("+"'"+Id.getText()+"'"+","+"'"+Salary.getText()+"'"+");";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                    }

                JOptionPane.showMessageDialog(rootPane,"Salary ADDED");

            }catch(ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(rootPane,ex);
            }
        }
        if("MANEGER".equals(s)){
                try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "INSERT INTO SALARY(M_ID,SALARY) VALUES("+"'"+Id.getText()+"'"+","+"'"+Salary.getText()+"'"+");";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                    }

                JOptionPane.showMessageDialog(rootPane,"Salary ADDED");

            }catch(ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(rootPane,ex);
            }
        }if("CASHIER".equals(s)){
                try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "INSERT INTO SALARY(C_ID,SALARY) VALUES("+"'"+Id.getText()+"'"+","+"'"+Salary.getText()+"'"+");";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                    }

                JOptionPane.showMessageDialog(rootPane,"Salary ADDED");

            }catch(ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(rootPane,ex);
            }
        }if("EMPLOYEE".equals(s)){
                try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "INSERT INTO SALARY(E_ID,SALARY) VALUES("+"'"+Id.getText()+"'"+","+"'"+Salary.getText()+"'"+");";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                    }

                JOptionPane.showMessageDialog(rootPane,"Salary ADDED");

            }catch(ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(rootPane,ex);
            }
        }
    }
    
     void update(){ // customer data will be updated
         String s=jComboBox.getSelectedItem().toString();
        if("ADMIN".equals(s)){
                try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "UPDATE SALARY SET A_ID = "+"'"+Id.getText()+"', SALARY = '"+Salary.getText()+"'"+";";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                    }

                JOptionPane.showMessageDialog(rootPane,"Salary UPDATED");

            }catch(ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(rootPane,ex);
            }
        }
        if("MANEGER".equals(s)){
                try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "UPDATE SALARY SET M_ID = "+"'"+Id.getText()+"', SALARY = '"+Salary.getText()+"'"+";";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                    }

                JOptionPane.showMessageDialog(rootPane,"Salary UPDATED");

            }catch(ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(rootPane,ex);
            }
        }if("CASHIER".equals(s)){
                try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "UPDATE SALARY SET C_ID = "+"'"+Id.getText()+"', SALARY = '"+Salary.getText()+"'"+";";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                    }

                JOptionPane.showMessageDialog(rootPane,"Salary UPDATED");

            }catch(ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(rootPane,ex);
            }
        }if("EMPLOYEE".equals(s)){
                try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "UPDATE SALARY SET E_ID = "+"'"+Id.getText()+"', SALARY = '"+Salary.getText()+"'"+";";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                    }

                JOptionPane.showMessageDialog(rootPane,"Salary UPDATED");

            }catch(ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(rootPane,ex);
            }
        }
    }
    
    void delete(){
        String s=jComboBox.getSelectedItem().toString();
        if("ADMIN".equals(s)){
                try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "DELETE FROM SALARY WHERE A_ID = "+"'"+Id.getText()+"'"+";";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                    }

                JOptionPane.showMessageDialog(rootPane,"DELETED");

            }catch(ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(rootPane,ex);
            }
        }
        if("MANEGER".equals(s)){
                try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "DELETE FROM SALARY WHERE M_ID = "+"'"+Id.getText()+"'"+";";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                    }

                JOptionPane.showMessageDialog(rootPane,"DELETED");

            }catch(ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(rootPane,ex);
            }
        }if("CASHIER".equals(s)){
                try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "DELETE FROM SALARY WHERE C_ID = "+"'"+Id.getText()+"'"+";";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                    }

                JOptionPane.showMessageDialog(rootPane,"DELETED");

            }catch(ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(rootPane,ex);
            }
        }if("EMPLOYEE".equals(s)){
                try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                Statement stmt = conn.createStatement();
                String qrry;
                qrry = "DELETE FROM SALARY WHERE E_ID = "+"'"+Id.getText()+"'"+";";
                boolean gotResults=stmt.execute(qrry);
                ResultSet rs = null;
                if(!gotResults){
                    System.out.println("No results returned");
                }
                else {
                    rs = stmt.getResultSet();
                    }

                JOptionPane.showMessageDialog(rootPane,"DELETED");

            }catch(ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(rootPane,ex);
            }
        }
    }
     void updateJtable(){ // order list whitch are not accepted
         
        String s=jComboBox1.getSelectedItem().toString();
        DefaultTableModel MenuDetail =(DefaultTableModel)SalaryJtable.getModel();
        MenuDetail.setRowCount(0);
        if("ADMIN".equals(s)){
            try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                    Statement stmt = conn.createStatement();
                    String qrry;
                    qrry = "select * from SALARY where A_ID!=''";
                    ResultSet rs = stmt.executeQuery(qrry);
                    while(rs.next()){
                        String  A_ID = rs.getString("A_ID");
                        String  SALARY= rs.getString("SALARY");
                        MenuDetail.addRow(new Object[]{A_ID,SALARY});
                    }
                }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                    JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                    }
        }
        else if("MANEGER".equals(s)){
            try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                    Statement stmt = conn.createStatement();
                    String qrry;
                    qrry = "select * from SALARY where M_ID!=''";
                    ResultSet rs = stmt.executeQuery(qrry);
                    while(rs.next()){
                        String  A_ID = rs.getString("M_ID");
                        String  SALARY= rs.getString("SALARY");
                        MenuDetail.addRow(new Object[]{A_ID,SALARY});
                    }
                }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                    JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                    }
        }
        else if("CASHIER".equals(s)){
            try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                    Statement stmt = conn.createStatement();
                    String qrry;
                    qrry = "select * from SALARY where C_ID!=''";
                    ResultSet rs = stmt.executeQuery(qrry);
                    while(rs.next()){
                        String  A_ID = rs.getString("C_ID");
                        String  SALARY= rs.getString("SALARY");
                        MenuDetail.addRow(new Object[]{A_ID,SALARY});
                    }
                }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                    JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                    }
        }
        else if("EMPLOYEE".equals(s)){
            try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection conn = DriverManager.getConnection ("jdbc:sqlserver://localhost:1433; databaseName=RMS; user=sa; password=123456");
                    Statement stmt = conn.createStatement();
                    String qrry;
                    qrry = "select * from SALARY  where E_ID!=''";
                    ResultSet rs = stmt.executeQuery(qrry);
                    while(rs.next()){
                        String  A_ID = rs.getString("E_ID");
                        String  SALARY= rs.getString("SALARY");
                        MenuDetail.addRow(new Object[]{A_ID,SALARY});
                    }
                }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                    JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
                    }
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
        Id = new javax.swing.JTextField();
        Salary = new javax.swing.JTextField();
        ADD = new javax.swing.JButton();
        UPDATE = new javax.swing.JButton();
        DELETE = new javax.swing.JButton();
        jComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        SalaryJtable = new javax.swing.JTable();
        VIEW = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        Back = new javax.swing.JButton();
        Login = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        Id.setFont(new java.awt.Font("Trajan Pro", 0, 13)); // NOI18N
        Id.setBorder(javax.swing.BorderFactory.createTitledBorder("ID"));

        Salary.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SALARY", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trajan Pro", 0, 13))); // NOI18N

        ADD.setBackground(new java.awt.Color(255, 255, 255));
        ADD.setText("ADD");
        ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDActionPerformed(evt);
            }
        });

        UPDATE.setBackground(new java.awt.Color(255, 255, 255));
        UPDATE.setText("UPDATE");
        UPDATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UPDATEActionPerformed(evt);
            }
        });

        DELETE.setBackground(new java.awt.Color(255, 255, 255));
        DELETE.setText("DELETE");
        DELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELETEActionPerformed(evt);
            }
        });

        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "MANEGER", "CASHIER", "EMPLOYEE" }));

        SalaryJtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "SALARY"
            }
        ));
        jScrollPane1.setViewportView(SalaryJtable);

        VIEW.setBackground(new java.awt.Color(255, 255, 255));
        VIEW.setText("VIEW");
        VIEW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VIEWActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "MANEGER", "CASHIER", "EMPLOYEE" }));

        Back.setBackground(new java.awt.Color(255, 255, 153));
        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        Login.setBackground(new java.awt.Color(255, 255, 153));
        Login.setText("LOG OUT");
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(ADD, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(UPDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(DELETE, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(Salary)
                                .addComponent(Id))
                            .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(VIEW, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Login)
                        .addGap(42, 42, 42))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Back)
                    .addComponent(Login))
                .addGap(28, 28, 28)
                .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Salary, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ADD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UPDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DELETE, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VIEW))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VIEWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VIEWActionPerformed
        // TODO add your handling code here:
        updateJtable();
    }//GEN-LAST:event_VIEWActionPerformed

    private void ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDActionPerformed
        // TODO add your handling code here:
        addSalary();
    }//GEN-LAST:event_ADDActionPerformed

    private void DELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELETEActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_DELETEActionPerformed

    private void UPDATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPDATEActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_UPDATEActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_BackActionPerformed

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
        // TODO add your handling code here:
        LoginUI temp = new LoginUI();
        temp.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_LoginActionPerformed

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
            java.util.logging.Logger.getLogger(SalaryUIAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalaryUIAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalaryUIAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalaryUIAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalaryUIAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADD;
    private javax.swing.JButton Back;
    private javax.swing.JButton DELETE;
    private javax.swing.JTextField Id;
    private javax.swing.JButton Login;
    private javax.swing.JTextField Salary;
    private javax.swing.JTable SalaryJtable;
    private javax.swing.JButton UPDATE;
    private javax.swing.JButton VIEW;
    private javax.swing.JComboBox<String> jComboBox;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
