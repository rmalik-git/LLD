package org.example.repository;

import java.time.LocalDateTime;

public class Transaction {
    private final String cardNumber;

    private final double amount;
    private final TransactionType transactionType;
    private final LocalDateTime localDateTime;

    public Transaction(String cardNumber, double amount, TransactionType transactionType) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.localDateTime = LocalDateTime.now();
    }
}
