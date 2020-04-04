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
public class Loan {
    private int loan_ID;
    private String purpose;
    private String paydate;
    private double amount;
    private int bank_ID;

    public Loan() {
    }

    public Loan(int loan_ID, String purpose, String paydate, double amount, int bank_ID) {
        this.loan_ID = loan_ID;
        this.purpose = purpose;
        this.paydate = paydate;
        this.amount = amount;
        this.bank_ID = bank_ID;
    }

    public int getLoan_ID() {
        return loan_ID;
    }

    public void setLoan_ID(int loan_ID) {
        this.loan_ID = loan_ID;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getBank_ID() {
        return bank_ID;
    }

    public void setBank_ID(int bank_ID) {
        this.bank_ID = bank_ID;
    }
    
    
    
}
