package org.example;

import org.example.account.Account;
import org.example.account.AccountType;
import org.example.account.Card;
import org.example.bankingService.BankingService;

public class ATMDemo {
    public static void main(String[] args) {
       Card card = new Card("C1234", "1234");
        Account account = new Account("A1234", AccountType.SAVINGS);
        account.addCard(card);
        account.setBalance(10000);

        BankingService bankingService = new BankingService();
        bankingService.addAccount(account);

        ATMSystem atmSystem = new ATMSystem(bankingService);
        atmSystem.loadCash(100, 100);

        atmSystem.withdraw(card.getCardNumber(), card.getPin(), 100);
        System.out.println("Balance of account is" +atmSystem.getBalance(card.getCardNumber(),
                card.getPin()));
    }
}