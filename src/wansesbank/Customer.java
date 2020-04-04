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
public class Customer {
    private int cust_ID;
    private String firstname;
    private String lastname;
    private String address;
    private String username;
    private String password;
    private String phonenumber;
    private int membership;
    private double income;
    private int loan_ID;

    public Customer(int cust_ID, String firstname, String lastname, String address, String username, String password, String phonenumber, int membership, double income, int loan_ID) {
        this.cust_ID = cust_ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.membership = membership;
        this.income = income;
        this.loan_ID = loan_ID;
    }

    public Customer(int cust_ID, String firstname, String lastname, String address, String username, String password, String phonenumber, int membership, double income) {
        this.cust_ID = cust_ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.membership = membership;
        this.income = income;
    }

    public Customer() {
    }

    
    
    public int getCust_ID() {
        return cust_ID;
    }

    public void setCust_ID(int cust_ID) {
        this.cust_ID = cust_ID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getMembership() {
        return membership;
    }

    public void setMembership(int membership) {
        this.membership = membership;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public int getLoan_ID() {
        return loan_ID;
    }

    public void setLoan_ID(int loan_ID) {
        this.loan_ID = loan_ID;
    }
    
    
}
