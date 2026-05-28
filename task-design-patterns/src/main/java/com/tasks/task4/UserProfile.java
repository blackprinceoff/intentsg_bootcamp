package com.tasks.task4;

public class UserProfile {
    private final String id;
    private final String email;
    private final String address;
    private final String phone;
    private final String avatar;
    private final String bio;

    private UserProfile(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.address = builder.address;
        this.phone = builder.phone;
        this.avatar = builder.avatar;
        this.bio = builder.bio;
    }

    public static class Builder {
        // Required parameters
        private final String id;
        private final String email;

        // Optional parameters
        private String address = "Not specified";
        private String phone = "Not specified";
        private String avatar = "default.png";
        private String bio = "No bio provided";

        public Builder(String id, String email) {
            this.id = id;
            this.email = email;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Builder bio(String bio) {
            this.bio = bio;
            return this;
        }

        public UserProfile build() {
            return new UserProfile(this);
        }
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
