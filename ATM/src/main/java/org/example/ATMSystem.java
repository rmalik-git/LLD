package org.example;

import org.example.account.Account;
import org.example.atmCashManagement.CashManagementService;
import org.example.bankingService.BankingService;
import org.example.repository.Transaction;
import org.example.repository.TransactionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ATMSystem {
    private final BankingService bankingService;
    private final CashManagementService cashManagementService;
    private List<Transaction> transactions;
    private static ATMSystem instance;

    public ATMSystem(BankingService bankingService) {
        this.bankingService =bankingService;
        this.cashManagementService = new CashManagementService();
        this.transactions = new ArrayList<>();
    }

    public void loadCash(int denomination, int count) {
        cashManagementService.loadCash(denomination, count);
    }

    public void withdraw(String cardNumber, String pin, double amount) {
        if(!bankingService.canWithdraw(cardNumber,amount)){
            System.out.println("Insufficient account balance");
            return;
        }
        if(cashManagementService.getCashBalance()<amount){
            System.out.println("Insufficient ATM cash balance");
            return;
        }

        boolean isCashDispensed = cashManagementService.dispenseCash((int)amount);
        if(isCashDispensed) {
            bankingService.withdraw(cardNumber, pin, amount);
        }

        Transaction transaction = new Transaction(cardNumber, amount, TransactionType.WITHDRAW);
        transactions.add(transaction);
    }

    public void deposit(String cardNumber, String pin, Map<Integer, Integer> denominationCountMap) {
        double depositAmount = 0;
        for(Map.Entry<Integer, Integer> entry : denominationCountMap.entrySet()) {
            int denomination = entry.getKey();
            int count = entry.getValue();
            if(!cashManagementService.allowedDenominations.contains(denomination)){
               System.out.printf("ATM doesn't support %c denomination, please collect cash. Retry" +
                       " with denomination in %c", denomination, cashManagementService.allowedDenominations);
               continue;
            }

            depositAmount += count*denomination;

            cashManagementService.loadCash(denomination, count);
        }
        boolean isDepositSuccess = bankingService.deposit(cardNumber, pin, depositAmount);
        if(!isDepositSuccess){
            System.out.println("Bank is unable to update balance at this moment, Your balance " +
                    "will be updated in 24 hours");
        }
        Transaction transaction = new Transaction(cardNumber, depositAmount, TransactionType.DEPOSIT);
        transactions.add(transaction);
    }

    public double getBalance(String cardNumber, String pin) {
        return bankingService.getBalance(cardNumber,pin);
    }

    public void changePin(String cardNumber, String oldPin, String newPin) {
        bankingService.changePin(cardNumber, oldPin, newPin);
    }

}
