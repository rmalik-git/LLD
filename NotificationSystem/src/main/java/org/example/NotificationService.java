package org.example;

import org.example.notification.DeliveryStatus;
import org.example.notification.EmailNotificationSender;
import org.example.notification.Notification;
import org.example.notification.NotificationSender;
import org.example.notification.NotificationType;
import org.example.notification.PushNotificationSender;
import org.example.notification.SMSNotificationSender;
import org.example.repository.NotificationRepository;
import org.example.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationService {
    private final Map<NotificationType, NotificationSender> senders;
    private final NotificationRepository repository;
    private static final NotificationService INSTANCE = new NotificationService();

    private NotificationService() {
        this.repository = new NotificationRepository();
        this.senders = new HashMap<>();
        senders.put(NotificationType.EMAIL, new EmailNotificationSender());
        senders.put(NotificationType.SMS, new SMSNotificationSender());
        senders.put(NotificationType.PUSH, new PushNotificationSender());
    }

    public static NotificationService getInstance() {
        return INSTANCE;
    }

    public void sendNotification(User user, Notification notification) {
        if (!user.getPreference().isEnabled(notification.getType())) {
            System.out.println("User has disabled " + notification.getType() + " notifications");
            notification.setDeliveryStatus(DeliveryStatus.FAILED);
            repository.save(notification);
            return;
        }

        NotificationSender sender = senders.get(notification.getType());
        if (sender == null) {
            throw new IllegalArgumentException("No sender for type: " + notification.getType());
        }

        try {
            sender.sendNotification(user, notification);
            notification.setDeliveryStatus(DeliveryStatus.SENT);
        } catch (Exception e) {
            notification.setDeliveryStatus(DeliveryStatus.FAILED);
        }
        repository.save(notification);
    }


    public void sendBulkNotifications(List<User> users, String message, NotificationType type) {
        for (User user : users) {
            Notification notification1=   new Notification(message,
                    user.getId(), type);
            sendNotification(user, notification1);
        }
    }

    public Notification getDeliveryReport(String notificationId) {
        return repository.findById(notificationId);
    }

    public List<Notification> getDeliveryReportsByUser(String userId) {
        return repository.findByUser(userId);
    }
}
