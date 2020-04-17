/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wansesbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils; //check the rs2xml.jar file + i've imported that file (Done) "i used this for the JTable mainly"
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Wans
 */
public class ExecFrame extends javax.swing.JFrame {

    private static final String USERNAME= "wansesco_wans";
    private static final String PASSWORD= "xzMAsW1WQ8Lg";
    private static final String CONN_STRING= "jdbc:mysql://wanses.com:3306/wansesco_wanses";
    String[] empRoles = {"CEO", "Security & Fraud Specialist", "Accountant", "Fund Manager", "Business Technology Specialist"};
    String myEmpRole;
    String []cus= new String[7];
    private TableModel model;
    Employee user = new Employee();

    /**
     * Creates new form EmployeeFrame
     */
    public ExecFrame() {
        initComponents();
        updateTable();
    }

    public ExecFrame(String username, String password) {
        try (
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("SELECT * FROM EMPLOYEE WHERE username='" + username + "' and password='" + password + "'");) 
        {
               
            //jTable1.setModel(DbUtils.resultSetToTableModel(resultset));
            ResultSetMetaData meta = resultset.getMetaData();

            resultset.next();
            user.setEmp_ID(resultset.getInt(1));
            user.setFirstname(resultset.getString(2));
            user.setLastname(resultset.getString(3));
            user.setUsername(resultset.getString(4));
            user.setPassword(resultset.getString(5));
            user.setEmprole(resultset.getInt(6));

            if (user.getEmprole() == 1) {
                myEmpRole = empRoles[0];
            }
            if (user.getEmprole() == 2) {
                myEmpRole = empRoles[1];
            }
            if (user.getEmprole() == 3) {
                myEmpRole = empRoles[2];
            }
            if (user.getEmprole() == 4) {
                myEmpRole = empRoles[3];
            }
            if (user.getEmprole() == 5) {
                myEmpRole = empRoles[4];
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        initComponents();

        
        updateTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainCenterPane = new javax.swing.JPanel();
        CheckInformationPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        updatebutton = new javax.swing.JButton();
        addbutton = new javax.swing.JButton();
        deletebutton = new javax.swing.JButton();
        filterfield = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setTitle("Employee Panel");
        setMinimumSize(new java.awt.Dimension(835, 520));
        setPreferredSize(new java.awt.Dimension(835, 520));
        setResizable(false);
        setSize(new java.awt.Dimension(835, 520));
        getContentPane().setLayout(null);

        MainCenterPane.setLayout(null);

        CheckInformationPanel.setLayout(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(153, 153, 153));
        jTable1.setRowHeight(20);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);

        CheckInformationPanel.add(jScrollPane1);
        jScrollPane1.setBounds(10, 20, 620, 350);

        MainCenterPane.add(CheckInformationPanel);
        CheckInformationPanel.setBounds(180, 90, 830, 440);

        jPanel1.setLayout(null);

        jButton1.setText("Check Information");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(10, 200, 160, 32);

        updatebutton.setText("Update an Employee");
        updatebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebuttonActionPerformed(evt);
            }
        });
        jPanel1.add(updatebutton);
        updatebutton.setBounds(10, 160, 160, 32);

        addbutton.setText("Hire an Employee");
        addbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbuttonActionPerformed(evt);
            }
        });
        jPanel1.add(addbutton);
        addbutton.setBounds(10, 120, 160, 32);

        deletebutton.setText("Fire an Employee");
        deletebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebuttonActionPerformed(evt);
            }
        });
        jPanel1.add(deletebutton);
        deletebutton.setBounds(10, 80, 160, 32);

        filterfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filterfieldKeyReleased(evt);
            }
        });
        jPanel1.add(filterfield);
        filterfield.setBounds(10, 40, 160, 25);

        jLabel4.setText("Find: ");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 20, 40, 20);

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
        jLabel7.setText("<html>Roles:\n<br><br>1 - CEO\n<br><br>2 - Security & Fraud Specialist\n<br><br>3 - Accountant\n<br><br>4 - Fund Manager\n<br><br>5 - Business Technology Specialist</html>");
        jLabel7.setToolTipText("");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 230, 150, 160);

        MainCenterPane.add(jPanel1);
        jPanel1.setBounds(0, 90, 180, 440);

        jPanel2.setBackground(new java.awt.Color(44, 62, 78));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(null);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/logo.png"))); // NOI18N
        jPanel2.add(jLabel5);
        jLabel5.setBounds(0, 5, 210, 80);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Manager Panel");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(210, 10, 590, 47);

        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setFocusable(false);
        jPanel3.setOpaque(false);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Employee Name: "+user.getFirstname()+" "+user.getLastname()+"  |  ");
        jPanel3.add(jLabel1);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Employee ID: "+user.getEmp_ID()+"  |  ");
        jPanel3.add(jLabel3);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Employee Role: "+myEmpRole);
        jPanel3.add(jLabel2);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(210, 60, 590, 30);

        MainCenterPane.add(jPanel2);
        jPanel2.setBounds(0, 0, 1010, 90);

        getContentPane().add(MainCenterPane);
        MainCenterPane.setBounds(0, 0, 1000, 530);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbuttonActionPerformed
    HireForm b= new HireForm(null, true);
    b.setLocationRelativeTo(this);
    b.setVisible(true);  
    }//GEN-LAST:event_addbuttonActionPerformed

    private void deletebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebuttonActionPerformed
        if(jTable1.getSelectedRow()!=-1){
           int column=0;
        int row= jTable1.getSelectedRow();
        int valueid= (int)jTable1.getValueAt(row, column);
                     try {
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                statement.executeUpdate("DELETE FROM EMPLOYEE WHERE EMP_ID="+valueid);
        } catch (SQLException e) {
            System.out.println(e);
        }
        updateTable(); 
        }
        
        
    }//GEN-LAST:event_deletebuttonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        updateTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void filterfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filterfieldKeyReleased
        String a= filterfield.getText();
        filter(a);
    }//GEN-LAST:event_filterfieldKeyReleased

    private void updatebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebuttonActionPerformed
        
        if(jTable1.getSelectedRow()!=-1){
        int column=0;
        int row= jTable1.getSelectedRow();
        int cus= (int)jTable1.getValueAt(row, 0);
        EditEmp l = new EditEmp(null,true,cus);
        l.setLocationRelativeTo(this);
        l.setVisible(true);
        }
        
    }//GEN-LAST:event_updatebuttonActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ExecFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExecFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExecFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExecFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExecFrame().setVisible(true);
            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CheckInformationPanel;
    private javax.swing.JPanel MainCenterPane;
    private javax.swing.JButton addbutton;
    private javax.swing.JButton deletebutton;
    private javax.swing.JTextField filterfield;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton updatebutton;
    // End of variables declaration//GEN-END:variables

    private void updateTable() {
                     try {
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("SELECT EMP_ID, CONCAT(FIRSTNAME,' ',LASTNAME) as 'Full Name',Emprole, USERNAME, PASSWORD FROM EMPLOYEE");
                //ResultSet resultset2 = statement.executeQuery("SELECT BALANCE FROM CUSTOMER");
                
            ResultSetMetaData meta = resultset.getMetaData();
            model = DbUtils.resultSetToTableModel(resultset);
            jTable1.setModel(model);
            jTable1.setDefaultEditor(Object.class, null);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);


        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void filter(String a) {
       TableRowSorter<DefaultTableModel> sorter= new TableRowSorter<>((DefaultTableModel) model);
       jTable1.setRowSorter(sorter);
       
       sorter.setRowFilter(RowFilter.regexFilter(a));
    }
}