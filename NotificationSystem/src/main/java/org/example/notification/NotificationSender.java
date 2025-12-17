package org.example.notification;

import org.example.user.User;

public interface NotificationSender {
    boolean sendNotification(User user, Notification notification);
    NotificationType getType();
}
