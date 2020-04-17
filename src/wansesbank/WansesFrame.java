/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wansesbank;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wans
 */
public class WansesFrame extends javax.swing.JFrame {

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    SimpleDateFormat formattert = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    Customer user = new Customer();
    Account checking = new Account();
    Account saving = new Account();
    int[] benarray;
    String[] fullnamearray;
    private static  final String USERNAME= "wansesco_wans";
    private static final String PASSWORD= "xzMAsW1WQ8Lg";
    private static final String CONN_STRING= "jdbc:mysql://wanses.com:3306/wansesco_wanses";
    
    public void retrieveUser(){
                try (
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("SELECT * FROM CUSTOMER WHERE username='" + user.getUsername() + "' and password='" + user.getPassword() + "'");) {

            ResultSetMetaData meta = resultset.getMetaData();
            
            resultset.next();
            user.setCust_ID(resultset.getInt(1));
            user.setFirstname(resultset.getString(2));
            user.setLastname(resultset.getString(3));
            user.setAddress(resultset.getString(4));
            user.setUsername(resultset.getString(5));
            user.setPassword(resultset.getString(6));
            user.setPhonenumber(resultset.getString(7));
            user.setMembership(resultset.getInt(8));
            user.setIncome(resultset.getDouble(9));
            user.setLoan_ID(resultset.getInt(10));
        } catch (SQLException e) {
            System.out.println(e);
        }
        try (
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("SELECT * FROM ACCOUNT WHERE Cust_ID=" + user.getCust_ID());) {

            ResultSetMetaData meta = resultset.getMetaData();
            while (resultset.next()) {
                if (resultset.getInt(2) == 1) {
                    checking.setAcc_ID(resultset.getInt(1));
                    checking.setAcctype(resultset.getInt(2));
                    checking.setBalance(resultset.getDouble(3));
                    checking.setCust_ID(resultset.getInt(4));
                } else if (resultset.getInt(2) == 2) {
                    saving.setAcc_ID(resultset.getInt(1));
                    saving.setAcctype(resultset.getInt(2));
                    saving.setBalance(resultset.getDouble(3));
                    saving.setCust_ID(resultset.getInt(4));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void updateBalance(){
                try (
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("SELECT * FROM ACCOUNT WHERE Cust_ID=" + user.getCust_ID());) {

            ResultSetMetaData meta = resultset.getMetaData();
            while (resultset.next()) {
                if (resultset.getInt(2) == 1) {
                    checking.setAcc_ID(resultset.getInt(1));
                    checking.setAcctype(resultset.getInt(2));
                    checking.setBalance(resultset.getDouble(3));
                    checking.setCust_ID(resultset.getInt(4));
                } else if (resultset.getInt(2) == 2) {
                    saving.setAcc_ID(resultset.getInt(1));
                    saving.setAcctype(resultset.getInt(2));
                    saving.setBalance(resultset.getDouble(3));
                    saving.setCust_ID(resultset.getInt(4));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
     balancecashlabel2.setText(String.format("%.2f", checking.getBalance()));
     balancecashlabel1.setText(String.format("%.2f", checking.getBalance()));
     balancecashlabel.setText(String.format("%.2f", saving.getBalance()));
     balancecashlabel4.setText(String.format("%.2f", saving.getBalance()));
     balancecashlabel3.setText(String.format("%.2f", checking.getBalance()));
       }
    public void initSummary(){
        try {
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("select TRANSACTION_ID, SENDER_NAME, AMOUNT, RECIEVER_NAME FROM TRANSACTS WHERE RECIEVER_ID="+checking.getAcc_ID()+" OR SENDER_ID="+checking.getAcc_ID()+" OR RECIEVER_ID="+saving.getAcc_ID()+" ORDER BY TRANSACTION_ID DESC");
            //ResultSetMetaData meta = resultset.getMetaData();
            
            String []data= new String[4];
            DefaultTableModel model = (DefaultTableModel)summary.getModel();
            model.setRowCount(0);
                for (int i = 0; i < 5; i++) {
                    if(resultset.next()){
                    data[0]= resultset.getString(1);
                    data[1]= resultset.getString(2);
                    data[2]= String.format("%.2f",resultset.getDouble(3));
                    data[3]= resultset.getString(4);
                    model.addRow(data);
                    }else
                        break;
            }
        
        //resultset2.next();
        
          //System.out.println(resultset.getString(1));

  
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void loanFront(){
        double loanam = user.getIncome()*24*0.2;
        double berate = user.getMembership()==1?0.05:user.getMembership()==2?0.035:user.getMembership()==3?0.2:0.5;
        benrate.setText(String.format("%.2f",berate*100)+"%");
        jLabel25.setText(String.format("%.2f",loanam));
        benamount.setText(String.format("%.2f",loanam*berate)+" SAR");
        totalwithrate.setText(String.format("%.2f",loanam+loanam*berate)+" SAR");
        monthpay.setText(String.format("%.2f",(loanam+loanam*berate)/24)+" SAR");
    }
    public void loanDebt(){
                try {
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("SELECT LOAN.Loan_ID, Amount, Monthly, LDate FROM LOAN INNER JOIN CUSTOMER WHERE LOAN.Loan_ID= CUSTOMER.Loan_ID AND Cust_ID="+user.getCust_ID());
                resultset.next();
        debtid.setText(resultset.getString(1));
        debttotall.setText(resultset.getString(2)+" SAR");
        debtmonth.setText(resultset.getString(3)+" SAR");
        debtdate.setText(resultset.getString(4));
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e);
        }
    }
    public void loanCheck(){
                try (
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("SELECT LOAN.Loan_ID, Amount, Monthly, LDate FROM CUSTOMER INNER JOIN LOAN WHERE Cust_ID=" + user.getCust_ID()+" AND CUSTOMER.Loan_ID = LOAN.Loan_ID");) {
 
            if(resultset.next()){
                if(resultset.getDouble(2)<=0){
                statement.executeUpdate("DELETE FROM LOAN WHERE Loan_ID="+resultset.getInt(1));
                JOptionPane.showMessageDialog(null, "Your loan has been paid fully!");
                loanFront();
                debt.setVisible(false);
                front.setVisible(true);
                return;
                }
                loanDebt();
                debt.setVisible(true);
                front.setVisible(false);
            }
            else{
                loanFront();
                debt.setVisible(false);
                front.setVisible(true);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e);
        }
    }
    public WansesFrame() {
        initComponents();

    }

    public WansesFrame(String Username, String Password) {

        user.setUsername(Username);
        user.setPassword(Password);
        retrieveUser();
        initComponents();
        initSummary();
        URL iconURL = getClass().getResource("/wansesbank/images/appicon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentpanel = new javax.swing.JPanel();
        dashboard = new javax.swing.JLayeredPane();
        mainpanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        logindate = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        balancecashlabel = new javax.swing.JLabel();
        SARlabel = new javax.swing.JLabel();
        balancelabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        summary = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        briefsummarylabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        balancelabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        balancecashlabel1 = new javax.swing.JLabel();
        SARlabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        depositbutton1 = new javax.swing.JButton();
        withdrawbutton1 = new javax.swing.JButton();
        SARlabel3 = new javax.swing.JLabel();
        transactfield = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        withdrawsuccess = new javax.swing.JLabel();
        depositsuccess = new javax.swing.JLabel();
        balancelabel4 = new javax.swing.JLabel();
        accountpanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        logindate1 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        changepass = new javax.swing.JButton();
        passhow = new javax.swing.JToggleButton();
        savechange = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        oldpass = new javax.swing.JTextField();
        newpass = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        usernamepanel = new javax.swing.JPanel();
        usern = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        passpanel = new javax.swing.JPanel();
        passw = new javax.swing.JLabel();
        passww = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        editinfo = new javax.swing.JButton();
        saveinfo = new javax.swing.JButton();
        cancelinfo = new javax.swing.JButton();
        refreshbut = new javax.swing.JButton();
        firstnamepanel = new javax.swing.JPanel();
        firstn = new javax.swing.JLabel();
        firstnamedata = new javax.swing.JLabel();
        firstnameupdate = new javax.swing.JTextField();
        lastnamepanel = new javax.swing.JPanel();
        lastn = new javax.swing.JLabel();
        lastnamedata = new javax.swing.JLabel();
        lastnameupdate = new javax.swing.JTextField();
        custidpanel = new javax.swing.JPanel();
        custid = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        phonepanel = new javax.swing.JPanel();
        phonen = new javax.swing.JLabel();
        phonenumberdata = new javax.swing.JLabel();
        phonenumberupdate = new javax.swing.JTextField();
        addresspanel = new javax.swing.JPanel();
        addres = new javax.swing.JLabel();
        addressdata = new javax.swing.JLabel();
        addressupdate = new javax.swing.JTextField();
        incomepanel = new javax.swing.JPanel();
        incomee = new javax.swing.JLabel();
        incomedata = new javax.swing.JLabel();
        incomeupdate = new javax.swing.JTextField();
        membershippanel = new javax.swing.JPanel();
        member = new javax.swing.JLabel();
        membershipdata = new javax.swing.JLabel();
        loanpanel = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        logindate3 = new javax.swing.JLabel();
        debt = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        debttotall = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        debtid = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        debtdate = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        debtmonth = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        fulldebtpayment = new javax.swing.JButton();
        front = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        benrate = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        benamount = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        totalwithrate = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        monthpay = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        transactionpanel = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        logindate2 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        tobenfit = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        balancelabel3 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        balancecashlabel2 = new javax.swing.JLabel();
        SARlabel2 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        reciever = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel27 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        totaltrans = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        sendtrans = new javax.swing.JToggleButton();
        jLabel34 = new javax.swing.JLabel();
        betweenmyacc = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        balancelabel2 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        balancecashlabel3 = new javax.swing.JLabel();
        SARlabel4 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        balancecashlabel4 = new javax.swing.JLabel();
        SARlabel5 = new javax.swing.JLabel();
        balancelabel5 = new javax.swing.JLabel();
        transactfield1 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        addben = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        logindate4 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        found = new javax.swing.JLabel();
        notfound = new javax.swing.JLabel();
        benpanel1 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        benname = new javax.swing.JLabel();
        benpanel2 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        benacclabel = new javax.swing.JLabel();
        benconfirmpanel = new javax.swing.JPanel();
        benconfirm = new javax.swing.JLabel();
        benyes = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        benacc = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        leftboard = new javax.swing.JLayeredPane();
        leftpan = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        signout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User Panel");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(845, 630));
        setResizable(false);
        setSize(new java.awt.Dimension(850, 630));
        getContentPane().setLayout(null);

        contentpanel.setBackground(new java.awt.Color(255, 255, 255));
        contentpanel.setLayout(null);

        mainpanel.setBackground(new java.awt.Color(240, 240, 240));
        mainpanel.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(70, 94, 114));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 2, 0, new java.awt.Color(50, 72, 92)));
        jPanel1.setLayout(null);

        jLabel4.setFont(new java.awt.Font("HelvLight", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/labels/dashboardlabelwhite.png"))); // NOI18N
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 25, 200, 47);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(248, 248, 248)));
        jPanel6.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Gill Sans MT", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Welcome back, "+user.getFirstname()+" "+ user.getLastname());
        jPanel6.add(jLabel2);
        jLabel2.setBounds(20, 10, 550, 30);

        logindate.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        logindate.setForeground(new java.awt.Color(153, 153, 153));
        logindate.setText("Logged in at "+ formatter.format(date));
        jPanel6.add(logindate);
        logindate.setBounds(25, 35, 220, 16);

        jPanel1.add(jPanel6);
        jPanel6.setBounds(0, 90, 590, 60);

        mainpanel.add(jPanel1);
        jPanel1.setBounds(0, 0, 590, 150);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(236, 236, 236)));
        jPanel2.setForeground(new java.awt.Color(165, 165, 165));
        jPanel2.setLayout(null);

        jPanel3.setOpaque(false);

        balancecashlabel.setFont(new java.awt.Font("Lato", 0, 24)); // NOI18N
        balancecashlabel.setForeground(new java.awt.Color(28, 28, 28));
        balancecashlabel.setText(String.format("%.2f", saving.getBalance()));
        jPanel3.add(balancecashlabel);

        SARlabel.setForeground(new java.awt.Color(102, 102, 102));
        SARlabel.setText("SAR");
        jPanel3.add(SARlabel);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(10, 30, 250, 30);

        balancelabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        balancelabel.setForeground(new java.awt.Color(44, 62, 78));
        balancelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        balancelabel.setText("Saving Account: "+saving.getAcc_ID());
        jPanel2.add(balancelabel);
        balancelabel.setBounds(10, 10, 250, 24);

        mainpanel.add(jPanel2);
        jPanel2.setBounds(300, 160, 270, 80);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(236, 236, 236)));
        jPanel5.setLayout(null);

        summary.setForeground(new java.awt.Color(52, 92, 118));
        summary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction ID", "From", "Amount", "To"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        summary.setGridColor(new java.awt.Color(255, 255, 255));
        summary.setIntercellSpacing(new java.awt.Dimension(0, 0));
        summary.setRowHeight(20);
        summary.setSelectionBackground(new java.awt.Color(75, 112, 136));
        jScrollPane1.setViewportView(summary);
        if (summary.getColumnModel().getColumnCount() > 0) {
            summary.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        jPanel5.add(jScrollPane1);
        jScrollPane1.setBounds(10, 40, 540, 130);

        jButton2.setText("Full Summary");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2);
        jButton2.setBounds(220, 180, 110, 20);

        briefsummarylabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        briefsummarylabel.setForeground(new java.awt.Color(44, 62, 78));
        briefsummarylabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        briefsummarylabel.setText("Breif Summary");
        jPanel5.add(briefsummarylabel);
        briefsummarylabel.setBounds(10, 6, 540, 24);

        mainpanel.add(jPanel5);
        jPanel5.setBounds(10, 250, 560, 210);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(236, 236, 236)));
        jPanel7.setForeground(new java.awt.Color(165, 165, 165));
        jPanel7.setLayout(null);

        balancelabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        balancelabel1.setForeground(new java.awt.Color(44, 62, 78));
        balancelabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        balancelabel1.setText("Checking Account: "+checking.getAcc_ID());
        jPanel7.add(balancelabel1);
        balancelabel1.setBounds(10, 10, 260, 24);

        jPanel8.setOpaque(false);

        balancecashlabel1.setFont(new java.awt.Font("Lato", 0, 24)); // NOI18N
        balancecashlabel1.setForeground(new java.awt.Color(28, 28, 28));
        balancecashlabel1.setText(String.format("%.2f", checking.getBalance()));
        jPanel8.add(balancecashlabel1);

        SARlabel1.setForeground(new java.awt.Color(102, 102, 102));
        SARlabel1.setText("SAR");
        jPanel8.add(SARlabel1);

        jPanel7.add(jPanel8);
        jPanel8.setBounds(10, 30, 260, 30);

        mainpanel.add(jPanel7);
        jPanel7.setBounds(10, 160, 280, 80);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(236, 236, 236)));
        jPanel9.setLayout(null);

        depositbutton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/dashboard/deposi.png"))); // NOI18N
        depositbutton1.setToolTipText("Deposit");
        depositbutton1.setBorder(null);
        depositbutton1.setBorderPainted(false);
        depositbutton1.setContentAreaFilled(false);
        depositbutton1.setFocusable(false);
        depositbutton1.setRolloverEnabled(true);
        depositbutton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/dashboard/deposipressed.png"))); // NOI18N
        depositbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositbutton1ActionPerformed(evt);
            }
        });
        jPanel9.add(depositbutton1);
        depositbutton1.setBounds(420, 87, 30, 30);

        withdrawbutton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/dashboard/with.png"))); // NOI18N
        withdrawbutton1.setToolTipText("Withdraw");
        withdrawbutton1.setBorder(null);
        withdrawbutton1.setBorderPainted(false);
        withdrawbutton1.setContentAreaFilled(false);
        withdrawbutton1.setFocusable(false);
        withdrawbutton1.setRolloverEnabled(true);
        withdrawbutton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/dashboard/withpressed.png"))); // NOI18N
        withdrawbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawbutton1ActionPerformed(evt);
            }
        });
        jPanel9.add(withdrawbutton1);
        withdrawbutton1.setBounds(360, 90, 30, 30);

        SARlabel3.setForeground(new java.awt.Color(102, 102, 102));
        SARlabel3.setText("SAR");
        jPanel9.add(SARlabel3);
        SARlabel3.setBounds(500, 60, 24, 16);

        transactfield.setFont(new java.awt.Font("Lato", 0, 24)); // NOI18N
        transactfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        transactfield.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(226, 226, 226)));
        transactfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                transactfieldKeyTyped(evt);
            }
        });
        jPanel9.add(transactfield);
        transactfield.setBounds(280, 30, 250, 50);

        jLabel8.setText("Account: ");
        jPanel9.add(jLabel8);
        jLabel8.setBounds(20, 45, 60, 20);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Checking", "Saving" }));
        jComboBox2.setBorder(null);
        jComboBox2.setFocusable(false);
        jPanel9.add(jComboBox2);
        jComboBox2.setBounds(80, 45, 170, 20);

        withdrawsuccess.setForeground(new java.awt.Color(208, 0, 0));
        withdrawsuccess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        withdrawsuccess.setText("You withdrawed 100 SAR");
        withdrawsuccess.setVisible(false);
        jPanel9.add(withdrawsuccess);
        withdrawsuccess.setBounds(20, 90, 220, 16);

        depositsuccess.setForeground(new java.awt.Color(0, 102, 51));
        depositsuccess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        depositsuccess.setText("You deposited 100 SAR");
        depositsuccess.setVisible(false);
        jPanel9.add(depositsuccess);
        depositsuccess.setBounds(20, 90, 220, 16);

        balancelabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        balancelabel4.setForeground(new java.awt.Color(44, 62, 78));
        balancelabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        balancelabel4.setText("Deposit / Withdraw");
        jPanel9.add(balancelabel4);
        balancelabel4.setBounds(20, 10, 240, 18);

        mainpanel.add(jPanel9);
        jPanel9.setBounds(10, 470, 560, 130);

        dashboard.add(mainpanel);
        mainpanel.setBounds(0, 0, 580, 600);

        accountpanel.setBackground(new java.awt.Color(240, 240, 240));
        accountpanel.setLayout(null);

        jPanel4.setBackground(new java.awt.Color(70, 94, 114));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 2, 0, new java.awt.Color(50, 72, 92)));
        jPanel4.setLayout(null);

        jLabel9.setFont(new java.awt.Font("HelvLight", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/labels/accountlabelwhite.png"))); // NOI18N
        jPanel4.add(jLabel9);
        jLabel9.setBounds(20, 25, 200, 47);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(248, 248, 248)));
        jPanel10.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Gill Sans MT", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Welcome back, "+user.getFirstname()+" "+ user.getLastname());
        jPanel10.add(jLabel10);
        jLabel10.setBounds(20, 10, 550, 30);

        logindate1.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        logindate1.setForeground(new java.awt.Color(153, 153, 153));
        logindate1.setText("Logged in at "+ formatter.format(date));
        jPanel10.add(logindate1);
        logindate1.setBounds(25, 35, 220, 16);

        jPanel4.add(jPanel10);
        jPanel10.setBounds(0, 90, 590, 60);

        accountpanel.add(jPanel4);
        jPanel4.setBounds(0, 0, 590, 150);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(236, 236, 236)));
        jPanel14.setLayout(null);

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel23.setText("Login information");
        jPanel14.add(jLabel23);
        jLabel23.setBounds(30, 10, 130, 19);

        changepass.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        changepass.setText("Change");
        changepass.setFocusable(false);
        changepass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changepassActionPerformed(evt);
            }
        });
        jPanel14.add(changepass);
        changepass.setBounds(370, 70, 70, 20);

        passhow.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        passhow.setText("Show");
        passhow.setFocusable(false);
        passhow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passhowActionPerformed(evt);
            }
        });
        jPanel14.add(passhow);
        passhow.setBounds(290, 70, 70, 20);

        savechange.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        savechange.setText("Save");
        savechange.setFocusable(false);
        savechange.setVisible(false);
        savechange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savechangeActionPerformed(evt);
            }
        });
        jPanel14.add(savechange);
        savechange.setBounds(290, 40, 70, 20);

        cancel.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        cancel.setText("Cancel");
        cancel.setVisible(false);
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        jPanel14.add(cancel);
        cancel.setBounds(370, 40, 70, 20);

        oldpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oldpassActionPerformed(evt);
            }
        });
        oldpass.setVisible(false);
        jPanel14.add(oldpass);
        oldpass.setBounds(160, 70, 110, 20);

        newpass.setVisible(false);
        jPanel14.add(newpass);
        newpass.setBounds(380, 70, 110, 20);

        jLabel17.setText("Current Password: ");
        jLabel17.setVisible(false);
        jPanel14.add(jLabel17);
        jLabel17.setBounds(60, 70, 110, 20);

        jLabel22.setText("New Password: ");
        jLabel22.setVisible(false);
        jPanel14.add(jLabel22);
        jLabel22.setBounds(290, 70, 100, 20);

        usernamepanel.setBackground(new java.awt.Color(255, 255, 255));
        usernamepanel.setLayout(new javax.swing.BoxLayout(usernamepanel, javax.swing.BoxLayout.LINE_AXIS));

        usern.setText("Username: ");
        usernamepanel.add(usern);

        jLabel21.setText(user.getUsername());
        usernamepanel.add(jLabel21);

        jPanel14.add(usernamepanel);
        usernamepanel.setBounds(60, 40, 210, 20);

        passpanel.setBackground(new java.awt.Color(255, 255, 255));
        passpanel.setLayout(new javax.swing.BoxLayout(passpanel, javax.swing.BoxLayout.LINE_AXIS));

        passw.setText("Password: ");
        passpanel.add(passw);

        passww.setText("*****");
        passpanel.add(passww);

        jPanel14.add(passpanel);
        passpanel.setBounds(60, 70, 210, 20);

        accountpanel.add(jPanel14);
        jPanel14.setBounds(10, 160, 560, 110);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(null);

        jLabel26.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel26.setText("Personal Information");
        jPanel13.add(jLabel26);
        jLabel26.setBounds(30, 10, 180, 19);

        editinfo.setText("Edit");
        editinfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editinfoActionPerformed(evt);
            }
        });
        jPanel13.add(editinfo);
        editinfo.setBounds(240, 220, 100, 25);

        saveinfo.setText("Save");
        saveinfo.setVisible(false);
        saveinfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveinfoActionPerformed(evt);
            }
        });
        jPanel13.add(saveinfo);
        saveinfo.setBounds(180, 220, 100, 25);

        cancelinfo.setText("Cancel");
        cancelinfo.setVisible(false);
        cancelinfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelinfoActionPerformed(evt);
            }
        });
        jPanel13.add(cancelinfo);
        cancelinfo.setBounds(300, 220, 100, 25);

        refreshbut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/refresh.png"))); // NOI18N
        refreshbut.setBorder(null);
        refreshbut.setBorderPainted(false);
        refreshbut.setContentAreaFilled(false);
        refreshbut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refreshbut.setFocusable(false);
        refreshbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshbutActionPerformed(evt);
            }
        });
        jPanel13.add(refreshbut);
        refreshbut.setBounds(526, 10, 24, 24);

        firstnamepanel.setBackground(new java.awt.Color(248, 248, 248));
        firstnamepanel.setLayout(new javax.swing.BoxLayout(firstnamepanel, javax.swing.BoxLayout.LINE_AXIS));

        firstn.setBackground(new java.awt.Color(102, 102, 102));
        firstn.setForeground(new java.awt.Color(102, 102, 102));
        firstn.setText(" First Name: ");
        firstnamepanel.add(firstn);

        firstnamedata.setForeground(new java.awt.Color(0, 0, 0));
        firstnamedata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        firstnamedata.setText(user.getFirstname());
        firstnamepanel.add(firstnamedata);

        firstnameupdate.setColumns(12);
        firstnameupdate.setText(user.getFirstname());
        firstnameupdate.setVisible(false);
        firstnameupdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                firstnameupdateKeyTyped(evt);
            }
        });
        firstnamepanel.add(firstnameupdate);

        jPanel13.add(firstnamepanel);
        firstnamepanel.setBounds(30, 50, 240, 20);

        lastnamepanel.setBackground(new java.awt.Color(248, 248, 248));
        lastnamepanel.setLayout(new javax.swing.BoxLayout(lastnamepanel, javax.swing.BoxLayout.LINE_AXIS));

        lastn.setBackground(new java.awt.Color(102, 102, 102));
        lastn.setForeground(new java.awt.Color(102, 102, 102));
        lastn.setText(" Last Name: ");
        lastnamepanel.add(lastn);

        lastnamedata.setForeground(new java.awt.Color(0, 0, 0));
        lastnamedata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lastnamedata.setText(user.getLastname());
        lastnamepanel.add(lastnamedata);

        lastnameupdate.setText(user.getLastname());
        lastnameupdate.setVisible(false);
        lastnameupdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lastnameupdateKeyTyped(evt);
            }
        });
        lastnamepanel.add(lastnameupdate);

        jPanel13.add(lastnamepanel);
        lastnamepanel.setBounds(290, 50, 240, 20);

        custidpanel.setBackground(new java.awt.Color(248, 248, 248));
        custidpanel.setLayout(new javax.swing.BoxLayout(custidpanel, javax.swing.BoxLayout.LINE_AXIS));

        custid.setBackground(new java.awt.Color(102, 102, 102));
        custid.setForeground(new java.awt.Color(102, 102, 102));
        custid.setText(" Customer ID: ");
        custidpanel.add(custid);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText(String.valueOf(user.getCust_ID()));
        custidpanel.add(jLabel12);

        jPanel13.add(custidpanel);
        custidpanel.setBounds(30, 90, 240, 20);

        phonepanel.setBackground(new java.awt.Color(248, 248, 248));
        phonepanel.setLayout(new javax.swing.BoxLayout(phonepanel, javax.swing.BoxLayout.LINE_AXIS));

        phonen.setBackground(new java.awt.Color(102, 102, 102));
        phonen.setForeground(new java.awt.Color(102, 102, 102));
        phonen.setText(" Phone Number: ");
        phonepanel.add(phonen);

        phonenumberdata.setForeground(new java.awt.Color(0, 0, 0));
        phonenumberdata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        phonenumberdata.setText(user.getPhonenumber());
        phonepanel.add(phonenumberdata);

        phonenumberupdate.setText(user.getPhonenumber());
        phonenumberupdate.setVisible(false);
        phonenumberupdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phonenumberupdateKeyTyped(evt);
            }
        });
        phonepanel.add(phonenumberupdate);

        jPanel13.add(phonepanel);
        phonepanel.setBounds(290, 90, 240, 20);

        addresspanel.setBackground(new java.awt.Color(248, 248, 248));
        addresspanel.setLayout(new javax.swing.BoxLayout(addresspanel, javax.swing.BoxLayout.LINE_AXIS));

        addres.setBackground(new java.awt.Color(102, 102, 102));
        addres.setForeground(new java.awt.Color(102, 102, 102));
        addres.setText(" Address: ");
        addresspanel.add(addres);

        addressdata.setForeground(new java.awt.Color(0, 0, 0));
        addressdata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        addressdata.setText(user.getAddress());
        addresspanel.add(addressdata);

        addressupdate.setText(user.getAddress());
        addressupdate.setVisible(false);
        addressupdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                addressupdateKeyTyped(evt);
            }
        });
        addresspanel.add(addressupdate);

        jPanel13.add(addresspanel);
        addresspanel.setBounds(30, 130, 240, 20);

        incomepanel.setBackground(new java.awt.Color(248, 248, 248));
        incomepanel.setLayout(new javax.swing.BoxLayout(incomepanel, javax.swing.BoxLayout.LINE_AXIS));

        incomee.setBackground(new java.awt.Color(102, 102, 102));
        incomee.setForeground(new java.awt.Color(102, 102, 102));
        incomee.setText(" Income: ");
        incomepanel.add(incomee);

        incomedata.setForeground(new java.awt.Color(0, 0, 0));
        incomedata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        incomedata.setText(String.valueOf(user.getIncome()));
        incomepanel.add(incomedata);

        incomeupdate.setText(String.valueOf(user.getIncome()));
        incomeupdate.setVisible(false);
        incomeupdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                incomeupdateKeyTyped(evt);
            }
        });
        incomepanel.add(incomeupdate);

        jPanel13.add(incomepanel);
        incomepanel.setBounds(290, 130, 240, 20);

        membershippanel.setBackground(new java.awt.Color(248, 248, 248));
        membershippanel.setLayout(new javax.swing.BoxLayout(membershippanel, javax.swing.BoxLayout.LINE_AXIS));

        member.setBackground(new java.awt.Color(102, 102, 102));
        member.setForeground(new java.awt.Color(102, 102, 102));
        member.setText(" Membership: ");
        membershippanel.add(member);

        String mem="";
        if(user.getMembership()==1)
        mem="Silver";
        else if(user.getMembership()==2)
        mem="Gold";
        else
        mem="Diamond";
        membershipdata.setForeground(new java.awt.Color(0, 0, 0));
        membershipdata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        membershipdata.setText(mem);
        membershippanel.add(membershipdata);

        jPanel13.add(membershippanel);
        membershippanel.setBounds(30, 170, 240, 20);

        accountpanel.add(jPanel13);
        jPanel13.setBounds(10, 280, 560, 270);

        dashboard.add(accountpanel);
        accountpanel.setBounds(0, 0, 580, 600);

        loanpanel.setBackground(new java.awt.Color(240, 240, 240));
        loanpanel.setForeground(new java.awt.Color(240, 240, 240));
        loanpanel.setLayout(null);

        jPanel19.setBackground(new java.awt.Color(70, 94, 114));
        jPanel19.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 2, 0, new java.awt.Color(50, 72, 92)));
        jPanel19.setLayout(null);

        jLabel19.setFont(new java.awt.Font("HelvLight", 0, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/labels/loanlabelwhite.png"))); // NOI18N
        jPanel19.add(jLabel19);
        jLabel19.setBounds(20, 25, 200, 47);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(248, 248, 248)));
        jPanel20.setLayout(null);

        jLabel20.setFont(new java.awt.Font("Gill Sans MT", 0, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Welcome back, "+user.getFirstname()+" "+ user.getLastname());
        jPanel20.add(jLabel20);
        jLabel20.setBounds(20, 10, 550, 30);

        logindate3.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        logindate3.setForeground(new java.awt.Color(153, 153, 153));
        logindate3.setText("Logged in at "+ formatter.format(date));
        jPanel20.add(logindate3);
        logindate3.setBounds(25, 35, 220, 16);

        jPanel19.add(jPanel20);
        jPanel20.setBounds(0, 90, 590, 60);

        loanpanel.add(jPanel19);
        jPanel19.setBounds(0, 0, 590, 150);

        debt.setBackground(new java.awt.Color(255, 255, 255));
        debt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(241, 241, 241)));
        debt.setForeground(new java.awt.Color(255, 255, 255));
        debt.setLayout(null);

        jPanel34.setOpaque(false);

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(153, 0, 0));
        jLabel27.setText("In debt:");
        jPanel34.add(jLabel27);

        debttotall.setFont(new java.awt.Font("Lato", 0, 36)); // NOI18N
        debttotall.setForeground(new java.awt.Color(44, 62, 78));
        debttotall.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        debttotall.setText(String.format("%.2f",user.getIncome()*24*0.2));
        jPanel34.add(debttotall);

        jLabel45.setText("  SAR");
        jPanel34.add(jLabel45);

        debt.add(jPanel34);
        jPanel34.setBounds(10, 60, 530, 60);

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setOpaque(false);
        jPanel36.setLayout(new java.awt.GridLayout());

        jLabel49.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel49.setText("Loan ID: ");
        jPanel36.add(jLabel49);

        debtid.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        debtid.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        debtid.setText("null");
        jPanel36.add(debtid);

        debt.add(jPanel36);
        jPanel36.setBounds(80, 160, 380, 21);

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));
        jPanel41.setOpaque(false);
        jPanel41.setLayout(new java.awt.GridLayout());

        jLabel48.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel48.setText("Loan starting date: ");
        jPanel41.add(jLabel48);

        debtdate.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        debtdate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        debtdate.setText("null");
        jPanel41.add(debtdate);

        debt.add(jPanel41);
        jPanel41.setBounds(80, 200, 380, 20);

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setOpaque(false);
        jPanel43.setLayout(new java.awt.GridLayout());

        jLabel51.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel51.setText("Monthly payment* : ");
        jPanel43.add(jLabel51);

        debtmonth.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        debtmonth.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        debtmonth.setText("0.0");
        jPanel43.add(debtmonth);

        debt.add(jPanel43);
        jPanel43.setBounds(80, 240, 380, 21);

        jButton10.setText("Pay Monthly");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        debt.add(jButton10);
        jButton10.setBounds(320, 300, 130, 21);

        fulldebtpayment.setText("Pay Full");
        fulldebtpayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fulldebtpaymentActionPerformed(evt);
            }
        });
        debt.add(fulldebtpayment);
        fulldebtpayment.setBounds(100, 300, 130, 20);

        loanpanel.add(debt);
        debt.setBounds(10, 160, 560, 430);

        front.setBackground(new java.awt.Color(255, 255, 255));
        front.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(241, 241, 241)));
        front.setForeground(new java.awt.Color(255, 255, 255));
        front.setLayout(null);

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/loanicon.png"))); // NOI18N
        front.add(jLabel40);
        jLabel40.setBounds(70, 50, 420, 80);

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setOpaque(false);

        jLabel25.setFont(new java.awt.Font("Lato", 0, 36)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(44, 62, 78));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText(String.format("%.2f",user.getIncome()*24*0.2));
        jPanel32.add(jLabel25);

        jLabel3.setText("  SAR");
        jPanel32.add(jLabel3);

        front.add(jPanel32);
        jPanel32.setBounds(160, 90, 310, 50);

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setOpaque(false);
        jPanel35.setLayout(new java.awt.GridLayout());

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel13.setText("Benefit rate: ");
        jPanel35.add(jLabel13);

        benrate.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        benrate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        benrate.setText("5%");
        jPanel35.add(benrate);

        front.add(jPanel35);
        jPanel35.setBounds(80, 170, 380, 20);

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));
        jPanel38.setOpaque(false);
        jPanel38.setLayout(new java.awt.GridLayout());

        jLabel47.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel47.setText("Benefit amount: ");
        jPanel38.add(jLabel47);

        benamount.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        benamount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        benamount.setText("0.0");
        jPanel38.add(benamount);

        front.add(jPanel38);
        jPanel38.setBounds(80, 200, 380, 20);

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setOpaque(false);
        jPanel39.setLayout(new java.awt.GridLayout());

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel16.setText("Total: ");
        jPanel39.add(jLabel16);

        totalwithrate.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        totalwithrate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalwithrate.setText("0.0");
        jPanel39.add(totalwithrate);

        front.add(jPanel39);
        jPanel39.setBounds(80, 230, 380, 20);

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));
        jPanel40.setOpaque(false);
        jPanel40.setLayout(new java.awt.GridLayout());

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel15.setText("Monthly payment* : ");
        jPanel40.add(jLabel15);

        monthpay.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        monthpay.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        monthpay.setText("0.0");
        jPanel40.add(monthpay);

        front.add(jPanel40);
        jPanel40.setBounds(80, 260, 380, 20);

        jButton11.setText("Apply for the loan");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        front.add(jButton11);
        jButton11.setBounds(200, 320, 160, 32);

        jLabel24.setFont(new java.awt.Font("Dialog", 3, 10)); // NOI18N
        jLabel24.setText("* for a duration of 2 years");
        front.add(jLabel24);
        jLabel24.setBounds(80, 360, 140, 14);

        loanpanel.add(front);
        front.setBounds(10, 160, 560, 430);

        dashboard.add(loanpanel);
        loanpanel.setBounds(0, 0, 580, 600);

        transactionpanel.setBackground(new java.awt.Color(240, 240, 240));
        transactionpanel.setForeground(new java.awt.Color(240, 240, 240));
        transactionpanel.setLayout(null);

        jPanel11.setBackground(new java.awt.Color(70, 94, 114));
        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 2, 0, new java.awt.Color(50, 72, 92)));
        jPanel11.setLayout(null);

        jLabel11.setFont(new java.awt.Font("HelvLight", 0, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/labels/transactionlabelwhite.png"))); // NOI18N
        jPanel11.add(jLabel11);
        jLabel11.setBounds(20, 25, 200, 47);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(248, 248, 248)));
        jPanel12.setLayout(null);

        jLabel18.setFont(new java.awt.Font("Gill Sans MT", 0, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Welcome back, "+user.getFirstname()+" "+ user.getLastname());
        jPanel12.add(jLabel18);
        jLabel18.setBounds(20, 10, 550, 30);

        logindate2.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        logindate2.setForeground(new java.awt.Color(153, 153, 153));
        logindate2.setText("Logged in at "+ formatter.format(date));
        jPanel12.add(logindate2);
        logindate2.setBounds(25, 35, 220, 16);

        jPanel11.add(jPanel12);
        jPanel12.setBounds(0, 90, 590, 60);

        transactionpanel.add(jPanel11);
        jPanel11.setBounds(0, 0, 590, 150);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setLayout(null);

        tobenfit.setBackground(new java.awt.Color(255, 255, 255));
        tobenfit.setLayout(null);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(236, 236, 236)));
        jPanel24.setForeground(new java.awt.Color(165, 165, 165));
        jPanel24.setLayout(null);

        balancelabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        balancelabel3.setForeground(new java.awt.Color(44, 62, 78));
        balancelabel3.setText("Checking Account");
        jPanel24.add(balancelabel3);
        balancelabel3.setBounds(100, 10, 130, 24);

        jPanel25.setOpaque(false);

        balancecashlabel2.setFont(new java.awt.Font("Lato", 0, 24)); // NOI18N
        balancecashlabel2.setForeground(new java.awt.Color(28, 28, 28));
        balancecashlabel2.setText(String.format("%.2f", checking.getBalance()));
        jPanel25.add(balancecashlabel2);

        SARlabel2.setForeground(new java.awt.Color(102, 102, 102));
        SARlabel2.setText("SAR");
        jPanel25.add(SARlabel2);

        jPanel24.add(jPanel25);
        jPanel25.setBounds(10, 30, 310, 30);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("membership");
        jPanel24.add(jLabel39);
        jLabel39.setBounds(100, 70, 130, 16);

        tobenfit.add(jPanel24);
        jPanel24.setBounds(20, 20, 330, 90);

        reciever.setFocusable(false);
        reciever.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recieverActionPerformed(evt);
            }
        });
        tobenfit.add(reciever);
        reciever.setBounds(20, 140, 330, 25);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Amount : ");
        tobenfit.add(jLabel28);
        jLabel28.setBounds(20, 170, 110, 16);

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        tobenfit.add(jTextField2);
        jTextField2.setBounds(20, 190, 130, 24);

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setLayout(new javax.swing.BoxLayout(jPanel27, javax.swing.BoxLayout.LINE_AXIS));

        jLabel37.setText("Total: ");
        jPanel27.add(jLabel37);
        jPanel27.add(totaltrans);

        tobenfit.add(jPanel27);
        jPanel27.setBounds(190, 190, 160, 20);

        jLabel35.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(153, 153, 153));
        jLabel35.setText(" SAR");
        jLabel35.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        tobenfit.add(jLabel35);
        jLabel35.setBounds(150, 200, 30, 14);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setLayout(new javax.swing.BoxLayout(jPanel26, javax.swing.BoxLayout.LINE_AXIS));

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Purpose : ");
        jPanel26.add(jLabel31);

        jLabel33.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(153, 153, 153));
        jLabel33.setText("(Optional)");
        jPanel26.add(jLabel33);

        tobenfit.add(jPanel26);
        jPanel26.setBounds(20, 220, 190, 20);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        tobenfit.add(jTextField1);
        jTextField1.setBounds(20, 240, 330, 25);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Date:");
        tobenfit.add(jLabel36);
        jLabel36.setBounds(20, 270, 80, 16);

        jTextField3.setText(formattert.format(date));
        jTextField3.setEnabled(false);
        tobenfit.add(jTextField3);
        jTextField3.setBounds(20, 290, 330, 24);

        sendtrans.setText("Send");
        sendtrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendtransActionPerformed(evt);
            }
        });
        tobenfit.add(sendtrans);
        sendtrans.setBounds(100, 320, 150, 30);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Send to :    ");
        tobenfit.add(jLabel34);
        jLabel34.setBounds(20, 120, 120, 16);

        tobenfit.setVisible(false);

        jPanel23.add(tobenfit);
        tobenfit.setBounds(80, 60, 380, 350);

        betweenmyacc.setBackground(new java.awt.Color(255, 255, 255));
        betweenmyacc.setLayout(null);

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(236, 236, 236)));
        jPanel28.setForeground(new java.awt.Color(165, 165, 165));
        jPanel28.setLayout(null);

        balancelabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        balancelabel2.setForeground(new java.awt.Color(44, 62, 78));
        balancelabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        balancelabel2.setText("Checking Account");
        jPanel28.add(balancelabel2);
        balancelabel2.setBounds(10, 10, 300, 24);

        jPanel29.setOpaque(false);

        balancecashlabel3.setFont(new java.awt.Font("Lato", 0, 24)); // NOI18N
        balancecashlabel3.setForeground(new java.awt.Color(28, 28, 28));
        balancecashlabel3.setText(String.format("%.2f", checking.getBalance()));
        jPanel29.add(balancecashlabel3);

        SARlabel4.setForeground(new java.awt.Color(102, 102, 102));
        SARlabel4.setText("SAR");
        jPanel29.add(SARlabel4);

        jPanel28.add(jPanel29);
        jPanel29.setBounds(10, 40, 300, 30);

        betweenmyacc.add(jPanel28);
        jPanel28.setBounds(30, 20, 330, 100);

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(236, 236, 236)));
        jPanel30.setForeground(new java.awt.Color(165, 165, 165));
        jPanel30.setLayout(null);

        jPanel31.setOpaque(false);

        balancecashlabel4.setFont(new java.awt.Font("Lato", 0, 24)); // NOI18N
        balancecashlabel4.setForeground(new java.awt.Color(28, 28, 28));
        balancecashlabel4.setText(String.format("%.2f", saving.getBalance()));
        jPanel31.add(balancecashlabel4);

        SARlabel5.setForeground(new java.awt.Color(102, 102, 102));
        SARlabel5.setText("SAR");
        jPanel31.add(SARlabel5);

        jPanel30.add(jPanel31);
        jPanel31.setBounds(10, 40, 300, 30);

        balancelabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        balancelabel5.setForeground(new java.awt.Color(44, 62, 78));
        balancelabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        balancelabel5.setText("Saving Account");
        jPanel30.add(balancelabel5);
        balancelabel5.setBounds(10, 10, 300, 24);

        betweenmyacc.add(jPanel30);
        jPanel30.setBounds(30, 200, 330, 100);

        transactfield1.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        transactfield1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        transactfield1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(226, 226, 226)));
        transactfield1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                transactfield1KeyTyped(evt);
            }
        });
        betweenmyacc.add(transactfield1);
        transactfield1.setBounds(110, 140, 170, 40);

        jButton8.setText("v");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        betweenmyacc.add(jButton8);
        jButton8.setBounds(30, 150, 60, 32);

        jButton9.setText("^");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        betweenmyacc.add(jButton9);
        jButton9.setBounds(300, 150, 60, 32);

        betweenmyacc.setVisible(false);

        jPanel23.add(betweenmyacc);
        betweenmyacc.setBounds(70, 60, 390, 350);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Transact:");
        jPanel23.add(jLabel38);
        jLabel38.setBounds(100, 10, 120, 20);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Between my accounts", "To a beneficiary" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPanel23.add(jComboBox1);
        jComboBox1.setBounds(100, 30, 330, 26);

        transactionpanel.add(jPanel23);
        jPanel23.setBounds(10, 160, 560, 430);

        dashboard.add(transactionpanel);
        transactionpanel.setBounds(0, 0, 580, 600);

        addben.setBackground(new java.awt.Color(240, 240, 240));
        addben.setForeground(new java.awt.Color(240, 240, 240));
        addben.setLayout(null);

        jPanel15.setBackground(new java.awt.Color(70, 94, 114));
        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 2, 0, new java.awt.Color(50, 72, 92)));
        jPanel15.setLayout(null);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(248, 248, 248)));
        jPanel17.setLayout(null);

        jLabel29.setFont(new java.awt.Font("Gill Sans MT", 0, 20)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Welcome back, "+user.getFirstname()+" "+ user.getLastname());
        jPanel17.add(jLabel29);
        jLabel29.setBounds(20, 10, 550, 30);

        logindate4.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        logindate4.setForeground(new java.awt.Color(153, 153, 153));
        logindate4.setText("Logged in at "+ formatter.format(date));
        jPanel17.add(logindate4);
        logindate4.setBounds(25, 35, 220, 16);

        jPanel15.add(jPanel17);
        jPanel17.setBounds(0, 90, 590, 60);

        addben.add(jPanel15);
        jPanel15.setBounds(0, 0, 590, 150);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(243, 243, 243)));
        jPanel18.setLayout(null);

        jLabel7.setText("You can add a benificiary for an easy access and transaction");
        jPanel18.add(jLabel7);
        jLabel7.setBounds(80, 80, 360, 16);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setLayout(null);

        found.setForeground(new java.awt.Color(0, 102, 51));
        found.setText("Found!");
        found.setVisible(false);
        jPanel21.add(found);
        found.setBounds(160, 10, 48, 16);

        notfound.setForeground(new java.awt.Color(204, 0, 0));
        notfound.setText("No Found!");
        notfound.setVisible(false);
        jPanel21.add(notfound);
        notfound.setBounds(150, 10, 70, 16);

        benpanel1.setBackground(new java.awt.Color(255, 255, 255));
        benpanel1.setLayout(new javax.swing.BoxLayout(benpanel1, javax.swing.BoxLayout.LINE_AXIS));
        benpanel1.setVisible(false);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText(" Full Name: ");
        benpanel1.add(jLabel30);

        benname.setForeground(new java.awt.Color(0, 0, 0));
        benname.setText("name from database");
        benpanel1.add(benname);

        jPanel21.add(benpanel1);
        benpanel1.setBounds(50, 30, 260, 20);

        benpanel2.setBackground(new java.awt.Color(255, 255, 255));
        benpanel2.setLayout(new javax.swing.BoxLayout(benpanel2, javax.swing.BoxLayout.LINE_AXIS));
        benpanel2.setVisible(false);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText(" Account: ");
        benpanel2.add(jLabel32);

        benacclabel.setForeground(new java.awt.Color(0, 0, 0));
        benacclabel.setText("acc from database");
        benpanel2.add(benacclabel);

        jPanel21.add(benpanel2);
        benpanel2.setBounds(50, 50, 260, 20);

        benconfirmpanel.setBackground(new java.awt.Color(232, 232, 232));
        benconfirmpanel.setLayout(null);
        benconfirmpanel.setVisible(false);

        benconfirm.setForeground(new java.awt.Color(0, 0, 0));
        benconfirm.setText("Do you want to add this account?");
        benconfirmpanel.add(benconfirm);
        benconfirm.setBounds(50, 10, 190, 16);

        benyes.setText("Yes");
        benyes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                benyesActionPerformed(evt);
            }
        });
        benconfirmpanel.add(benyes);
        benyes.setBounds(100, 30, 80, 25);

        jPanel21.add(benconfirmpanel);
        benconfirmpanel.setBounds(40, 80, 280, 60);

        jPanel18.add(jPanel21);
        jPanel21.setBounds(90, 190, 360, 180);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setLayout(new javax.swing.BoxLayout(jPanel22, javax.swing.BoxLayout.LINE_AXIS));

        jLabel14.setText("Benificiary Account Number:  ");
        jPanel22.add(jLabel14);

        benacc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        benacc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                benaccKeyTyped(evt);
            }
        });
        jPanel22.add(benacc);

        jPanel18.add(jPanel22);
        jPanel22.setBounds(90, 120, 330, 20);

        jButton1.setText("Check");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton1);
        jButton1.setBounds(230, 150, 80, 25);

        addben.add(jPanel18);
        jPanel18.setBounds(20, 170, 540, 390);

        dashboard.add(addben);
        addben.setBounds(0, 0, 580, 600);

        contentpanel.add(dashboard);
        dashboard.setBounds(260, 0, 580, 600);

        leftpan.setBackground(new java.awt.Color(44, 62, 78));
        leftpan.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Menu");
        leftpan.add(jLabel5);
        jLabel5.setBounds(20, 120, 60, 30);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Others");
        leftpan.add(jLabel6);
        jLabel6.setBounds(20, 340, 150, 19);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/benefbutton.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton3.setFocusable(false);
        jButton3.setRolloverEnabled(true);
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/benefbuttonpressed.png"))); // NOI18N
        jButton3.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/benefbuttonpressed.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        leftpan.add(jButton3);
        jButton3.setBounds(0, 370, 440, 35);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/dashboardbutt.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton4.setFocusable(false);
        jButton4.setRolloverEnabled(true);
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/dashboardbuttpressed.png"))); // NOI18N
        jButton4.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/dashboardbuttpressed.png"))); // NOI18N
        jButton4.setVerifyInputWhenFocusTarget(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        leftpan.add(jButton4);
        jButton4.setBounds(0, 150, 440, 40);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/accountbutton.png"))); // NOI18N
        jButton5.setBorder(null);
        jButton5.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton5.setFocusable(false);
        jButton5.setRolloverEnabled(true);
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/accountbuttonpressed.png"))); // NOI18N
        jButton5.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/accountbuttonpressed.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        leftpan.add(jButton5);
        jButton5.setBounds(0, 190, 440, 40);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/loanbutton.png"))); // NOI18N
        jButton6.setBorder(null);
        jButton6.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton6.setFocusable(false);
        jButton6.setRolloverEnabled(true);
        jButton6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/loanbuttonpressed.png"))); // NOI18N
        jButton6.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/loanbuttonpressed.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        leftpan.add(jButton6);
        jButton6.setBounds(0, 270, 440, 40);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/transbutton.png"))); // NOI18N
        jButton7.setBorder(null);
        jButton7.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton7.setFocusable(false);
        jButton7.setRolloverEnabled(true);
        jButton7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/transbuttonpressed.png"))); // NOI18N
        jButton7.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/transbuttonpressed.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        leftpan.add(jButton7);
        jButton7.setBounds(0, 230, 440, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/logo.png"))); // NOI18N
        leftpan.add(jLabel1);
        jLabel1.setBounds(20, 10, 210, 80);

        signout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/signout.png"))); // NOI18N
        signout.setBorder(null);
        signout.setBorderPainted(false);
        signout.setContentAreaFilled(false);
        signout.setFocusable(false);
        signout.setRolloverEnabled(true);
        signout.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/wansesbank/images/signoutpressed.png"))); // NOI18N
        signout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signoutActionPerformed(evt);
            }
        });
        leftpan.add(signout);
        signout.setBounds(60, 530, 130, 50);

        leftboard.add(leftpan);
        leftpan.setBounds(0, 0, 260, 600);

        contentpanel.add(leftboard);
        leftboard.setBounds(0, 0, 260, 600);

        mainpanel.setVisible(true);
        transactionpanel.setVisible(false);
        loanpanel.setVisible(false);
        accountpanel.setVisible(false);

        getContentPane().add(contentpanel);
        contentpanel.setBounds(0, 0, 840, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        updateBalance();
        initSummary();
        mainpanel.setVisible(true);
        transactionpanel.setVisible(false);
        loanpanel.setVisible(false);
        accountpanel.setVisible(false);
        addben.setVisible(false);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        mainpanel.setVisible(false);
        transactionpanel.setVisible(false);
        loanpanel.setVisible(false);
        accountpanel.setVisible(true);
        addben.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        updateBalance();
        ArrayList<String> benef = new ArrayList<>();
        ArrayList<String> fullname = new ArrayList<>();
        ArrayList<Integer> accnumber = new ArrayList<>();
        jTextField2.setText("");
        totaltrans.setText("");
        try (
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("SELECT Ben_ID, FULLNAME FROM CUSTOMER_BEN WHERE Cust_ID=" + user.getCust_ID());) {

            while (resultset.next()) {
                accnumber.add(resultset.getInt(1));
                benef.add(resultset.getString(2) + " - Account: " + String.valueOf(resultset.getInt(1)));
                fullname.add(resultset.getString(2));
            }
            String[] array = benef.toArray(new String[benef.size()]);
            fullnamearray = fullname.toArray(new String[fullname.size()]);
            benarray = accnumber.stream().mapToInt(i -> i).toArray();
            reciever.setModel(new DefaultComboBoxModel(array));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e);
        }

        mainpanel.setVisible(false);
        transactionpanel.setVisible(true);
        loanpanel.setVisible(false);
        accountpanel.setVisible(false);
        addben.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        loanCheck();


        mainpanel.setVisible(false);
        transactionpanel.setVisible(false);
        loanpanel.setVisible(true);
        accountpanel.setVisible(false);
        addben.setVisible(false);
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void depositbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositbutton1ActionPerformed
        if (!transactfield.getText().isEmpty()) {
            if (jComboBox2.getSelectedItem().equals("Checking")) {
                try (
                        Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                        Statement statement = connection.createStatement();
                        ResultSet resultset = statement.executeQuery("SELECT Balance FROM ACCOUNT WHERE Cust_ID=" + user.getCust_ID() + " AND AccType=" + 1);) {
                    resultset.next();
                    double databalance = resultset.getDouble(1);
                    statement.executeUpdate("UPDATE ACCOUNT SET balance=balance+" + Double.parseDouble(transactfield.getText()) + " WHERE Cust_ID=" + user.getCust_ID() + " AND AccType=" + 1);
                    balancecashlabel1.setText(String.format("%.2f", databalance + Double.parseDouble(transactfield.getText())));
                } catch (SQLException e) {
                    transactfield.setText("");
                } catch (Exception e) {
                    transactfield.setText("");
                }
            }
            if (jComboBox2.getSelectedItem().equals("Saving")) {
                try (
                        Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                        Statement statement = connection.createStatement();
                        ResultSet resultset = statement.executeQuery("SELECT Balance FROM ACCOUNT WHERE Cust_ID=" + user.getCust_ID() + " AND AccType=" + 2);) {
                    resultset.next();
                    double databalance = resultset.getDouble(1);
                    statement.executeUpdate("UPDATE ACCOUNT SET balance=balance+" + Double.parseDouble(transactfield.getText()) + " WHERE Cust_ID=" + user.getCust_ID() + " AND AccType=" + 2);
                    //balancecashlabel.setText(String.valueOf(databalance + Double.parseDouble(transactfield.getText())));
                } catch (SQLException e) {
                    transactfield.setText("");
                } catch (Exception e) {
                    transactfield.setText("");
                }

            }
            depositsuccess.setText("You deposited " + transactfield.getText() + " SAR");
            withdrawsuccess.setVisible(false);
            depositsuccess.setVisible(true);
        } else {
            depositsuccess.setText("You entered an invalid value");
            withdrawsuccess.setVisible(false);
            depositsuccess.setVisible(true);
        }
        updateBalance();

    }//GEN-LAST:event_depositbutton1ActionPerformed

    private void withdrawbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdrawbutton1ActionPerformed
        if (!transactfield.getText().isEmpty()) {
            if (jComboBox2.getSelectedItem().equals("Checking")) {
                try (
                        Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                        Statement statement = connection.createStatement();
                        ResultSet resultset = statement.executeQuery("SELECT Balance FROM ACCOUNT WHERE Cust_ID=" + user.getCust_ID() + " AND AccType=" + 1);) {
                    resultset.next();
                    double databalance = resultset.getDouble(1);
                    if (databalance >= Double.parseDouble(transactfield.getText())) {
                        statement.executeUpdate("UPDATE ACCOUNT SET balance=balance-" + Double.parseDouble(transactfield.getText()) + " WHERE Cust_ID=" + user.getCust_ID() + " AND AccType=" + 1);
                        //balancecashlabel1.setText(String.format("%.2f", databalance - Double.parseDouble(transactfield.getText())));
                        withdrawsuccess.setText("You withdrawn " + transactfield.getText() + " SAR");
                        withdrawsuccess.setVisible(true);
                        depositsuccess.setVisible(false);
                    } else {
                        withdrawsuccess.setText("You don't have enough to do this action ");
                        withdrawsuccess.setVisible(true);
                        depositsuccess.setVisible(false);
                    }

                } catch (SQLException e) {
                    transactfield.setText("");
                } catch (Exception e) {
                    transactfield.setText("");
                }
            }
            if (jComboBox2.getSelectedItem().equals("Saving")) {
                try (
                        Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                        Statement statement = connection.createStatement();
                        ResultSet resultset = statement.executeQuery("SELECT Balance FROM ACCOUNT WHERE Cust_ID=" + user.getCust_ID() + " AND AccType=" + 2);) {
                    resultset.next();
                    double databalance = resultset.getDouble(1);
                    if (databalance >= Double.parseDouble(transactfield.getText())) {
                        statement.executeUpdate("UPDATE ACCOUNT SET balance=balance-" + Double.parseDouble(transactfield.getText()) + " WHERE Cust_ID=" + user.getCust_ID() + " AND AccType=" + 2);
                        //balancecashlabel.setText(String.format("%.2f",databalance - Double.parseDouble(transactfield.getText())));
                        withdrawsuccess.setText("You withdrawn " + transactfield.getText() + " SAR");
                        withdrawsuccess.setVisible(true);
                        depositsuccess.setVisible(false);
                    } else {
                        withdrawsuccess.setText("You don't have enough balance");
                        withdrawsuccess.setVisible(true);
                        depositsuccess.setVisible(false);
                    }

                } catch (SQLException e) {
                    transactfield.setText("");
                } catch (Exception e) {
                    transactfield.setText("");
                }

            }

        } else {
            withdrawsuccess.setText("You entered an invalid value");
            withdrawsuccess.setVisible(true);
            depositsuccess.setVisible(false);
        }
        updateBalance();
    }//GEN-LAST:event_withdrawbutton1ActionPerformed

    private void transactfieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_transactfieldKeyTyped

        if (!(Character.isDigit(evt.getKeyChar()) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_PERIOD )) 
            evt.consume();
        if(transactfield.getText().length() >= 10)
            evt.consume();
    }//GEN-LAST:event_transactfieldKeyTyped

    private void passhowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passhowActionPerformed
        if (passhow.isSelected()) {
            passww.setText(user.getPassword());
        } else {
            passww.setText("*****");
        }
    }//GEN-LAST:event_passhowActionPerformed

    private void oldpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oldpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oldpassActionPerformed

    private void savechangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savechangeActionPerformed
        if (oldpass.getText().equals(user.getPassword()) && !newpass.getText().isEmpty()) {
            int input = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (input == 0) {
                try (
                        Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                        Statement statement = connection.createStatement();
                        ResultSet resultset = statement.executeQuery("SELECT PASSWORD FROM CUSTOMER WHERE Cust_ID=" + user.getCust_ID());) {
                    statement.executeUpdate("UPDATE CUSTOMER SET password='" + newpass.getText() + "' WHERE Cust_ID=" + user.getCust_ID());
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e);
                }
                JOptionPane.showMessageDialog(null, "You will be logged out!");
                WansesLogin a = new WansesLogin();
                a.setVisible(true);
                this.dispose();
            } else {
                jLabel22.setVisible(false);
                jLabel17.setVisible(false);
                newpass.setVisible(false);
                oldpass.setVisible(false);
                savechange.setVisible(false);
                cancel.setVisible(false);
                passw.setVisible(true);
                passww.setVisible(true);
                changepass.setVisible(true);
                passhow.setVisible(true);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Current password is wrong");
        }

    }//GEN-LAST:event_savechangeActionPerformed

    private void changepassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changepassActionPerformed
        jLabel22.setVisible(true);
        jLabel17.setVisible(true);
        newpass.setVisible(true);
        oldpass.setVisible(true);
        savechange.setVisible(true);
        cancel.setVisible(true);
        passw.setVisible(false);
        passww.setVisible(false);
        changepass.setVisible(false);
        passhow.setVisible(false);
    }//GEN-LAST:event_changepassActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        jLabel22.setVisible(false);
        jLabel17.setVisible(false);
        newpass.setVisible(false);
        oldpass.setVisible(false);
        savechange.setVisible(false);
        cancel.setVisible(false);
        passw.setVisible(true);
        passww.setVisible(true);
        changepass.setVisible(true);
        passhow.setVisible(true);
    }//GEN-LAST:event_cancelActionPerformed

    private void signoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signoutActionPerformed
        WansesLogin a = new WansesLogin();
        a.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_signoutActionPerformed

    private void editinfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editinfoActionPerformed
        firstnamedata.setVisible(false);
        lastnamedata.setVisible(false);
        phonenumberdata.setVisible(false);
        addressdata.setVisible(false);
        incomedata.setVisible(false);
        firstnameupdate.setVisible(true);
        phonenumberupdate.setVisible(true);
        addressupdate.setVisible(true);
        lastnameupdate.setVisible(true);
        incomeupdate.setVisible(true);
        editinfo.setVisible(false);
        saveinfo.setVisible(true);
        cancelinfo.setVisible(true);

    }//GEN-LAST:event_editinfoActionPerformed

    private void cancelinfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelinfoActionPerformed
        firstnamedata.setVisible(true);
        lastnamedata.setVisible(true);
        phonenumberdata.setVisible(true);
        addressdata.setVisible(true);
        incomedata.setVisible(true);
        firstnameupdate.setVisible(false);
        phonenumberupdate.setVisible(false);
        addressupdate.setVisible(false);
        lastnameupdate.setVisible(false);
        incomeupdate.setVisible(false);
        editinfo.setVisible(true);
        saveinfo.setVisible(false);
        cancelinfo.setVisible(false);
    }//GEN-LAST:event_cancelinfoActionPerformed

    private void saveinfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveinfoActionPerformed
        try {
            // create a java mysql database connection
            Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            //Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/wanses", "wans", "123");
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE CUSTOMER SET FIRSTNAME='" + firstnameupdate.getText() + "' , LASTNAME='" + lastnameupdate.getText() + "'"
                    + ", PHONENUMBER='" + phonenumberupdate.getText() + "' , ADDRESS='" + addressupdate.getText() + "'"
                    + ", INCOME=" + Double.parseDouble(incomeupdate.getText()) + " , MEMBERSHIP=" + ((Double.parseDouble(incomeupdate.getText()) >= 15000) ? 3 : ((Double.parseDouble(incomeupdate.getText()) >= 7500) ? 2 : 1)) + " WHERE Cust_ID=" + user.getCust_ID());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e);
            System.out.println(e);
        }
        
        try (
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("SELECT * FROM CUSTOMER WHERE Cust_ID=" + user.getCust_ID());) {

            ResultSetMetaData meta = resultset.getMetaData();
                
            resultset.next();
            firstnamedata.setText(resultset.getString(2));
            lastnamedata.setText(resultset.getString(3));
            addressdata.setText(resultset.getString(4));
            phonenumberdata.setText(resultset.getString(7));
            user.setMembership(resultset.getInt(8));
            if (user.getMembership() == 1) {
                membershipdata.setText("Silver");
            } else if (user.getMembership() == 2) {
                membershipdata.setText("Gold");
            } else {
                membershipdata.setText("Diamond");
            }
            incomedata.setText(String.valueOf(resultset.getDouble(9)));

        } catch (SQLException e) {
            System.out.println(e);
        }
        retrieveUser();
        firstnamedata.setVisible(true);
        lastnamedata.setVisible(true);
        phonenumberdata.setVisible(true);
        addressdata.setVisible(true);
        incomedata.setVisible(true);
        firstnameupdate.setVisible(false);
        phonenumberupdate.setVisible(false);
        addressupdate.setVisible(false);
        lastnameupdate.setVisible(false);
        incomeupdate.setVisible(false);
        editinfo.setVisible(true);
        saveinfo.setVisible(false);
        cancelinfo.setVisible(false);
    }//GEN-LAST:event_saveinfoActionPerformed

    private void refreshbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbutActionPerformed
        int memu;
        try (
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("SELECT * FROM CUSTOMER WHERE Cust_ID=" + user.getCust_ID());) {

            ResultSetMetaData meta = resultset.getMetaData();

            resultset.next();
            firstnamedata.setText(resultset.getString(2));
            lastnamedata.setText(resultset.getString(3));
            addressdata.setText(resultset.getString(4));
            phonenumberdata.setText(resultset.getString(7));
            if (resultset.getInt(8) == 1) {
                membershipdata.setText("Silver");
            } else if (resultset.getInt(8) == 2) {
                membershipdata.setText("Gold");
            } else {
                membershipdata.setText("Diamond");
            }
            incomedata.setText(String.valueOf(resultset.getDouble(9)));

        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_refreshbutActionPerformed

    private void benyesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_benyesActionPerformed

        try {
            Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT Cust_ID FROM ACCOUNT WHERE Acc_ID=" + benacc.getText());
            resultset.next();
            if (user.getCust_ID() != resultset.getInt(1)) {
                statement.executeUpdate("INSERT INTO CUSTOMER_BEN(BEN_ID, CUST_ID, FULLNAME) "
                        + "VALUES(" + Integer.parseInt(benacclabel.getText()) + "," + user.getCust_ID() + ",'"+ benname.getText()+"')");

                found.setVisible(false);
                notfound.setVisible(false);
                benpanel1.setVisible(false);
                benpanel2.setVisible(false);
                benconfirmpanel.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "You can not add yourself!");
            }

        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "This account is added already");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong");
        }

    }//GEN-LAST:event_benyesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (benacc.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a number!");
        } else {
            try {
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("SELECT Cust_ID FROM ACCOUNT WHERE Acc_ID=" + benacc.getText());
                if (resultset.next()) {
                    found.setVisible(true);
                    notfound.setVisible(false);
                    benpanel1.setVisible(true);
                    benpanel2.setVisible(true);
                    benconfirmpanel.setVisible(true);
                    int bencust = resultset.getInt(1);
                    resultset = statement.executeQuery("SELECT * FROM CUSTOMER WHERE Cust_ID=" + bencust);
                    resultset.next();
                    benname.setText(resultset.getString(2) + " " + resultset.getString(3));
                    benacclabel.setText(benacc.getText());
                } else {
                    found.setVisible(false);
                    notfound.setVisible(true);
                    benpanel1.setVisible(false);
                    benpanel2.setVisible(false);
                    benconfirmpanel.setVisible(false);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void benaccKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_benaccKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        } else {
            found.setVisible(false);
            notfound.setVisible(false);
            benpanel1.setVisible(false);
            benpanel2.setVisible(false);
            benconfirmpanel.setVisible(false);
        }

    }//GEN-LAST:event_benaccKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        mainpanel.setVisible(false);
        transactionpanel.setVisible(false);
        loanpanel.setVisible(false);
        accountpanel.setVisible(false);
        addben.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void recieverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recieverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_recieverActionPerformed

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_PERIOD)) {
            evt.consume();
        }
        if (jTextField2.getText().length() >= 10)
                evt.consume();

    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed

    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        double feedmoney;
        try {
            feedmoney = (user.getMembership()==1)?(Double.parseDouble(jTextField2.getText())*1.025):
                        ((user.getMembership()==2)?(Double.parseDouble(jTextField2.getText())*1.020):
                        (Double.parseDouble(jTextField2.getText()))*1.015);
            
            totaltrans.setText(String.format("%.2f", (feedmoney)));
        } catch (Exception e) {
            jTextField2.setText("0.0");
            jTextField2.selectAll();
            totaltrans.setText("0");
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void sendtransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendtransActionPerformed
        
                if(!jTextField2.getText().isEmpty()){
                    if(Double.parseDouble(jTextField2.getText())>1){
                        if(Double.parseDouble(balancecashlabel2.getText())>=Double.parseDouble(totaltrans.getText())){
                        int a = JOptionPane.showConfirmDialog(this,"Do you want to do this transaction?","Transaction Confirmation",JOptionPane.YES_OPTION);
                        if(a==0){
                    try {
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                
                int senderid= checking.getAcc_ID(),
                    receiverid= benarray[reciever.getSelectedIndex()];
                double amount=(user.getMembership()==1)?(Double.parseDouble(jTextField2.getText())*1.025):
                              ((user.getMembership()==2)?(Double.parseDouble(jTextField2.getText())*1.020):
                              (Double.parseDouble(jTextField2.getText()))*1.015);
                double amountnofee= Double.parseDouble(jTextField2.getText());
                double fee= amount-amountnofee;
                String purpose=jTextField1.getText(),
                       tdate=jTextField3.getText();
                String sendername = user.getFirstname()+" "+user.getLastname();
                String recievername = fullnamearray[reciever.getSelectedIndex()];
 
                statement.executeUpdate("INSERT INTO TRANSACTS(SENDER_ID, RECIEVER_ID, PURPOSE, TRANSDATE, AMOUNT, SENDER_NAME, RECIEVER_NAME, FEE) VALUES("+senderid+","+receiverid+",'"+purpose+"','"+tdate+"',"+amountnofee+",'"+sendername+"','"+recievername+"',"+fee+")");
                statement.executeUpdate("UPDATE BANK SET BR_BALANCE=BR_BALANCE+"+fee+"WHERE BANK_ID= 1");
                statement.executeUpdate("UPDATE ACCOUNT SET BALANCE=BALANCE-"+amount+" WHERE ACC_ID="+senderid);
                statement.executeUpdate("UPDATE ACCOUNT SET BALANCE=BALANCE+"+amount+" WHERE ACC_ID="+receiverid);
                updateBalance();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e);
            }
                            }}else
                        {
                            JOptionPane.showMessageDialog(this, "You don't have enough to do this action!");
                        }
                    }else
        {
            JOptionPane.showMessageDialog(this, "The minimum transaction amount is 10 SAR");
        }
                }else
                JOptionPane.showMessageDialog(this, "Please enter a value in amount field");
 
        



    }//GEN-LAST:event_sendtransActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        SummaryFrame a= new SummaryFrame(checking.getAcc_ID(), saving.getAcc_ID());
        a.setVisible(true);
        a.setLocationRelativeTo(this);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void transactfield1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_transactfield1KeyTyped
        if (transactfield1.getText().length() >= 10)
                evt.consume();
        if (!(Character.isLetterOrDigit(evt.getKeyChar()) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_PERIOD )) 
                evt.consume();
             
    }//GEN-LAST:event_transactfield1KeyTyped

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try (
                        Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                        Statement statement = connection.createStatement();
                        ResultSet resultset = statement.executeQuery("SELECT Balance FROM ACCOUNT WHERE Cust_ID=" + user.getCust_ID() + " AND AccType=" + 1);) {
                    resultset.next();
                    double databalance = resultset.getDouble(1);
                    ResultSet resultset2 = statement.executeQuery("SELECT Balance FROM ACCOUNT WHERE Cust_ID=" + user.getCust_ID() + " AND AccType=" + 2);
                    resultset2.next();
                    double databalance2 = resultset2.getDouble(1);
                    
                    
                    
                    if (databalance2 >= Double.parseDouble(transactfield1.getText())) {
                        statement.executeUpdate("UPDATE ACCOUNT SET balance=balance+" + Double.parseDouble(transactfield1.getText()) + " WHERE Cust_ID=" + user.getCust_ID() + " AND AccType=" + 1);
                        statement.executeUpdate("UPDATE ACCOUNT SET balance=balance-" + Double.parseDouble(transactfield1.getText()) + " WHERE Cust_ID=" + user.getCust_ID() + " AND AccType=" + 2);
                    } else {
                        JOptionPane.showMessageDialog(this,"You don't have enough balance");
                    }
                } catch (SQLException e) {
                    transactfield1.setText("");
                } catch (Exception e) {
                    transactfield1.setText("");
                }
        updateBalance();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try (
                        Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                        Statement statement = connection.createStatement();
                        ResultSet resultset = statement.executeQuery("SELECT Balance FROM ACCOUNT WHERE Cust_ID=" + user.getCust_ID() + " AND AccType=" + 1);) {
                    resultset.next();
                    double databalance = resultset.getDouble(1);
                    ResultSet resultset2 = statement.executeQuery("SELECT Balance FROM ACCOUNT WHERE Cust_ID=" + user.getCust_ID() + " AND AccType=" + 2);
                    resultset2.next();
                    double databalance2 = resultset2.getDouble(1);
                    
                    
                    
                    if (databalance >= Double.parseDouble(transactfield1.getText())) {
                        statement.executeUpdate("UPDATE ACCOUNT SET balance=balance+" + Double.parseDouble(transactfield1.getText()) + " WHERE Cust_ID=" + user.getCust_ID() + " AND AccType=" + 2);
                        statement.executeUpdate("UPDATE ACCOUNT SET balance=balance-" + Double.parseDouble(transactfield1.getText()) + " WHERE Cust_ID=" + user.getCust_ID() + " AND AccType=" + 1);
                    } else {
                        JOptionPane.showMessageDialog(this,"You don't have enough balance");
                    }
                } catch (SQLException e) {
                    transactfield1.setText("");
                } catch (Exception e) {
                    transactfield1.setText("");
                }
        updateBalance();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if(jComboBox1.getSelectedIndex()==1){
            betweenmyacc.setVisible(true);
            tobenfit.setVisible(false);
        } else if(jComboBox1.getSelectedIndex()==2){
            betweenmyacc.setVisible(false);
            tobenfit.setVisible(true);
        } else {
            betweenmyacc.setVisible(false);
            tobenfit.setVisible(false);
        }
            
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
       if (jTextField1.getText().length() >= 50)
                evt.consume();
    }//GEN-LAST:event_jTextField1KeyTyped

    private void firstnameupdateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_firstnameupdateKeyTyped
                if (firstnameupdate.getText().length() >= 25 || evt.getKeyChar()==KeyEvent.VK_SPACE)
                evt.consume();
    }//GEN-LAST:event_firstnameupdateKeyTyped

    private void addressupdateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addressupdateKeyTyped
                if (addressupdate.getText().length() >= 50)
                evt.consume();
    }//GEN-LAST:event_addressupdateKeyTyped

    private void lastnameupdateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastnameupdateKeyTyped
                if (lastnameupdate.getText().length() >= 25 || evt.getKeyChar()==KeyEvent.VK_SPACE)
                evt.consume();
    }//GEN-LAST:event_lastnameupdateKeyTyped

    private void phonenumberupdateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phonenumberupdateKeyTyped
                if (phonenumberupdate.getText().length() >= 10 || evt.getKeyChar()==KeyEvent.VK_SPACE)
                evt.consume();
                if (!(Character.isDigit(evt.getKeyChar()) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_DELETE )) 
                evt.consume();
    }//GEN-LAST:event_phonenumberupdateKeyTyped

    private void incomeupdateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_incomeupdateKeyTyped
                if (incomeupdate.getText().length() >= 10 )
                evt.consume();
                if (!(Character.isDigit(evt.getKeyChar()) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_PERIOD )) 
                evt.consume();
    }//GEN-LAST:event_incomeupdateKeyTyped

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        double loanam = user.getIncome()*24*0.2;
        double berate = user.getMembership()==1?0.05:user.getMembership()==2?0.035:user.getMembership()==3?0.2:0.5;
        double monthly=(loanam+loanam*berate)/24;
        double totalam=loanam+loanam*berate;
        String ldate= formattert.format(date);
        benrate.setText(String.format("%.2f",berate*100)+"%");
        jLabel25.setText(String.format("%.2f",loanam));
        benamount.setText(String.format("%.2f",loanam*berate)+" SAR");
        totalwithrate.setText(String.format("%.2f",loanam+loanam*berate)+" SAR");
        monthpay.setText(String.format("%.2f",(loanam+loanam*berate)/24)+" SAR");
        try {
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO LOAN(Amount, Monthly, LDate) VALUES("+totalam+","+monthly+",'"+ldate+"')"); 
                statement.executeUpdate("UPDATE CUSTOMER SET Loan_ID=LAST_INSERT_ID() WHERE Cust_ID="+user.getCust_ID());
                statement.executeUpdate("UPDATE ACCOUNT SET BALANCE=BALANCE+"+loanam+" WHERE Cust_ID="+user.getCust_ID()+" AND ACCTYPE=1");
                
        JOptionPane.showMessageDialog(null, "You got your loan!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e);
        }
        loanCheck();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        
        try {
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("SELECT LOAN.Loan_ID, Monthly, Amount from LOAN INNER JOIN CUSTOMER WHERE CUSTOMER.Loan_ID= LOAN.Loan_ID AND Cust_ID="+user.getCust_ID());
                resultset.next();
                
                int loanid = resultset.getInt(1);
                double monthlyd= resultset.getDouble(2);
                //double fullamount = resultset.getDouble(3);
                if(monthlyd>checking.getBalance()){
                JOptionPane.showMessageDialog(null, "You don't have enough balace in your checking account");
                }else{
                statement.executeUpdate("UPDATE LOAN SET Amount=Amount-"+monthlyd+" WHERE Loan_ID="+loanid);
                statement.executeUpdate("UPDATE ACCOUNT SET BALANCE=BALANCE-"+monthlyd+" WHERE Cust_ID="+user.getCust_ID()+" AND ACCTYPE=1");
                JOptionPane.showMessageDialog(null, "Thank you, you paid "+monthlyd);
                loanCheck();
                }
                
                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e);
        }
        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void fulldebtpaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fulldebtpaymentActionPerformed
              try {
                Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("SELECT LOAN.Loan_ID, Monthly, Amount from LOAN INNER JOIN CUSTOMER WHERE CUSTOMER.Loan_ID= LOAN.Loan_ID AND Cust_ID="+user.getCust_ID());
                resultset.next();
                
                int loanid = resultset.getInt(1);
                //double monthlyd= resultset.getDouble(2);
                double fullamount = resultset.getDouble(3);
                if(fullamount>checking.getBalance()){
                JOptionPane.showMessageDialog(null, "You don't have enough balace in your checking account");
                }else{
                statement.executeUpdate("UPDATE LOAN SET Amount=Amount-"+fullamount+" WHERE Loan_ID="+loanid);
                statement.executeUpdate("UPDATE ACCOUNT SET BALANCE=BALANCE-"+fullamount+" WHERE Cust_ID="+user.getCust_ID()+" AND ACCTYPE=1");
                JOptionPane.showMessageDialog(null, "Thank you, you paid "+fullamount);
                loanCheck();
                }

            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e);
        }
    }//GEN-LAST:event_fulldebtpaymentActionPerformed

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
            java.util.logging.Logger.getLogger(WansesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WansesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WansesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WansesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WansesFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SARlabel;
    private javax.swing.JLabel SARlabel1;
    private javax.swing.JLabel SARlabel2;
    private javax.swing.JLabel SARlabel3;
    private javax.swing.JLabel SARlabel4;
    private javax.swing.JLabel SARlabel5;
    private javax.swing.JPanel accountpanel;
    private javax.swing.JPanel addben;
    private javax.swing.JLabel addres;
    private javax.swing.JLabel addressdata;
    private javax.swing.JPanel addresspanel;
    private javax.swing.JTextField addressupdate;
    private javax.swing.JLabel balancecashlabel;
    private javax.swing.JLabel balancecashlabel1;
    private javax.swing.JLabel balancecashlabel2;
    private javax.swing.JLabel balancecashlabel3;
    private javax.swing.JLabel balancecashlabel4;
    private javax.swing.JLabel balancelabel;
    private javax.swing.JLabel balancelabel1;
    private javax.swing.JLabel balancelabel2;
    private javax.swing.JLabel balancelabel3;
    private javax.swing.JLabel balancelabel4;
    private javax.swing.JLabel balancelabel5;
    private javax.swing.JTextField benacc;
    private javax.swing.JLabel benacclabel;
    private javax.swing.JLabel benamount;
    private javax.swing.JLabel benconfirm;
    private javax.swing.JPanel benconfirmpanel;
    private javax.swing.JLabel benname;
    private javax.swing.JPanel benpanel1;
    private javax.swing.JPanel benpanel2;
    private javax.swing.JLabel benrate;
    private javax.swing.JButton benyes;
    private javax.swing.JPanel betweenmyacc;
    private javax.swing.JLabel briefsummarylabel;
    private javax.swing.JButton cancel;
    private javax.swing.JButton cancelinfo;
    private javax.swing.JButton changepass;
    private javax.swing.JPanel contentpanel;
    private javax.swing.JLabel custid;
    private javax.swing.JPanel custidpanel;
    private javax.swing.JLayeredPane dashboard;
    private javax.swing.JPanel debt;
    private javax.swing.JLabel debtdate;
    private javax.swing.JLabel debtid;
    private javax.swing.JLabel debtmonth;
    private javax.swing.JLabel debttotall;
    private javax.swing.JButton depositbutton1;
    private javax.swing.JLabel depositsuccess;
    private javax.swing.JButton editinfo;
    private javax.swing.JLabel firstn;
    private javax.swing.JLabel firstnamedata;
    private javax.swing.JPanel firstnamepanel;
    private javax.swing.JTextField firstnameupdate;
    private javax.swing.JLabel found;
    private javax.swing.JPanel front;
    private javax.swing.JButton fulldebtpayment;
    private javax.swing.JLabel incomedata;
    private javax.swing.JLabel incomee;
    private javax.swing.JPanel incomepanel;
    private javax.swing.JTextField incomeupdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lastn;
    private javax.swing.JLabel lastnamedata;
    private javax.swing.JPanel lastnamepanel;
    private javax.swing.JTextField lastnameupdate;
    private javax.swing.JLayeredPane leftboard;
    private javax.swing.JPanel leftpan;
    private javax.swing.JPanel loanpanel;
    private javax.swing.JLabel logindate;
    private javax.swing.JLabel logindate1;
    private javax.swing.JLabel logindate2;
    private javax.swing.JLabel logindate3;
    private javax.swing.JLabel logindate4;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JLabel member;
    private javax.swing.JLabel membershipdata;
    private javax.swing.JPanel membershippanel;
    private javax.swing.JLabel monthpay;
    private javax.swing.JTextField newpass;
    private javax.swing.JLabel notfound;
    private javax.swing.JTextField oldpass;
    private javax.swing.JToggleButton passhow;
    private javax.swing.JPanel passpanel;
    private javax.swing.JLabel passw;
    private javax.swing.JLabel passww;
    private javax.swing.JLabel phonen;
    private javax.swing.JLabel phonenumberdata;
    private javax.swing.JTextField phonenumberupdate;
    private javax.swing.JPanel phonepanel;
    private javax.swing.JComboBox<String> reciever;
    private javax.swing.JButton refreshbut;
    private javax.swing.JButton savechange;
    private javax.swing.JButton saveinfo;
    private javax.swing.JToggleButton sendtrans;
    private javax.swing.JButton signout;
    private javax.swing.JTable summary;
    private javax.swing.JPanel tobenfit;
    private javax.swing.JLabel totaltrans;
    private javax.swing.JLabel totalwithrate;
    private javax.swing.JTextField transactfield;
    private javax.swing.JTextField transactfield1;
    private javax.swing.JPanel transactionpanel;
    private javax.swing.JLabel usern;
    private javax.swing.JPanel usernamepanel;
    private javax.swing.JButton withdrawbutton1;
    private javax.swing.JLabel withdrawsuccess;
    // End of variables declaration//GEN-END:variables
}
