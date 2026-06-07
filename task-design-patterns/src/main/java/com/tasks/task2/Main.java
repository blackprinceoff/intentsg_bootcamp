package com.tasks.task2;

public class Main {
    public static void main(String[] args) {
        NotificationService emailService = new NotificationService(new EmailNotification());
        emailService.notifyUser("Hello via Email!");

        NotificationService smsService = new NotificationService(new SmsNotification());
        smsService.notifyUser("Hello via SMS!");

        NotificationService pushService = new NotificationService(new PushNotification());
        pushService.notifyUser("Hello via Push!");
    }
}
