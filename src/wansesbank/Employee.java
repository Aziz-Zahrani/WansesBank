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
public class Employee {
    private int emp_ID;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private int emprole;
    private int bank_ID;

    public Employee() {
    }

    public Employee(int emp_ID, String firstname, String lastname, String username, String password, int emprole, int bank_ID) {
        this.emp_ID = emp_ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.emprole = emprole;
        this.bank_ID = bank_ID;
    }

    public int getEmp_ID() {
        return emp_ID;
    }

    public void setEmp_ID(int emp_ID) {
        this.emp_ID = emp_ID;
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

    public int getEmprole() {
        return emprole;
    }

    public void setEmprole(int emprole) {
        this.emprole = emprole;
    }

    public int getBank_ID() {
        return bank_ID;
    }

    public void setBank_ID(int bank_ID) {
        this.bank_ID = bank_ID;
    }
    
    
}
