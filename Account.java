package edu.eu.senla.atm;

public class Account {
    private final String cardNumber;
    private final String pinCode;
    private double balance;

    public Account(String cardNumber, String pinCode, double balance) {
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}
