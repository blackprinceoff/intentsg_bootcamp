package com.tasks.task4;

public class Main {
        public static void main(String[] args) {
                UserProfile basicProfile = new UserProfile.Builder("1001", "denys@gmail.com")
                                .build();
                System.out.println("Basic Profile: " + basicProfile);

                UserProfile profileWithAddress = new UserProfile.Builder("1002", "myroslav@gmail.com")
                                .address("Dovzhenko St, Lviv")
                                .phone("+380973581207")
                                .build();
                System.out.println("Profile with Address: " + profileWithAddress);

                UserProfile fullProfile = new UserProfile.Builder("1003", "oleksandr@gmail.com")
                                .address("Chornovola St, Lviv")
                                .phone("+380973334455")
                                .avatar("avatar.png")
                                .bio("Just a regular developer!")
                                .build();
                System.out.println("Full Profile: " + fullProfile);
        }
}
