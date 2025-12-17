package org.example.repository;

import org.example.notification.DeliveryStatus;
import org.example.notification.Notification;
import org.example.notification.NotificationType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationRepository {
    private final Map<String, Notification> notificationMap = new HashMap<>();

    public NotificationRepository() {
    }

    public void save(Notification notification) {
        notificationMap.put(notification.getId(), notification);
    }

    public Notification findById(String id) {
        return notificationMap.get(id);
    }

    public List<Notification> findByUser(String userId) {
        List<Notification> results = new java.util.ArrayList<>();
        for (Notification notification : notificationMap.values()) {
            if (notification.getRecipient().equals(userId)) {
                results.add(notification);
            }
        }
        return results;
    }

    public void updateStatus(String notificationId, DeliveryStatus status) {
        Notification notification = notificationMap.get(notificationId);
        if (notification != null) {
            notification.setDeliveryStatus(status);
        }
    }
}
