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
import java.util.Random;
import javax.swing.JFrame;
import net.proteanit.sql.DbUtils;

public class EditForm extends javax.swing.JDialog {
    
    private static final String USERNAME= "wansesco_wans";
    private static final String PASSWORD= "xzMAsW1WQ8Lg";
    private static final String CONN_STRING= "jdbc:mysql://wanses.com:3306/wansesco_wanses";
    private final String char_list = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_";
    private int cusid;
    
    
    public StringBuilder PasswordGen(){
        Random rand= new Random();
        int RanInt;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            RanInt= rand.nextInt(char_list.length());
            char ch = char_list.charAt(RanInt);
            str.append(ch);
        }
        return str;
        
    }
    public void userRetrieve(){
        try {
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("SELECT  CUST_ID,FIRSTNAME,LASTNAME,Username,Password,Address,Phonenumber,Income FROM CUSTOMER WHERE CUST_ID="+cusid);
                resultset.next();
                fnamefield.setText(resultset.getString(2));
                lnamefield.setText(resultset.getString(3));
                userfield.setText(resultset.getString(4));
                passfield.setText(resultset.getString(5));
                addfield.setText(resultset.getString(6));
                phonefield.setText(resultset.getString(7));
                incomefield.setText(resultset.getString(8));

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    private EditForm(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public EditForm(java.awt.Frame parent, boolean modal, int cus) {
        super(parent, modal);
        initComponents();
        cusid=cus;
        userRetrieve();
        
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
        registerbutton = new javax.swing.JButton();
        cancelbutton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        passfield = new javax.swing.JTextField();
        passlabel = new javax.swing.JLabel();
        userlabel = new javax.swing.JLabel();
        userfield = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        fullname = new javax.swing.JLabel();
        incomelabel = new javax.swing.JLabel();
        phonefield = new javax.swing.JTextField();
        fullname1 = new javax.swing.JLabel();
        addfield = new javax.swing.JTextField();
        lnamefield = new javax.swing.JTextField();
        incomefield = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fnamefield = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Register Form");
        setResizable(false);

        jPanel1.setLayout(null);

        registerbutton.setText("Register");
        registerbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerbuttonActionPerformed(evt);
            }
        });
        jPanel1.add(registerbutton);
        registerbutton.setBounds(60, 370, 100, 25);

        cancelbutton.setText("Cancel");
        cancelbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbuttonActionPerformed(evt);
            }
        });
        jPanel1.add(cancelbutton);
        cancelbutton.setBounds(180, 370, 100, 25);

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("<html>"+"Checking and saving accounts will be made for this customer"+"</html>");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(25, 330, 290, 30);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Login Informaion"));
        jPanel2.setLayout(null);
        jPanel2.add(passfield);
        passfield.setBounds(90, 60, 190, 24);

        passlabel.setText("Password:");
        jPanel2.add(passlabel);
        passlabel.setBounds(20, 60, 61, 25);

        userlabel.setText("Username:");
        jPanel2.add(userlabel);
        userlabel.setBounds(20, 30, 62, 25);
        jPanel2.add(userfield);
        userfield.setBounds(90, 30, 190, 24);

        jButton1.setText("Generate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(200, 90, 80, 25);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(20, 10, 300, 130);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Personal Information"));
        jPanel3.setLayout(null);

        fullname.setText("First Name:");
        jPanel3.add(fullname);
        fullname.setBounds(20, 30, 70, 25);

        incomelabel.setText("Yearly Income* :");
        jPanel3.add(incomelabel);
        incomelabel.setBounds(20, 150, 91, 25);
        jPanel3.add(phonefield);
        phonefield.setBounds(120, 120, 160, 24);

        fullname1.setText("Last Name:");
        jPanel3.add(fullname1);
        fullname1.setBounds(20, 60, 70, 25);
        jPanel3.add(addfield);
        addfield.setBounds(90, 90, 190, 24);
        jPanel3.add(lnamefield);
        lnamefield.setBounds(90, 60, 190, 24);
        jPanel3.add(incomefield);
        incomefield.setBounds(120, 150, 160, 24);

        jLabel5.setText("Phone Number:");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(20, 120, 87, 25);

        jLabel6.setText("Address:");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(20, 90, 51, 25);
        jPanel3.add(fnamefield);
        fnamefield.setBounds(90, 30, 190, 24);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(20, 140, 300, 190);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerbuttonActionPerformed

        String firstname = fnamefield.getText();
        String lastname = lnamefield.getText();
        String phonenumber = phonefield.getText();
        Double income = Double.parseDouble(incomefield.getText());
        int membership = Double.parseDouble(incomefield.getText()) >= 15000 ? 3 : (Double.parseDouble(incomefield.getText()) >= 7500 ? 2 : 1);
        String address = addfield.getText();
        String username = userfield.getText();
        String password = passfield.getText();
        int regcustid;

        try {
            // create a java mysql database connection
            Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();

            statement.executeUpdate("UPDATE CUSTOMER SET FIRSTNAME='"+firstname+"',LASTNAME='"+lastname+"',PHONENUMBER='"+phonenumber
                    + "',INCOME="+income+",MEMBERSHIP="+membership+",ADDRESS='"+address+"', USERNAME='"+username+"', PASSWORD='"+password+"' WHERE CUST_ID="+cusid);

            JOptionPane.showMessageDialog(this, "Updated seccessfully!");
            this.dispose();
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e);
        }
        
    }//GEN-LAST:event_registerbuttonActionPerformed

    private void cancelbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbuttonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelbuttonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        passfield.setText(PasswordGen().toString());
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(EditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditForm dialog = new EditForm(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addfield;
    private javax.swing.JButton cancelbutton;
    private javax.swing.JTextField fnamefield;
    private javax.swing.JLabel fullname;
    private javax.swing.JLabel fullname1;
    private javax.swing.JTextField incomefield;
    private javax.swing.JLabel incomelabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField lnamefield;
    private javax.swing.JTextField passfield;
    private javax.swing.JLabel passlabel;
    private javax.swing.JTextField phonefield;
    private javax.swing.JButton registerbutton;
    private javax.swing.JTextField userfield;
    private javax.swing.JLabel userlabel;
    // End of variables declaration//GEN-END:variables
}
