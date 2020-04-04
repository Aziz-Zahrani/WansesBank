/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wansesbank;

/**
 *
 * @author Wans
 */
public class Account {
    private int Acc_ID;
    private int Acctype;
    private double balance;
    private int cust_ID;
    private int emp_ID;

    public Account() {
    }

    public Account(int Acc_ID, int Acctype, double balance, int cust_ID, int emp_ID) {
        this.Acc_ID = Acc_ID;
        this.Acctype = Acctype;
        this.balance = balance;
        this.cust_ID = cust_ID;
        this.emp_ID = emp_ID;
    }

    public Account(int Acc_ID, int Acctype, double balance, int cust_ID) {
        this.Acc_ID = Acc_ID;
        this.Acctype = Acctype;
        this.balance = balance;
        this.cust_ID = cust_ID;
    }
    
    

    public int getAcc_ID() {
        return Acc_ID;
    }

    public void setAcc_ID(int Acc_ID) {
        this.Acc_ID = Acc_ID;
    }

    public int getAcctype() {
        return Acctype;
    }

    public void setAcctype(int Acctype) {
        this.Acctype = Acctype;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCust_ID() {
        return cust_ID;
    }

    public void setCust_ID(int cust_ID) {
        this.cust_ID = cust_ID;
    }

    public int getEmp_ID() {
        return emp_ID;
    }

    public void setEmp_ID(int emp_ID) {
        this.emp_ID = emp_ID;
    }
    
    
}
