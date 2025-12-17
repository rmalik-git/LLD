package org.example.notification;

import org.example.user.User;

public class EmailNotificationSender implements NotificationSender {


    @Override
    public boolean sendNotification(User user, Notification notification) {
        try {
            System.out.println("Sending email to " + user.getEmail());
            // Actual sending logic
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public NotificationType getType() {
        return NotificationType.EMAIL;
    }


}
