package org.example.account;

public class Card {
    private final String cardNumber;
    private String pin;

    public Card(String cardNumber, String pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
