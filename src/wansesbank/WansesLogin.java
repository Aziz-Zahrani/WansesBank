/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
 */
package wansesbank;

import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.ImageIcon;

/**
 *
 * @author Wans
 */
public class WansesLogin extends javax.swing.JFrame {

    /**
     * Creates new form WansesFrame
     */
    private static  final String USERNAME= "wansesco_wans";
    private static final String PASSWORD= "xzMAsW1WQ8Lg";
    private static final String CONN_STRING= "jdbc:mysql://wanses.com:3306/wansesco_wanses";
//public String myname;

    public WansesLogin() {
        initComponents();
        URL iconURL = getClass().getResource("/wansesbank/images/appicon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        LoginPanel = new javax.swing.JPanel();
        usernamefield = new javax.swing.JTextField();
        passlabel = new javax.swing.JLabel();
        userlabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        passfield = new javax.swing.JPasswordField();
        RegisterPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        userlabel1 = new javax.swing.JLabel();
        regusername = new javax.swing.JTextField();
        regpassword = new javax.swing.JTextField();
        passlabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        regphonenumber = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        incomelabel = new javax.swing.JLabel();
        regincome = new javax.swing.JTextField();
        regaddress = new javax.swing.JTextField();
        reglastname = new javax.swing.JTextField();
        reglastnamelabel = new javax.swing.JLabel();
        regfirstname = new javax.swing.JTextField();
        fullname = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        registerbutton = new javax.swing.JButton();
        cancelbutton = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(620, 475));
        setPreferredSize(new java.awt.Dimension(620, 475));
        setResizable(false);
        getContentPane().setLayout(null);

        container.setLayout(null);

        LoginPanel.setBackground(new java.awt.Color(240, 240, 240));
        LoginPanel.setLayout(null);

        usernamefield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernamefieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usernamefieldKeyTyped(evt);
            }
        });
        LoginPanel.add(usernamefield);
        usernamefield.setBounds(110, 180, 221, 24);

        passlabel.setForeground(new java.awt.Color(51, 51, 51));
        passlabel.setText("Password:");
        LoginPanel.add(passlabel);
        passlabel.setBounds(110, 220, 61, 16);

        userlabel.setForeground(new java.awt.Color(51, 51, 51));
        userlabel.setText("Username:");
        LoginPanel.add(userlabel);
        userlabel.setBounds(110, 160, 62, 16);

        jButton1.setText("Log in");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        LoginPanel.add(jButton1);
        jButton1.setBounds(160, 290, 110, 30);

        jLabel7.setText("Connecting..");
        jLabel7.setVisible(false);
        LoginPanel.add(jLabel7);
        jLabel7.setBounds(180, 360, 80, 16);

        passfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passfieldKeyTyped(evt);
            }
        });
        LoginPanel.add(passfield);
        passfield.setBounds(110, 240, 220, 22);

        jLayeredPane1.add(LoginPanel);
        LoginPanel.setBounds(0, 0, 440, 470);

        RegisterPanel.setBackground(new java.awt.Color(230, 230, 230));
        RegisterPanel.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(240, 240, 240));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(226, 226, 226)));
        jPanel2.setLayout(null);

        userlabel1.setForeground(new java.awt.Color(0, 0, 0));
        userlabel1.setText("Username:");
        jPanel2.add(userlabel1);
        userlabel1.setBounds(70, 45, 62, 25);

        regusername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                regusernameKeyTyped(evt);
            }
        });
        jPanel2.add(regusername);
        regusername.setBounds(140, 45, 190, 24);

        regpassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                regpasswordKeyTyped(evt);
            }
        });
        jPanel2.add(regpassword);
        regpassword.setBounds(140, 80, 190, 24);

        passlabel1.setForeground(new java.awt.Color(0, 0, 0));
        passlabel1.setText("Password:");
        jPanel2.add(passlabel1);
        passlabel1.setBounds(70, 80, 61, 25);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(44, 62, 78));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Login information");
        jLabel3.setToolTipText("");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(10, 10, 420, 19);

        RegisterPanel.add(jPanel2);
        jPanel2.setBounds(0, 60, 440, 130);

        jPanel3.setBackground(new java.awt.Color(240, 240, 240));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(237, 237, 237)));
        jPanel3.setLayout(null);

        regphonenumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                regphonenumberKeyTyped(evt);
            }
        });
        jPanel3.add(regphonenumber);
        regphonenumber.setBounds(130, 125, 110, 24);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Phone Number :");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(40, 125, 100, 25);

        incomelabel.setForeground(new java.awt.Color(0, 0, 0));
        incomelabel.setText("Yearly Income* :");
        jPanel3.add(incomelabel);
        incomelabel.setBounds(40, 165, 91, 25);

        regincome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                regincomeKeyTyped(evt);
            }
        });
        jPanel3.add(regincome);
        regincome.setBounds(130, 165, 110, 24);

        regaddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                regaddressKeyTyped(evt);
            }
        });
        jPanel3.add(regaddress);
        regaddress.setBounds(90, 85, 310, 24);

        reglastname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                reglastnameKeyTyped(evt);
            }
        });
        jPanel3.add(reglastname);
        reglastname.setBounds(290, 45, 110, 24);

        reglastnamelabel.setForeground(new java.awt.Color(0, 0, 0));
        reglastnamelabel.setText("Last Name:");
        jPanel3.add(reglastnamelabel);
        reglastnamelabel.setBounds(230, 45, 70, 25);

        regfirstname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                regfirstnameKeyTyped(evt);
            }
        });
        jPanel3.add(regfirstname);
        regfirstname.setBounds(110, 45, 110, 24);

        fullname.setForeground(new java.awt.Color(0, 0, 0));
        fullname.setText("First Name:");
        jPanel3.add(fullname);
        fullname.setBounds(40, 45, 70, 25);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(44, 62, 78));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Personal Information");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(10, 10, 420, 19);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Address:");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(40, 85, 51, 25);

        registerbutton.setText("Register");
        registerbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerbuttonActionPerformed(evt);
            }
        });
        jPanel3.add(registerbutton);
        registerbutton.setBounds(80, 210, 100, 25);

        cancelbutton.setText("Cancel");
        cancelbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbuttonActionPerformed(evt);
            }
        });
        jPanel3.add(cancelbutton);
        cancelbutton.setBounds(260, 210, 100, 25);

        RegisterPanel.add(jPanel3);
        jPanel3.setBounds(0, 190, 440, 270);

        jPanel15.setBackground(new java.awt.Color(70, 94, 114));
        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 2, 0, new java.awt.Color(50, 72, 92)));
        jPanel15.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 33)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/labels/registeration.png"))); // NOI18N
        jPanel15.add(jLabel1);
        jLabel1.setBounds(0, 0, 440, 60);

        RegisterPanel.add(jPanel15);
        jPanel15.setBounds(0, 0, 450, 60);

        jLayeredPane1.add(RegisterPanel);
        RegisterPanel.setBounds(0, 0, 440, 470);

        container.add(jLayeredPane1);
        jLayeredPane1.setBounds(180, 0, 440, 480);

        jPanel1.setBackground(new java.awt.Color(44, 62, 78));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 715));
        jPanel1.setLayout(null);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(38, 59, 82));
        jButton2.setText("Register");
        jButton2.setToolTipText(""); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(50, 390, 70, 23);

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Create an account");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 360, 110, 20);

        container.add(jPanel1);
        jPanel1.setBounds(0, 0, 180, 470);

        LoginPanel.setVisible(true);
        RegisterPanel.setVisible(false);

        getContentPane().add(container);
        container.setBounds(0, 0, 620, 480);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (usernamefield.getText().startsWith("admin@")) {
            try (Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultset = statement.executeQuery("SELECT username, password FROM EMPLOYEE WHERE username='" + usernamefield.getText() + "' AND password= BINARY '" + passfield.getText() + "'");) {
                resultset.next();
                String username = resultset.getString(1);
                String password = resultset.getString(2);
                if (username.equalsIgnoreCase(usernamefield.getText()) && password.equals(passfield.getText())) {
                    EmployeeFrame a = new EmployeeFrame(usernamefield.getText(), passfield.getText());
                    a.setLocationRelativeTo(this);
                    a.setVisible(true);
                    this.dispose();

                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "INVALID!");
                System.out.println(e);
            }
        } else {
            try (Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultset = statement.executeQuery("SELECT username, password FROM CUSTOMER WHERE username='" + usernamefield.getText() + "' AND password= BINARY '" + passfield.getText() + "'");) {

                resultset.next();
                String username = resultset.getString(1);
                String password = resultset.getString(2);
                if (username.equalsIgnoreCase(usernamefield.getText()) && password.equals(passfield.getText())) {
                    WansesFrame a = new WansesFrame(usernamefield.getText(), passfield.getText());
                    a.setLocationRelativeTo(this);
                    a.setVisible(true);
                    this.dispose();

                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "INVALID!");
                System.out.println(e);
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        LoginPanel.setVisible(false);
        RegisterPanel.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void registerbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerbuttonActionPerformed

        String firstname = regfirstname.getText();
        String lastname = reglastname.getText();
        String phonenumber = regphonenumber.getText();
        Double income = Double.parseDouble(regincome.getText());
        int membership = Double.parseDouble(regincome.getText()) >= 15000 ? 3 : (Double.parseDouble(regincome.getText()) >= 7500 ? 2 : 1);
        String address = regaddress.getText();
        String username = regusername.getText();
        String password = regpassword.getText();
        int regchecking;
        int regsaving;
        int regcustid;

        try {
            // create a java mysql database connection
            Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            //Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/wanses", "wans", "123");
            Statement statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,PHONENUMBER,INCOME,MEMBERSHIP,ADDRESS, USERNAME, PASSWORD) "
                    + "VALUES('" + firstname + "','" + lastname + "'"
                    + ",'" + phonenumber + "','" + income + "',"
                    + membership + ",'" + address + "','" + username + "','"
                    + password + "')");

            ResultSet resultset = statement.executeQuery("SELECT CUST_ID FROM CUSTOMER WHERE USERNAME='" + username + "'");
            resultset.next();
            regcustid = resultset.getInt(1);

            statement.executeUpdate("INSERT INTO ACCOUNT (ACCTYPE,BALANCE,CUST_ID) VALUES( 1 , 0 ," + regcustid + ")");
            //regchecking = (statement.executeQuery("SELECT ACC_ID FROM ACCOUNT WHERE CUST_ID="+regcustid+" AND ACCTYPE=1")).getInt(1);
            statement.executeUpdate("INSERT INTO ACCOUNT (ACCTYPE,BALANCE,CUST_ID) VALUES( 2 , 0 ," + regcustid + ")");
            //regsaving = (statement.executeQuery("SELECT ACC_ID FROM ACCOUNT WHERE CUST_ID="+regcustid+" AND ACCTYPE=2")).getInt(1);

            JOptionPane.showMessageDialog(this, "You registered successfully!");
            regfirstname.setText("");
            reglastname.setText("");
            regphonenumber.setText("");
            regincome.setText("");
            regaddress.setText("");
            regusername.setText("");
            regpassword.setText("");
            LoginPanel.setVisible(true);
            RegisterPanel.setVisible(false);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e);
            //System.out.println(e);
        }

    }//GEN-LAST:event_registerbuttonActionPerformed

    private void cancelbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbuttonActionPerformed
        LoginPanel.setVisible(true);
        RegisterPanel.setVisible(false);
    }//GEN-LAST:event_cancelbuttonActionPerformed

    private void usernamefieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernamefieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton1.doClick();
        }
    }//GEN-LAST:event_usernamefieldKeyPressed

    private void regusernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_regusernameKeyTyped
                if (regusername.getText().length() >= 20 || evt.getKeyChar()==KeyEvent.VK_SPACE)
                evt.consume();
                if (!(Character.isLetterOrDigit(evt.getKeyChar()) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_UNDERSCORE )) 
                evt.consume();

    }//GEN-LAST:event_regusernameKeyTyped

    private void regpasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_regpasswordKeyTyped
                if (regpassword.getText().length() >= 20)
                evt.consume();
    }//GEN-LAST:event_regpasswordKeyTyped

    private void regfirstnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_regfirstnameKeyTyped
                if (regfirstname.getText().length() >= 25 || evt.getKeyChar()==KeyEvent.VK_SPACE)
                evt.consume();
    }//GEN-LAST:event_regfirstnameKeyTyped

    private void reglastnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reglastnameKeyTyped
                if (reglastname.getText().length() >= 25 || evt.getKeyChar()==KeyEvent.VK_SPACE)
                evt.consume();
    }//GEN-LAST:event_reglastnameKeyTyped

    private void regaddressKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_regaddressKeyTyped
                if (regaddress.getText().length() >= 40)
                evt.consume();
    }//GEN-LAST:event_regaddressKeyTyped

    private void regphonenumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_regphonenumberKeyTyped
                if (regphonenumber.getText().length() >= 10 || evt.getKeyChar()==KeyEvent.VK_SPACE)
                evt.consume();
                if (!(Character.isDigit(evt.getKeyChar()) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_DELETE )) 
                evt.consume();

    }//GEN-LAST:event_regphonenumberKeyTyped

    private void regincomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_regincomeKeyTyped
                if (regincome.getText().length() >= 10 )
                evt.consume();
                if (!(Character.isDigit(evt.getKeyChar()) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_PERIOD )) 
            evt.consume();
    }//GEN-LAST:event_regincomeKeyTyped

    private void usernamefieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernamefieldKeyTyped
                if (usernamefield.getText().length() >= 20)
                evt.consume();
    }//GEN-LAST:event_usernamefieldKeyTyped

    private void passfieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passfieldKeyTyped
                if (passfield.getText().length() >= 20)
                    evt.consume();
    }//GEN-LAST:event_passfieldKeyTyped

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
            java.util.logging.Logger.getLogger(WansesLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WansesLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WansesLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WansesLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        // String theUserNameField=" ";
        // theUserNameField.valueOf(usernamefield.getText());  
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WansesLogin().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LoginPanel;
    private javax.swing.JPanel RegisterPanel;
    private javax.swing.JButton cancelbutton;
    private javax.swing.JPanel container;
    private javax.swing.JLabel fullname;
    private javax.swing.JLabel incomelabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField passfield;
    private javax.swing.JLabel passlabel;
    private javax.swing.JLabel passlabel1;
    private javax.swing.JTextField regaddress;
    private javax.swing.JTextField regfirstname;
    private javax.swing.JTextField regincome;
    private javax.swing.JButton registerbutton;
    private javax.swing.JTextField reglastname;
    private javax.swing.JLabel reglastnamelabel;
    private javax.swing.JTextField regpassword;
    private javax.swing.JTextField regphonenumber;
    private javax.swing.JTextField regusername;
    private javax.swing.JLabel userlabel;
    private javax.swing.JLabel userlabel1;
    private javax.swing.JTextField usernamefield;
    // End of variables declaration//GEN-END:variables
}
