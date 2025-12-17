package org.example.notification;

import org.example.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {
    private final String id;
    private final String message;
    private String recipient;
    private final NotificationType type;
    private final LocalDateTime sentAt;
    private DeliveryStatus deliveryStatus;

    public Notification(String message, String recipient, NotificationType type) {
        this.id = UUID.randomUUID().toString();
        this.message = message;
        this.type = type;
        this.recipient = null;
        this.sentAt = LocalDateTime.now();
        this.deliveryStatus = DeliveryStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String userId) {
        this.recipient = userId;
    }

    public NotificationType getType() {
        return type;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
         this.deliveryStatus=deliveryStatus;
    }
}
