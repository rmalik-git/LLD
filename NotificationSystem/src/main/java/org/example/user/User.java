package org.example.user;

import org.example.notification.NotificationPreference;

import java.util.UUID;

public class User {
    private final String id;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private  String deviceToken;
    private NotificationPreference preference;

    public User(String name, String email, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        preference = new NotificationPreference(id);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }


    public NotificationPreference getPreference() {
        return preference;
    }

    public void setPreference(NotificationPreference preference) {
        this.preference = preference;
    }
}
