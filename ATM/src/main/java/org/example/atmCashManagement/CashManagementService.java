package org.example.atmCashManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CashManagementService {
    public final Set<Integer> allowedDenominations = Set.of(100, 500, 1000);
    private long cashBalance;
    private Map<Integer, Integer> cashInventory;

    public CashManagementService() {
        this.cashBalance = 0;
        this.cashInventory = new HashMap<>();
    }

    public boolean loadCash(int denomination, int value) {
        if (allowedDenominations.contains(denomination) && value > 0) {
            cashInventory.put(denomination, cashInventory.getOrDefault(denomination, 0) + value);
            cashBalance += value;
            return true;
        }
        return false;
    }


   public boolean dispenseCash(int amount) {
        if (amount > 0 && amount <= cashBalance) {
            // Simple dispensing logic (not optimal)
            for (int denom : allowedDenominations) {
                while (amount >= denom && cashInventory.getOrDefault(denom, 0) > 0) {
                    amount -= denom;
                    cashInventory.put(denom, cashInventory.get(denom) - 1);
                    cashBalance -= denom;
                }
            }
            return amount == 0;
        }
        return false;
    }

    public long getCashBalance() {
        return cashBalance;
    }

    public Map<Integer, Integer> getCashInventory() {
        return cashInventory;
    }
}
