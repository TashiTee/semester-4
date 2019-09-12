package sample;

import java.util.Date;
import java.io.Serializable;

public class Account implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private double balance;
    private double annualInterestRate;
    private Date dateCreated;

    public Account() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.annualInterestRate = 0;
        this.balance = 0;
        this.dateCreated = new Date();
    }

    public Account(int id, double balance) {
        this.id = id;
        this.firstName = "";
        this.lastName = "";
        this.balance = balance;
        this.dateCreated = new Date();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return this.balance;

    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAnnualInterestRate() {
        return this.annualInterestRate;

    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public String getFirstName() {
        return this.firstName;

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;

    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDate() {
        return this.dateCreated;
    }

    public double getMonthlyInterestRate() {
        return this.annualInterestRate / 12;
    }

    public double getMonthlyInterest() {
        return this.balance * this.getMonthlyInterest();
    }

    public void withdraw(double withdrawAmount) {
        balance -= withdrawAmount;
        System.out.println("Withdraw success: $" + withdrawAmount);
    }

    public void deposit(double depositAmount) {
        balance += depositAmount;
        System.out.println("Deposit success: $" + depositAmount);
    }

    public void print() {
        System.out.println(this.getId() + ": " + this.getBalance());
    }
}