package org.example.strategy;

import org.example.entities.Split;
import org.example.entities.User;

import java.util.List;

public interface SplitStrategy {
    List<Split> calculateSplits(double totalAmount, User paidBy, List<User> participants, List<Double> splitValues);
}
