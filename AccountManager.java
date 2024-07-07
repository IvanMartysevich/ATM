package edu.eu.senla.atm;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private static final String FILE_NAME = "C:\\Users\\BadFly\\Desktop\\Senla\\JavaSE\\src\\edu\\eu\\senla\\atm\\accounts.txt";
    private Map<String, Account> accounts = new HashMap<>();

    public AccountManager() {
        loadAccounts();
    }

    public Account getAccount(String cardNumber) {
        return accounts.get(cardNumber);
    }

    public void saveAccounts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Account account : accounts.values()) {
                writer.write(account.getCardNumber() + " " + account.getPinCode() + " " + account.getBalance());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }

    private void loadAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String cardNumber = parts[0];
                    String pinCode = parts[1];
                    double balance = Double.parseDouble(parts[2]);
                    accounts.put(cardNumber, new Account(cardNumber, pinCode, balance));
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке данных: " + e.getMessage());
        }
    }
}
