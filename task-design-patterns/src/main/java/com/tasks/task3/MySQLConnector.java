package com.tasks.task3;

public class MySQLConnector implements DatabaseConnector {
    @Override
    public void connect() {
        System.out.println("Connecting to MySQL...");
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnecting from MySQL...");
    }
}
