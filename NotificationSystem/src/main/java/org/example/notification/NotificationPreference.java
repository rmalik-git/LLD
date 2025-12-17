package org.example.notification;

import java.util.HashMap;
import java.util.Map;

public class NotificationPreference {
    private final String userId;
    private final Map<NotificationType, Boolean> preferences;

    public NotificationPreference(String userId) {
        this.userId = userId;
        this.preferences = new HashMap<>();
        preferences.put(NotificationType.EMAIL, true);
        preferences.put(NotificationType.SMS, true);
        preferences.put(NotificationType.PUSH, true);
    }

    public String getUserId() {
        return userId;
    }

    public boolean isEnabled(NotificationType type) {
        return preferences.getOrDefault(type, false);
    }


    public void setPreferences(NotificationType type, Boolean enabled) {
       preferences.put(type, enabled);
    }
}
