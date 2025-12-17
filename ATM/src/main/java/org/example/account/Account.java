package org.example.account;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String accountNumber;
    private final AccountType accountType;
    private double balance;
    private List<Card> cards = new ArrayList<>();

    public Account(String accountNumber, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
        System.out.println("Deposit amount " + amount + " to account " + accountNumber);
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdraw amount " + amount + " to account " + accountNumber);
            return true;
        }
        return false;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public  void removeCard(Card card) {
        cards.remove(card);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
