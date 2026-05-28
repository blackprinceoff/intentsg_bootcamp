package com.tasks.task1;

public class FileDeliveryService implements ReportDelivery {
    @Override
    public void deliver(String content) {
        System.out.println("Saving to file:\n" + content);
    }
}
