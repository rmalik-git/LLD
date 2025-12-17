package org.example.bankingService;

import org.example.account.Account;
import org.example.account.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankingService {
    private static List<Account> accounts;
    private static Map<String, String> cardToAccountMap;

    public BankingService() {
        accounts = new ArrayList<>();
        cardToAccountMap = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        for (Card card : account.getCards()) {
            cardToAccountMap.put(card.getCardNumber(), account.getAccountNumber());
        }
    }

    private boolean authenticate(String cardNumber, String pin) {
        String accountNumber = cardToAccountMap.get(cardNumber);
        if (accountNumber == null) {
            throw new IllegalArgumentException("Card not recognized");
        }

        Account account = accounts.stream()
                .filter(acc -> acc.getAccountNumber().equals(accountNumber)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        Card card = account.getCards().stream().filter(c -> c.getCardNumber().equals(cardNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Card not found in account"));

        if (card.getPin().equals(pin)) {
            return true;
        }
        return false;
    }

    public void withdraw(String cardNumber, String pin, double amount) {
        try {
            Account account = requireAuthenticatedAccount(cardNumber, pin);
            account.withdraw(amount);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid card or account: " + e.getMessage());
        }

    }

    public boolean canWithdraw(String cardNumber, double amount) {
        Account account = getAccountByCardNumber(cardNumber);
        double accountBalance = account.getBalance();
        return amount <= accountBalance ? true : false;

    }

    public boolean deposit(String cardNumber, String pin, double amount) {
        try {
            Account account = requireAuthenticatedAccount(cardNumber, pin);
            account.deposit(amount);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid card or account: " + e.getMessage());
            return false;
        }

    }

    public double getBalance(String cardNumber, String pin) {
        Account account = null;
        try {
            account = requireAuthenticatedAccount(cardNumber, pin);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid card or account: " + e.getMessage());
        }
        return account.getBalance();
    }

    public void changePin(String cardNumber, String oldPin, String newPin) {
        Account account = null;
        try {
            account = requireAuthenticatedAccount(cardNumber, oldPin);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid card or account: " + e.getMessage());
        }


        Card card = account.getCards().stream().filter(c -> c.getCardNumber().equals(cardNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Card not found in account"));

        card.setPin(newPin);
        System.out.println("PIN changed successfully for card " + cardNumber);
    }


    private Account getAccountByCardNumber(String cardNumber) {
        String accountNumber = cardToAccountMap.get(cardNumber);
        if (accountNumber == null) throw new IllegalArgumentException("Card not recognized");
        return accounts.stream().filter(acc -> acc.getAccountNumber().equals(accountNumber))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }

    private Card getCardFromAccount(Account account, String cardNumber) {
        return account.getCards().stream().filter(c -> c.getCardNumber().equals(cardNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Card not found in account"));
    }

    private boolean isAuthenticated(String cardNumber, String pin) {
        Account account = getAccountByCardNumber(cardNumber);
        Card card = getCardFromAccount(account, cardNumber);
        return card.getPin().equals(pin);
    }

    private Account requireAuthenticatedAccount(String cardNumber, String pin) {
        if (!isAuthenticated(cardNumber, pin)) {
            throw new IllegalArgumentException("Authentication failed");
        }
        return getAccountByCardNumber(cardNumber);
    }
}
