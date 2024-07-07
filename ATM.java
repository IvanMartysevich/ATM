package edu.eu.senla.atm;

import java.util.Scanner;

public class ATM {
    private static final double MAX_DEPOSIT = 1000000;

    private final AccountManager accountManager;
    private final Scanner scanner;
    private Account currentAccount;

    public ATM(AccountManager accountManager) {
        this.accountManager = accountManager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Добро пожаловать в банкомат!");

        if (authorizeUser()) {
            boolean exit = false;
            while (!exit) {
                System.out.println("Выберите действие: ");
                System.out.println("1. Проверить баланс");
                System.out.println("2. Снять средства");
                System.out.println("3. Пополнить баланс");
                System.out.println("4. Выйти");
                System.out.print("Введите номер действия: ");
                int action = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (action) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        withdrawFunds();
                        break;
                    case 3:
                        depositFunds();
                        break;
                    case 4:
                        exit = true;
                        System.out.println("Выход из системы.");
                        break;
                    default:
                        System.out.println("Неверный номер действия. Попробуйте снова.");
                }
            }
            accountManager.saveAccounts();
        }
    }

    private boolean authorizeUser() {
        System.out.print("Введите номер карты (xxxx-xxxx-xxxx-xxxx): ");
        String cardNumber = scanner.nextLine();
        if (!cardNumber.matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}")) {
            System.out.println("Неверный формат номера карты.");
            return false;
        }

        System.out.print("Введите PIN-код: ");
        String pinCode = scanner.nextLine();

        currentAccount = accountManager.getAccount(cardNumber);
        if (currentAccount != null && currentAccount.getPinCode().equals(pinCode)) {
            return true;
        } else {
            System.out.println("Неверный номер карты или PIN-код.");
            return false;
        }
    }

    private void checkBalance() {
        System.out.println("Текущий баланс: " + currentAccount.getBalance());
    }

    private void withdrawFunds() {
        System.out.print("Введите сумму для снятия: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        if (amount > currentAccount.getBalance()) {
            System.out.println("Недостаточно средств на счете.");
        } else {
            currentAccount.withdraw(amount);
            System.out.println("Снятие выполнено успешно. Новый баланс: " + currentAccount.getBalance());
        }
    }

    private void depositFunds() {
        System.out.print("Введите сумму для пополнения: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        if (amount > MAX_DEPOSIT) {
            System.out.println("Сумма пополнения не должна превышать " + MAX_DEPOSIT);
        } else {
            currentAccount.deposit(amount);
            System.out.println("Пополнение выполнено успешно. Новый баланс: " + currentAccount.getBalance());
        }
    }
}
