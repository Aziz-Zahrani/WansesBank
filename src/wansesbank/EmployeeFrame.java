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
public class EmployeeFrame extends javax.swing.JFrame {

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
    public EmployeeFrame() {
        initComponents();
        updateTable();
    }

    public EmployeeFrame(String username, String password) {
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
        if(user.getEmprole()==1)
            CEObut.setVisible(true);
        else
            CEObut.setVisible(false);
        
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
        jLabel4 = new javax.swing.JLabel();
        filterfield = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        CEObut = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        updatebutton = new javax.swing.JButton();
        addbutton = new javax.swing.JButton();
        deletebutton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        signoutlabel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Employee Panel");
        setMinimumSize(new java.awt.Dimension(1000, 560));
        setPreferredSize(new java.awt.Dimension(1000, 560));
        setResizable(false);
        setSize(new java.awt.Dimension(400, 400));
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
        jScrollPane1.setBounds(10, 20, 790, 370);

        jLabel4.setText("Find: ");
        CheckInformationPanel.add(jLabel4);
        jLabel4.setBounds(620, 400, 40, 25);

        filterfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filterfieldKeyReleased(evt);
            }
        });
        CheckInformationPanel.add(filterfield);
        filterfield.setBounds(660, 400, 130, 25);

        MainCenterPane.add(CheckInformationPanel);
        CheckInformationPanel.setBounds(180, 90, 830, 440);

        jPanel1.setLayout(null);

        CEObut.setText("Manage Employees");
        jPanel1.add(CEObut);
        CEObut.setBounds(10, 180, 160, 32);

        jButton1.setText("Check Information");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(10, 140, 160, 32);

        updatebutton.setText("Update a cutomer");
        updatebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebuttonActionPerformed(evt);
            }
        });
        jPanel1.add(updatebutton);
        updatebutton.setBounds(10, 100, 160, 32);

        addbutton.setText("Add a cutomer");
        addbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbuttonActionPerformed(evt);
            }
        });
        jPanel1.add(addbutton);
        addbutton.setBounds(10, 60, 160, 32);

        deletebutton.setText("Delete a cutomer");
        deletebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebuttonActionPerformed(evt);
            }
        });
        jPanel1.add(deletebutton);
        deletebutton.setBounds(10, 20, 160, 32);

        MainCenterPane.add(jPanel1);
        jPanel1.setBounds(0, 90, 180, 440);

        jPanel2.setBackground(new java.awt.Color(44, 62, 78));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(null);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/logo.png"))); // NOI18N
        jPanel2.add(jLabel5);
        jLabel5.setBounds(0, 5, 210, 80);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Employee Role:"+myEmpRole);
        jPanel2.add(jLabel2);
        jLabel2.setBounds(260, 35, 230, 16);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Employee ID: "+user.getEmp_ID());
        jPanel2.add(jLabel3);
        jLabel3.setBounds(260, 55, 300, 16);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Employee Name: "+user.getFirstname());
        jPanel2.add(jLabel1);
        jLabel1.setBounds(260, 15, 230, 16);

        signoutlabel.setBackground(new java.awt.Color(44, 62, 78));
        signoutlabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/signout.png"))); // NOI18N
        signoutlabel.setBorder(null);
        signoutlabel.setBorderPainted(false);
        signoutlabel.setContentAreaFilled(false);
        signoutlabel.setFocusPainted(false);
        signoutlabel.setFocusable(false);
        signoutlabel.setOpaque(true);
        signoutlabel.setRolloverEnabled(true);
        signoutlabel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/signoutpressed.png"))); // NOI18N
        signoutlabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signoutlabelActionPerformed(evt);
            }
        });
        jPanel2.add(signoutlabel);
        signoutlabel.setBounds(840, 20, 140, 50);

        MainCenterPane.add(jPanel2);
        jPanel2.setBounds(0, 0, 1010, 90);

        getContentPane().add(MainCenterPane);
        MainCenterPane.setBounds(0, 0, 1000, 530);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signoutlabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signoutlabelActionPerformed

        this.dispose();
        WansesLogin a = new WansesLogin();
        a.setLocationRelativeTo(this);
        a.setVisible(true);
       
    }//GEN-LAST:event_signoutlabelActionPerformed

    private void addbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbuttonActionPerformed
    RegisterForm b= new RegisterForm(null, true);
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
                statement.executeUpdate("DELETE FROM ACCOUNT WHERE CUST_ID="+valueid+" AND (ACCTYPE=1 OR ACCTYPE=2)");
                statement.executeUpdate("DELETE FROM CUSTOMER WHERE CUST_ID="+valueid);
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
        EditForm l = new EditForm(null,true,cus);
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
            java.util.logging.Logger.getLogger(EmployeeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeFrame().setVisible(true);
            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CEObut;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton signoutlabel;
    private javax.swing.JButton updatebutton;
    // End of variables declaration//GEN-END:variables

    private void updateTable() {
                     try {
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("SELECT DISTINCT C.CUST_ID AS ID,CONCAT(FIRSTNAME,' ',LASTNAME) as 'Full Name',Username,Password,Phonenumber as 'Phone Number',Income,A.BALANCE AS CHECKING ,B.BALANCE AS SAVING\n" +
"FROM CUSTOMER AS C \n" +
"INNER JOIN ACCOUNT AS A ON A.CUST_ID=C.CUST_ID AND A.ACCTYPE=1\n" +
"JOIN ACCOUNT AS B ON B.CUST_ID=C.CUST_ID AND B.ACCTYPE=2");
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
