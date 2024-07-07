package edu.eu.senla.atm;

public class ATMApplication {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();
        ATM atm = new ATM(accountManager);
        atm.start();
    }
}
