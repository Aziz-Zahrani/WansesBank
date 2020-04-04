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
public class Bank {
    private int bank_ID;
    private String location;
    
    
    public Bank() {
    }
    
    public Bank(int bank_ID, String location) {
        this.bank_ID = bank_ID;
        this.location = location;
    }

   

    
    
    
    public int getBank_ID() {
        return bank_ID;
    }

    public void setBank_ID(int bank_ID) {
        this.bank_ID = bank_ID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    
}
