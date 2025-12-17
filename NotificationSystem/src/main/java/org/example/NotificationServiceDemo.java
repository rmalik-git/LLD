package org.example;

import org.example.notification.Notification;
import org.example.notification.NotificationPreference;
import org.example.notification.NotificationType;
import org.example.user.User;

import java.util.List;

public class NotificationServiceDemo {
    public static void main(String[] args) {
        NotificationService service = NotificationService.getInstance();

        User user1 = new User("John", "john1@example.com", "1234567890");
        User user2 = new User("John", "john2@example.com", "1234567891");
        Notification notification = new Notification("Hello!", user1.getId(),
                NotificationType.EMAIL);


        service.sendNotification(user1, notification);
        service.sendBulkNotifications(List.of(user1,user2), "System Maintenance Alert",
                NotificationType.SMS);

        List<Notification> notificationList = service.getDeliveryReportsByUser(user1.getId());
        System.out.println("Notification Delivery Reports for User: " + user1.getName());
        for (Notification notification1 : notificationList) {
            System.out.println("Notification ID: " + notification1.getId() +
                    ", Type: " + notification1.getType() +
                    ", Status: " + notification1.getDeliveryStatus());
        }
    }
}