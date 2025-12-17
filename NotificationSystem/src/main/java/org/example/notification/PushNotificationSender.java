package org.example.notification;

import org.example.user.User;

public class PushNotificationSender implements NotificationSender {
    @Override
    public boolean sendNotification(User user, Notification notification) {
        if (user.getDeviceToken() == null || user.getDeviceToken().isEmpty()) {
            System.out.println(
                    "No device token available for user " + user.getName() + ". Cannot send push notification.");
            return false;
        }

        // Implementation for sending push notification
        System.out.println(
                "Sending push notification to device token " + user.getDeviceToken() + " with message: " + notification.getMessage());
        return true;
    }

    @Override
    public NotificationType getType() {
        return NotificationType.PUSH;
    }

}
