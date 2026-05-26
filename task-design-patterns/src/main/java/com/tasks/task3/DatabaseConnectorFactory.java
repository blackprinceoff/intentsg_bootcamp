package com.tasks.task3;

public class DatabaseConnectorFactory {
    public DatabaseConnector getConnector(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }

        switch (type.toLowerCase()) {
            case "postgres":
                return new PostgresConnector();
            case "mysql":
                return new MySQLConnector();
            case "mongo":
                return new MongoConnector();
            default:
                throw new IllegalArgumentException("Unknown database type: " + type);
        }
    }
}
