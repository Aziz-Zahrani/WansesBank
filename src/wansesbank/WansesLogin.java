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
import java.util.Random;
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
    private static final String CONN_STRING= "jdbc:mysql://172.105.47.42:3306/wansesco_wanses";
    private final String char_list = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_";
    int mouseX, mouseY;
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
        mini = new javax.swing.JButton();
        close = new javax.swing.JButton();
        headers = new javax.swing.JLabel();
        LoginPanel = new javax.swing.JPanel();
        usernamefield = new javax.swing.JTextField();
        passlabel = new javax.swing.JLabel();
        userlabel = new javax.swing.JLabel();
        passfield = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(480, 290));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(480, 290));
        setResizable(false);
        setSize(new java.awt.Dimension(480, 290));
        getContentPane().setLayout(null);

        container.setLayout(null);

        mini.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/minimize.png"))); // NOI18N
        mini.setBorder(null);
        mini.setBorderPainted(false);
        mini.setContentAreaFilled(false);
        mini.setFocusPainted(false);
        mini.setRolloverEnabled(true);
        mini.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/minimizepressed.png"))); // NOI18N
        mini.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miniActionPerformed(evt);
            }
        });
        container.add(mini);
        mini.setBounds(440, 0, 20, 20);

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/close.png"))); // NOI18N
        close.setBorder(null);
        close.setBorderPainted(false);
        close.setContentAreaFilled(false);
        close.setFocusPainted(false);
        close.setRolloverEnabled(true);
        close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/closepressed.png"))); // NOI18N
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });
        container.add(close);
        close.setBounds(460, 0, 20, 20);

        headers.setBackground(new java.awt.Color(44, 62, 78));
        headers.setOpaque(true);
        headers.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headersMouseDragged(evt);
            }
        });
        headers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headersMousePressed(evt);
            }
        });
        container.add(headers);
        headers.setBounds(0, 0, 480, 22);

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
        usernamefield.setBounds(70, 110, 220, 24);

        passlabel.setForeground(new java.awt.Color(51, 51, 51));
        passlabel.setText("Password:");
        LoginPanel.add(passlabel);
        passlabel.setBounds(70, 140, 61, 16);

        userlabel.setForeground(new java.awt.Color(51, 51, 51));
        userlabel.setText("Username:");
        LoginPanel.add(userlabel);
        userlabel.setBounds(70, 90, 62, 16);

        passfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passfieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passfieldKeyTyped(evt);
            }
        });
        LoginPanel.add(passfield);
        passfield.setBounds(70, 160, 220, 25);

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        LoginPanel.add(jButton1);
        jButton1.setBounds(120, 210, 110, 25);

        jPanel2.setBackground(new java.awt.Color(240, 240, 240));

        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Not a member?");
        jPanel2.add(jLabel2);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(49, 78, 110));
        jButton2.setText("Register now");
        jButton2.setToolTipText(""); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        LoginPanel.add(jPanel2);
        jPanel2.setBounds(60, 240, 220, 20);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/loginlabel.png"))); // NOI18N
        LoginPanel.add(jLabel1);
        jLabel1.setBounds(0, 40, 360, 40);

        container.add(LoginPanel);
        LoginPanel.setBounds(120, 0, 360, 290);

        jPanel1.setBackground(new java.awt.Color(44, 62, 78));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 715));
        jPanel1.setLayout(null);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/logoside.png"))); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 30, 90, 240);

        container.add(jPanel1);
        jPanel1.setBounds(0, 0, 120, 290);

        getContentPane().add(container);
        container.setBounds(0, 0, 530, 290);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            WansesRegister e= new WansesRegister();
            e.setVisible(true);
            e.setLocationRelativeTo(this);
            this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void usernamefieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernamefieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton1.doClick();
        }
    }//GEN-LAST:event_usernamefieldKeyPressed

    private void usernamefieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernamefieldKeyTyped
                if (usernamefield.getText().length() >= 20)
                evt.consume();
    }//GEN-LAST:event_usernamefieldKeyTyped

    private void passfieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passfieldKeyTyped
                if (passfield.getText().length() >= 20)
                    evt.consume();
    }//GEN-LAST:event_passfieldKeyTyped

    private void passfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passfieldKeyPressed
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton1.doClick();
        }
    }//GEN-LAST:event_passfieldKeyPressed

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

    private void headersMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headersMouseDragged
        int corX = evt.getXOnScreen();
        int corY = evt.getYOnScreen();
        
        this.setLocation(corX-mouseX, corY-mouseY);
    }//GEN-LAST:event_headersMouseDragged

    private void headersMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headersMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
        
        
    }//GEN-LAST:event_headersMousePressed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        dispose();
    }//GEN-LAST:event_closeActionPerformed

    private void miniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miniActionPerformed
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_miniActionPerformed

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
    private javax.swing.JButton close;
    private javax.swing.JPanel container;
    private javax.swing.JLabel headers;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton mini;
    private javax.swing.JPasswordField passfield;
    private javax.swing.JLabel passlabel;
    private javax.swing.JLabel userlabel;
    private javax.swing.JTextField usernamefield;
    // End of variables declaration//GEN-END:variables
}
