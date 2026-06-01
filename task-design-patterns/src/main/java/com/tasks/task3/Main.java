package com.tasks.task3;

public class Main {
    public static void main(String[] args) {
        DatabaseConnectorFactory factory = new DatabaseConnectorFactory();

        try {
            DatabaseConnector postgres = factory.getConnector("postgres");
            postgres.connect();
            postgres.disconnect();

            DatabaseConnector mysql = factory.getConnector("mysql");
            mysql.connect();
            mysql.disconnect();

            DatabaseConnector mongo = factory.getConnector("mongo");
            mongo.connect();
            mongo.disconnect();

            // Unknown type test
            System.out.println("Trying to connect to unknown DB:");
            DatabaseConnector unknown = factory.getConnector("oracle");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
