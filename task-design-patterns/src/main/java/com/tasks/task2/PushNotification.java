package com.tasks.task2;

public class PushNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending Push notification: " + message);
    }
}
