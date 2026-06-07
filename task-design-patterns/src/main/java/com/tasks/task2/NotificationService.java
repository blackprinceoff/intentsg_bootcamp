package com.tasks.task2;

public class NotificationService {
    private final Notification notification;

    public NotificationService(Notification notification) {
        this.notification = notification;
    }

    public void notifyUser(String message) {
        notification.send(message);
    }
}
