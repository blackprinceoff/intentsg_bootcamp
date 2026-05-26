package com.tasks.task1;

public class EmailDeliveryService implements ReportDelivery {
    @Override
    public void deliver(String content) {
        System.out.println("Sending email with:\n" + content);
    }
}
